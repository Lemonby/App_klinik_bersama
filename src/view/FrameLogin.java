package view;

import javax.swing.*;
import util.ButtonFactory;
import util.PopUpUtil;
import util.FontCreator;

import java.awt.*;
import java.awt.event.*;
// import view.MainFrame;

// import javax.swing.border.TitledBorder;

import controller.AkunController;
import controller.HistoryLogController;
// import model.source.Akun;
// import model.source.HistoryLog;

// import java.io.File;

public class FrameLogin extends JFrame {

    private ButtonFactory btn = new ButtonFactory();
    private final Font Poppins;
    private final Font HEADER_FONT;
    private final Font REGULAR_FONT;
    private final Font SMALL_FONT;
    private ImageIcon logoIcon;
    private final Color POPUP_BG_COLOR = new Color(25, 185, 115); // #19b973 - For popup
    private final Color MAIN_BG_COLOR = new Color(26, 82, 54); // Original dark green color

    public FrameLogin() {
        setTitle("Klinik Bersama Apps");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 4, screenSize.height /2 );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // Set content pane with original dark green background
        JPanel contentPane = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(MAIN_BG_COLOR); // Using the original dark green
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setOpaque(true);
        setContentPane(contentPane);

        // Load font Poppins using FontCreator utility
        Font loadedFont = FontCreator.getPopins();
        Poppins = loadedFont;
        HEADER_FONT = Poppins.deriveFont(Font.BOLD, 18f);
        REGULAR_FONT = Poppins.deriveFont(Font.PLAIN, 14f);
        SMALL_FONT = Poppins.deriveFont(Font.PLAIN, 12f);

        // Load logo icon (for popup)
        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/img_src/logoKlinik.png"));
            logoIcon = new ImageIcon(logo.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        } catch (Exception ex) {
            System.err.println("Error loading logo: " + ex.getMessage());
        }

