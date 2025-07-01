package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import controller.RegisterController;

public class FrameRegister extends JFrame {

    private final Font poppins;
    private ImageIcon logoIcon;
    private final Color POPUP_BG_COLOR = new Color(25, 185, 115);
    private final Color MAIN_BG_COLOR = new Color(26, 82, 54);

    public FrameRegister() {
        setTitle("Klinik Bersama Apps - Register Pasien");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());

        // Set content pane with dark green background
        JPanel contentPane = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(MAIN_BG_COLOR);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setOpaque(true);
        setContentPane(contentPane);

        // Load font Poppins
        Font tempFont;
        try {
            tempFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Poppins-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(tempFont);
        } catch (Exception ex) {
            System.err.println("Error loading font: " + ex.getMessage());
            tempFont = new Font("SansSerif", Font.PLAIN, 12);
        }
        poppins = tempFont;

        // Load logo icon
        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/img_src/logoKlinik.png"));
            logoIcon = new ImageIcon(logo.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        } catch (Exception ex) {
            System.err.println("Error loading logo: " + ex.getMessage());
        }

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());

        // Center container
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Logo and title section
        JPanel logoSection = new JPanel();
        logoSection.setOpaque(false);
        logoSection.setLayout(new BoxLayout(logoSection, BoxLayout.Y_AXIS));
        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/img_src/logoKlinik.png"));
            Image scaledLogo = logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoSection.add(logoLabel);
            logoSection.add(Box.createVerticalStrut(10));
        } catch (Exception ex) {
            System.err.println("Error loading logo: " + ex.getMessage());
        }
        JLabel titleLabel = new JLabel("REGISTER PASIEN");
        titleLabel.setFont(poppins.deriveFont(Font.BOLD, 22f));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoSection.add(titleLabel);

        centerPanel.add(logoSection);
        centerPanel.add(Box.createVerticalStrut(30));

        // Form section
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        CustomTextField userField = createFloatingLabelField("Username");
        userField.setMaximumSize(new Dimension(320, 45));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(userField);
        formPanel.add(Box.createVerticalStrut(12));

        CustomPasswordField passwordField = createFloatingLabelPasswordField("Password (min 8 karakter)");
        passwordField.setMaximumSize(new Dimension(320, 45));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(12));

        CustomTextField namaField = createFloatingLabelField("Nama Lengkap");
        namaField.setMaximumSize(new Dimension(320, 45));
        namaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(namaField);
        formPanel.add(Box.createVerticalStrut(12));

        CustomTextField nikField = createFloatingLabelField("NIK (16 digit angka)");
        nikField.setMaximumSize(new Dimension(320, 45));
        nikField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(nikField);
        formPanel.add(Box.createVerticalStrut(12));

        CustomTextField alamatField = createFloatingLabelField("Alamat");
        alamatField.setMaximumSize(new Dimension(320, 45));
        alamatField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(alamatField);
        formPanel.add(Box.createVerticalStrut(12));

        CustomTextField phoneField = createFloatingLabelField("No. Telepon");
        phoneField.setMaximumSize(new Dimension(320, 45));
        phoneField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(phoneField);
        formPanel.add(Box.createVerticalStrut(12));

        CustomTextField tglLahirField = createFloatingLabelField("Tanggal Lahir (YYYY-MM-DD)");
        tglLahirField.setMaximumSize(new Dimension(320, 45));
        tglLahirField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(tglLahirField);
        formPanel.add(Box.createVerticalStrut(12));

        // Jenis Kelamin
        JPanel genderPanel = new JPanel();
        genderPanel.setOpaque(false);
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.X_AXIS));
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        genderLabel.setFont(poppins.deriveFont(Font.PLAIN, 14f));
        genderLabel.setForeground(Color.WHITE);
        genderPanel.add(genderLabel);
        genderPanel.add(Box.createHorizontalStrut(10));
        JRadioButton lakiRadio = new JRadioButton("Laki-laki (L)");
        lakiRadio.setFont(poppins.deriveFont(Font.PLAIN, 13f));
        lakiRadio.setOpaque(false);
        lakiRadio.setForeground(Color.WHITE);
        JRadioButton perempuanRadio = new JRadioButton("Perempuan (P)");
        perempuanRadio.setFont(poppins.deriveFont(Font.PLAIN, 13f));
        perempuanRadio.setOpaque(false);
        perempuanRadio.setForeground(Color.WHITE);
        ButtonGroup kelaminGroup = new ButtonGroup();
        kelaminGroup.add(lakiRadio);
        kelaminGroup.add(perempuanRadio);
        genderPanel.add(lakiRadio);
        genderPanel.add(Box.createHorizontalStrut(10));
        genderPanel.add(perempuanRadio);
        genderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(genderPanel);
        formPanel.add(Box.createVerticalStrut(20));

        // Register button
        AnimatedButton registerButton = createAnimatedButton("REGISTER");
        registerButton.setMaximumSize(new Dimension(200, 45));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(registerButton);

        // Register action
        registerButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String nama = namaField.getText().trim();
            String nik = nikField.getText().trim();
            String tglLahir = tglLahirField.getText().trim();
            String alamat = alamatField.getText().trim();
            String phone = phoneField.getText().trim();
            String kelamin = "";
            if (lakiRadio.isSelected()) kelamin = "L";
            else if (perempuanRadio.isSelected()) kelamin = "P";

            RegisterController controller = new RegisterController();
            RegisterController.RegisterResult result = controller.RegistrasiPasien(
                username, password, nama, alamat, phone, kelamin, nik, tglLahir
            );
            switch (result) {
                case SUCCESS:
                    showPopupDialog("Akun berhasil terdaftar!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MainFrame().setVisible(true);
                    break;
                case USERNAME:
                    showPopupDialog("Username tidak boleh kosong!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    break;
                case PASSWORD:
                    showPopupDialog("Password tidak boleh kosong!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    break;
                case NAME:
                    showPopupDialog("Nama tidak boleh kosong!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    break;
                case ADDRESS:
                    showPopupDialog("Alamat tidak boleh kosong!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    break;
                case PHONE:
                    showPopupDialog("Nomor telepon tidak valid!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    break;
                case INVALID:
                    showPopupDialog("Pendaftaran tidak valid, Coba lagi!", "Gagal", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    break;
            }
        });
        // Enter key navigation
        userField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) passwordField.requestFocus();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) namaField.requestFocus();
            }
        });
        namaField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) alamatField.requestFocus();
            }
        });
        alamatField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) phoneField.requestFocus();
            }
        });
        phoneField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) lakiRadio.requestFocus();
            }
        });

        centerPanel.add(formPanel);
        mainPanel.add(centerPanel, new GridBagConstraints());

        // Back link
        JLabel backLink = new JLabel("< Back");
        backLink.setFont(poppins.deriveFont(Font.PLAIN, 14f));
        backLink.setForeground(Color.WHITE);
        backLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
                new MainFrame().setVisible(true);
            }
        });
        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 10));
        backPanel.add(backLink, BorderLayout.WEST);

        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(backPanel, BorderLayout.SOUTH);
    }

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
            setFont(poppins.deriveFont(Font.PLAIN, 14f));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 12, 20));
            setCaretColor(Color.WHITE);
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) { animateLabel(true); }
                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) animateLabel(false);
                }
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            super.paintComponent(g);
            if (getText().isEmpty() || isFloating) {
                g2.setFont(poppins.deriveFont(Font.PLAIN, isFloating ? 11f : 14f));
                g2.setColor(new Color(230, 230, 230));
                int labelX = 20;
                g2.drawString(label, labelX, labelY);
            }
            g2.dispose();
        }
        protected void animateLabel(boolean floating) {
            if (animationTimer != null && animationTimer.isRunning()) animationTimer.stop();
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
        protected void paintBorder(Graphics g) {}
    }

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
            setFont(poppins.deriveFont(Font.PLAIN, 14f));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 12, 20));
            setCaretColor(Color.WHITE);
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) { animateLabel(true); }
                @Override
                public void focusLost(FocusEvent e) {
                    if (getPassword().length == 0) animateLabel(false);
                }
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            super.paintComponent(g);
            if (getPassword().length == 0 || isFloating) {
                g2.setFont(poppins.deriveFont(Font.PLAIN, isFloating ? 11f : 14f));
                g2.setColor(new Color(230, 230, 230));
                int labelX = 20;
                g2.drawString(label, labelX, labelY);
            }
            g2.dispose();
        }
        protected void animateLabel(boolean floating) {
            if (animationTimer != null && animationTimer.isRunning()) animationTimer.stop();
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
        protected void paintBorder(Graphics g) {}
    }

    class AnimatedButton extends JButton {
        private Color currentColor = new Color(34, 186, 110);
        private Timer hoverTimer;
        public AnimatedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setForeground(Color.WHITE);
            setFont(poppins.deriveFont(Font.BOLD, 16f));
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) { animateColor(new Color(254, 191, 0)); }
                @Override
                public void mouseExited(MouseEvent e) { animateColor(new Color(34, 186, 110)); }
                @Override
                public void mousePressed(MouseEvent e) { animateColor(new Color(224, 171, 0)); }
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (contains(e.getPoint())) animateColor(new Color(254, 191, 0));
                    else animateColor(new Color(34, 186, 110));
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
            if (hoverTimer != null && hoverTimer.isRunning()) hoverTimer.stop();
            final Color startColor = currentColor;
            hoverTimer = new Timer(16, new ActionListener() {
                private int steps = 0;
                private final int totalSteps = 8;
                @Override
                public void actionPerformed(ActionEvent e) {
                    steps++;
                    float progress = (float) steps / totalSteps;
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
        protected void paintBorder(Graphics g) {}
    }

    private CustomTextField createFloatingLabelField(String labelText) {
        return new CustomTextField(labelText);
    }
    private CustomPasswordField createFloatingLabelPasswordField(String labelText) {
        return new CustomPasswordField(labelText);
    }
    private AnimatedButton createAnimatedButton(String text) {
        return new AnimatedButton(text);
    }

    private void showPopupDialog(String message, String title, int messageType) {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        Icon icon;
        if (messageType == JOptionPane.ERROR_MESSAGE) {
            icon = UIManager.getIcon("OptionPane.errorIcon");
        } else if (messageType == JOptionPane.INFORMATION_MESSAGE) {
            icon = UIManager.getIcon("OptionPane.informationIcon");
        } else {
            icon = logoIcon;
        }
        JLabel iconLabel = new JLabel(icon);
        panel.add(iconLabel, BorderLayout.WEST);
        JLabel messageLabel = new JLabel("<html><div style='width: 200px;'>" + message + "</div></html>");
        messageLabel.setFont(poppins.deriveFont(Font.PLAIN, 14f));
        panel.add(messageLabel, BorderLayout.CENTER);
        JButton okButton = new JButton("OK") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(POPUP_BG_COLOR);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        okButton.setFont(poppins.deriveFont(Font.BOLD, 13f));
        okButton.setForeground(Color.WHITE);
        okButton.setOpaque(false);
        okButton.setContentAreaFilled(false);
        okButton.setBorderPainted(false);
        okButton.setFocusPainted(false);
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        final JDialog dialog = new JDialog(this, title, true);
        dialog.setUndecorated(true);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setSize(dialog.getWidth() + 40, dialog.getHeight() + 20);
        dialog.setLocationRelativeTo(this);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 5),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        try {
            Class<?> roundRectClass = Class.forName("java.awt.geom.RoundRectangle2D$Double");
            Object roundRect = roundRectClass.getDeclaredConstructor(
                    double.class, double.class, double.class, double.class, double.class, double.class)
                    .newInstance(0, 0, dialog.getWidth(), dialog.getHeight(), 20, 20);
            dialog.setShape((Shape) roundRect);
        } catch (Exception ex) {
            System.err.println("Could not create rounded corners: " + ex.getMessage());
        }
        dialog.getRootPane().setDefaultButton(okButton);
        okButton.requestFocusInWindow();
        okButton.addActionListener(e -> dialog.dispose());
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dialog.dispose();
                }
            }
        };
        dialog.addKeyListener(keyAdapter);
        panel.addKeyListener(keyAdapter);
        okButton.addKeyListener(keyAdapter);
        messageLabel.addKeyListener(keyAdapter);
        panel.setFocusable(true);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new FrameRegister().setVisible(true));
    }
}