        // Main panel with transparent background
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false); // Make it transparent to show the content pane's background
        mainPanel.setLayout(new GridBagLayout());
        
        // Center container
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        // Logo and title section
        JPanel logoSection = new JPanel();
        logoSection.setOpaque(false);
        logoSection.setLayout(new BoxLayout(logoSection, BoxLayout.Y_AXIS));
        
        // Logo - Enlarged as requested
        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/img_src/logoKlinik.png"));
            Image scaledLogo = logo.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // 150x150
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoSection.add(logoLabel);
            logoSection.add(Box.createVerticalStrut(15));
        } catch (Exception ex) {
            System.err.println("Error loading logo: " + ex.getMessage());
        }
        
        // Title
        JLabel titleLabel = new JLabel("KLINIK");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoSection.add(titleLabel);
        
        JLabel subtitleLabel = new JLabel("BERSAMA");
        subtitleLabel.setFont(HEADER_FONT);
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoSection.add(subtitleLabel);
        
        centerPanel.add(logoSection);
        centerPanel.add(Box.createVerticalStrut(40));
        
        // Form section
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        // Username field with floating label
        CustomTextField userField = createFloatingLabelField("UserName");
        userField.setMaximumSize(new Dimension(320, 45));
        userField.setPreferredSize(new Dimension(320, 45));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(userField);
        formPanel.add(Box.createVerticalStrut(15));
        
        // Password field with floating label
        CustomPasswordField passwordField = createFloatingLabelPasswordField("Password");
        passwordField.setMaximumSize(new Dimension(320, 45));
        passwordField.setPreferredSize(new Dimension(320, 45));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(25));
        
        // Login button with hover effect - yellow on hover
        AnimatedButton loginButton = createAnimatedLoginButton("LOGIN");
        loginButton.setMaximumSize(new Dimension(200, 45));
        loginButton.setPreferredSize(new Dimension(200, 45));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(loginButton);
        
        AkunController akunController = new AkunController();
        ActionListener loginAction = e -> {
            HistoryLogController logController = new HistoryLogController();
            String username = userField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                PopUpUtil.showPopupDialog(this, REGULAR_FONT, logoIcon, "Username dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            AkunController.LoginResult result = akunController.login(username, password);
            switch (result) {
                case ADMIN:
                    dispose();
                    logController.recordAdminLogin();
                    new AdminDashboard().setVisible(true);
                    PopUpUtil.showPopupDialog(this, REGULAR_FONT, logoIcon, "Selamat datang Admin!", "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case USER:
                    dispose();
                    logController.recordLogin(username);
                    new UserDashboard(username).setVisible(true);
                    PopUpUtil.showPopupDialog(this, REGULAR_FONT, logoIcon, "Selamat datang "+ username, "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case INVALID:
                    PopUpUtil.showPopupDialog(this, REGULAR_FONT, logoIcon, "Password salah", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case NOT_FOUND:
                    PopUpUtil.showPopupDialog(this, REGULAR_FONT, logoIcon, "Akun tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        };
        
        loginButton.addActionListener(loginAction);
        
        // Add key listeners for pressing Enter
        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordField.requestFocus();
                }
            }
        });
        
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });
        
        centerPanel.add(formPanel);
        mainPanel.add(centerPanel, new GridBagConstraints());
        
        // Back link in separate panel
        JLabel backLink = new JLabel("< Back");
        backLink.setFont(SMALL_FONT);
        backLink.setForeground(Color.WHITE);
        backLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
                new MainFrame().setVisible(true);
            }
        });
        
        // Back link panel - transparent to show the green background
        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 10));
        backPanel.add(backLink, BorderLayout.WEST);
        
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(backPanel, BorderLayout.SOUTH);
    }
    
    // Custom TextField class with proper animation handling
    class CustomTextField extends JTextField {
        protected String label;
        protected boolean isFloating = false;
        protected float labelY = 22f;
        protected Timer animationTimer;
        
        public CustomTextField(String label) {
            this.label = label;
            setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
            setForeground(Color.WHITE);
            setFont(REGULAR_FONT);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 12, 20));
            setCaretColor(Color.WHITE); // Set text cursor color to white for better visibility
            
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    animateLabel(true);
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        animateLabel(false);
                    }
                }
            });
        }
            
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            // Transparent background
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            
            // White border
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            
            // Paint text content
            super.paintComponent(g);
            
            // Paint floating label
            if (getText().isEmpty() || isFloating) {
                g2.setFont(Poppins.deriveFont(Font.PLAIN, isFloating ? 11f : 14f));
                g2.setColor(new Color(230, 230, 230)); // Slightly brighter white for better visibility
                int labelX = 20;
                g2.drawString(label, labelX, labelY);
            }
            
            g2.dispose();
        }
        
        protected void animateLabel(boolean floating) {
            if (animationTimer != null && animationTimer.isRunning()) {
                animationTimer.stop();
            }
            
            final float startY = labelY;
            final float targetY = floating ? 12f : 22f;
            isFloating = floating;
            
            animationTimer = new Timer(16, new ActionListener() {
                private int steps = 0;
                private final int totalSteps = 10;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    steps++;
                    float progress = (float) steps / totalSteps;
                    // Easing function for smooth animation
                    progress = (float) (1 - Math.pow(1 - progress, 3));
                    
                    labelY = startY + (targetY - startY) * progress;
                    repaint();
                    
                    if (steps >= totalSteps) {
                        labelY = targetY;
                        animationTimer.stop();
                    }
                }
            });
            animationTimer.start();
        }
        
        @Override
        protected void paintBorder(Graphics g) {
            // No default border
        }
    }
    
    // Custom PasswordField class with proper animation handling
    class CustomPasswordField extends JPasswordField {
        protected String label;
        protected boolean isFloating = false;
        protected float labelY = 22f;
        protected Timer animationTimer;
        
        public CustomPasswordField(String label) {
            this.label = label;
            setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 12, 20));
            setCaretColor(Color.WHITE); // Set text cursor color to white for better visibility
            
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    animateLabel(true);
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (getPassword().length == 0) {
                        animateLabel(false);
                    }
                }
            });
        }
            
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            // Transparent background
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            
            // White border
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            
            // Paint text content
            super.paintComponent(g);
            
            // Paint floating label
            if (getPassword().length == 0 || isFloating) {
                g2.setFont(Poppins.deriveFont(Font.PLAIN, isFloating ? 11f : 14f));
                g2.setColor(new Color(230, 230, 230)); // Slightly brighter white for better visibility
                int labelX = 20;
                g2.drawString(label, labelX, labelY);
            }
            
            g2.dispose();
        }
        
        protected void animateLabel(boolean floating) {
            if (animationTimer != null && animationTimer.isRunning()) {
                animationTimer.stop();
            }
            
            final float startY = labelY;
            final float targetY = floating ? 14f : 22f;
            isFloating = floating;
            
            animationTimer = new Timer(16, new ActionListener() {
                private int steps = 0;
                private final int totalSteps = 10;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    steps++;
                    float progress = (float) steps / totalSteps;
                    // Easing function for smooth animation
                    progress = (float) (1 - Math.pow(1 - progress, 3));
                    
                    labelY = startY + (targetY - startY) * progress;
                    repaint();
                    
                    if (steps >= totalSteps) {
                        labelY = targetY;
                        animationTimer.stop();
                    }
                }
            });
            animationTimer.start();
        }
        
        @Override
        protected void paintBorder(Graphics g) {
            // No default border
        }
    }
    
    // AnimatedButton class with proper animation handling
    class AnimatedButton extends JButton {
        private Color currentColor = new Color(34, 186, 110); // Default button color
        private Timer hoverTimer;
        
        public AnimatedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setForeground(Color.WHITE);
            setFont(Poppins.deriveFont(Font.BOLD, 16f));
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Hover effects
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    animateColor(new Color(254, 191, 0)); // Hover color #FEBF00 (yellow)
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    animateColor(new Color(34, 186, 110)); // Normal color - back to green
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    animateColor(new Color(224, 171, 0)); // Pressed color (slightly darker yellow)
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (contains(e.getPoint())) {
                        animateColor(new Color(254, 191, 0)); // Back to hover (#FEBF00)
                    } else {
                        animateColor(new Color(34, 186, 110)); // Back to normal
                    }
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(currentColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            g2.dispose();
            super.paintComponent(g);
        }
        
        private void animateColor(Color targetColor) {
            if (hoverTimer != null && hoverTimer.isRunning()) {
                hoverTimer.stop();
            }
            
            final Color startColor = currentColor;
            
            hoverTimer = new Timer(16, new ActionListener() {
                private int steps = 0;
                private final int totalSteps = 8;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    steps++;
                    float progress = (float) steps / totalSteps;
                    // Smooth easing
                    progress = (float) (1 - Math.pow(1 - progress, 2));
                    
                    int r = (int) (startColor.getRed() + (targetColor.getRed() - startColor.getRed()) * progress);
                    int g = (int) (startColor.getGreen() + (targetColor.getGreen() - startColor.getGreen()) * progress);
                    int b = (int) (startColor.getBlue() + (targetColor.getBlue() - startColor.getBlue()) * progress);
                    
                    currentColor = new Color(r, g, b);
                    repaint();
                    
                    if (steps >= totalSteps) {
                        currentColor = targetColor;
                        hoverTimer.stop();
                    }
                }
            });
            hoverTimer.start();
        }
        
        @Override
        protected void paintBorder(Graphics g) {
            // No default border
        }
    }
    
    // TextField with floating label effect
    private CustomTextField createFloatingLabelField(String labelText) {
        return new CustomTextField(labelText);
    }
    
    // Password field with floating label effect
    private CustomPasswordField createFloatingLabelPasswordField(String labelText) {
        return new CustomPasswordField(labelText);
    }
    
    // Button with hover animation
    private AnimatedButton createAnimatedLoginButton(String text) {
        return new AnimatedButton(text);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new FrameLogin().setVisible(true));
    }
}