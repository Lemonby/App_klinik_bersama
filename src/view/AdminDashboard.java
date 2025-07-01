package view;

import javax.swing.*;
// import javax.swing.border.*;

import controller.HistoryLogController;
import util.ClockPanel;
import util.FontCreator;
import view.panel.HistoryLogPanel;
import view.panel.PanelDokter;
import view.panel.PanelViewPasien;
import view.panel.PanelVisibilityController;
import view.panel.PanelTambahDokter;
import java.awt.*;
import java.awt.event.*;
// import java.awt.image.BufferedImage;
import util.ImageLoader;

public class AdminDashboard extends JFrame {
    // Colors
    private final Color DARK_GREEN = Color.decode("#1a5236");
    private final Color LIGHT_GREEN = Color.decode("#19b973");
    private final Color YELLOW = Color.decode("#FEBF00");

    // Fonts
    private final Font Poppins;
    private final Font HEADER_FONT;
    private final Font REGULAR_FONT;
    private final Font SMALL_FONT;

    private HistoryLogController logControl;
    private PanelViewPasien patientsListPanel;
    private HistoryLogPanel historyLogPanel;
    private PanelDokter dokterPanel;
    private PanelVisibilityController visibilityController;
    private JPanel mainContentPanel;
    private PanelTambahDokter panelTambahDokter;
    private String activeMenu = "Dashboard";
    private CardLayout cardLayout;
    
    // Cache menu items to prevent recreation
    private final JPanel sidebarPanel;

    public AdminDashboard() {
        setTitle("Klinik Bersama - ADMINISTRATOR");
        setIconImage(ImageLoader.getImage("/img_src/logoKlinik.png", 50, 50).getImage());
        setMinimumSize(new Dimension(1200, 800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load resources only once
        Font loadedFont = FontCreator.getPopins();
        Poppins = loadedFont;
        HEADER_FONT = Poppins.deriveFont(Font.BOLD, 18f);
        REGULAR_FONT = Poppins.deriveFont(Font.PLAIN, 14f);
        SMALL_FONT = Poppins.deriveFont(Font.PLAIN, 12f);

        // Initialize components only once
        patientsListPanel = new PanelViewPasien();
        historyLogPanel = new HistoryLogPanel();
        visibilityController = new PanelVisibilityController();
        panelTambahDokter = new PanelTambahDokter();

        // Create layout and main panel
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(LIGHT_GREEN);

        // Create sidebar once
        sidebarPanel = createSidebarPanel();

        // Create panels only once
        JPanel dashboardPanel = createDashboardPanelWithHeader();
        mainContentPanel.add(dashboardPanel, "Dashboard");
        mainContentPanel.add(panelTambahDokter, "DoctorConfig");

        setupUI();
        setVisible(true);
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));
        sidebarPanel.setBackground(DARK_GREEN);

        // Logo section
        JPanel logoPanel = createLogoPanel();
        sidebarPanel.add(logoPanel, BorderLayout.NORTH);

        // Menu section
        JPanel menuPanel = createMenuPanel();
        sidebarPanel.add(menuPanel, BorderLayout.CENTER);

        // Clock section
        ClockPanel clockPanel = new ClockPanel();
        sidebarPanel.add(clockPanel, BorderLayout.SOUTH);

        return sidebarPanel;
    }
    
    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setPreferredSize(new Dimension(250, 150));
        logoPanel.setBackground(DARK_GREEN);
        
        // Use cached image if available
        ImageIcon logoKlinik = ImageLoader.getImage("/img_src/logoKlinik.png", 120, 120);
        JLabel logoLabel = new JLabel(logoKlinik);
        logoPanel.add(logoLabel, BorderLayout.NORTH);

        JLabel greetingsLabel = new JLabel("ADMINISTRATOR");
        greetingsLabel.setFont(HEADER_FONT);
        greetingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        greetingsLabel.setForeground(Color.WHITE);
        logoPanel.add(greetingsLabel, BorderLayout.CENTER);
        
        return logoPanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(DARK_GREEN);
        menuPanel.setForeground(Color.WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        JLabel analyticsLabel = new JLabel("ADMIN SERVICES");
        analyticsLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        analyticsLabel.setBackground(DARK_GREEN);
        analyticsLabel.setForeground(Color.WHITE);
        analyticsLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 0));
        analyticsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuPanel.add(analyticsLabel);

        String[][] menuItems = {
            {"Dashboard", "home", "ADMIN SERVICES"},
            {"User Configuration", "profile", "ADMIN SERVICES"},
            {"Doctor Configuration", "doctors", "ADMIN SERVICES"},
            {"Medicine", "obat", "CLINIC SERVICES"},
            {"Reports", "reports", "CLINIC SERVICES"},
            {"Doctors", "stetoscop", "CLINIC SERVICES"},
        };

        String currentCategory = "ADMIN SERVICES";
        for (String[] item : menuItems) {
            String label = item[0];
            String icon = item[1];
            String category = item[2];

            if (!category.equals(currentCategory)) {
                currentCategory = category;
                JLabel categoryLabel = new JLabel(currentCategory);
                categoryLabel.setFont(new Font("Poppins", Font.BOLD, 12));
                categoryLabel.setBackground(DARK_GREEN);
                categoryLabel.setForeground(Color.WHITE);
                categoryLabel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 0));
                categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                menuPanel.add(categoryLabel);
            }

            JPanel menuItem = createMenuItem(label, icon, label.equals(activeMenu));
            if (label.equals(activeMenu)) {
                menuItem.setBackground(YELLOW);
            } else {
                menuItem.setBackground(DARK_GREEN);
            }
            menuPanel.add(menuItem);
        }
        
        return menuPanel;
    }

    private JPanel createMenuItem(String text, String iconName, boolean isActive) {
        JPanel menuItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        menuItem.setMaximumSize(new Dimension(250, 40));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(REGULAR_FONT);
        textLabel.setForeground(Color.WHITE);

        ImageIcon icon = ImageLoader.getIcon(iconName, 22);
        JLabel iconLabel = new JLabel();
        if (icon != null) {
            iconLabel.setIcon(icon);
        } else {
            iconLabel.setText("•");
            iconLabel.setFont(new Font("Poppins", Font.BOLD, 20));
            iconLabel.setForeground(Color.WHITE);
        }

        menuItem.add(iconLabel);
        menuItem.add(textLabel);

        menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Check activeMenu dynamically
                if (!text.equals(activeMenu)) {
                    menuItem.setBackground(YELLOW);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!text.equals(activeMenu)) {
                    menuItem.setBackground(DARK_GREEN);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                activeMenu = text;
                refreshActiveMenuUI();
                if (text.equals("Dashboard")) {
                    cardLayout.show(mainContentPanel, "Dashboard");
                } else if (text.equals("Doctor Configuration")) {
                    cardLayout.show(mainContentPanel, "DoctorConfig");
                }
                // ...other menu actions...
            }
        });

        return menuItem;
    }
    
    // New method to refresh only the active menu highlights without recreating the sidebar
    private void refreshActiveMenuUI() {
        Component[] components = ((JPanel)sidebarPanel.getComponent(1)).getComponents();
        for (Component component : components) {
            if (component instanceof JPanel && component.getPreferredSize().height <= 40) {
                JPanel menuItem = (JPanel) component;
                Component[] menuComponents = menuItem.getComponents();
                if (menuComponents.length > 1 && menuComponents[1] instanceof JLabel) {
                    JLabel label = (JLabel) menuComponents[1];
                    boolean isActive = label.getText().equals(activeMenu);

                    if (isActive) {
                        menuItem.setBackground(YELLOW);
                        menuItem.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.GRAY));
                    } else {
                        menuItem.setBackground(DARK_GREEN);
                        menuItem.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                    }
                }
            }
        }
        revalidate();
        repaint();
    }

    private JPanel createHeaderPanel() {
        logControl = new HistoryLogController();
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARK_GREEN);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        
        JLabel welcomeTextLabel = new JLabel("Welcome, Administrator");
        welcomeTextLabel.setFont(HEADER_FONT);
        welcomeTextLabel.setForeground(Color.WHITE);
        headerPanel.add(welcomeTextLabel, BorderLayout.WEST);
        
        headerPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
        
        // Use cached image loading
        ImageIcon logout = ImageLoader.getImage("/img_src/logout.png", 50, 50);
        JLabel logoutLabel = new JLabel(logout);
        logoutLabel.setPreferredSize(new Dimension(50, 50));
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    logControl.recordAdminLogout();
                    dispose();
                }
            }
        });
        headerPanel.add(logoutLabel, BorderLayout.EAST);
        
        return headerPanel;
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
        dashboardPanel.setPreferredSize(new Dimension(950, 900));
        dashboardPanel.setBackground(LIGHT_GREEN);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel dashboardHeaderPanel = createPanelVisibilityComboBox();
        dashboardHeaderPanel.setBackground(LIGHT_GREEN);
        dashboardHeaderPanel.setMaximumSize(new Dimension(900, 50));
        dashboardPanel.add(dashboardHeaderPanel);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        contentPanel.setBackground(LIGHT_GREEN);
        contentPanel.setMaximumSize(new Dimension(900, 600));

        // Use the persistent instances
        contentPanel.add(historyLogPanel);
        contentPanel.add(patientsListPanel);

        dashboardPanel.add(contentPanel);

        return dashboardPanel;
    }

    private JPanel createPanelVisibilityComboBox() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));

        JLabel label = new JLabel("Show Panels:");
        label.setFont(REGULAR_FONT);
        label.setForeground(Color.WHITE);

        JCheckBox patientsCheckBox = new JCheckBox("Patients List", true);
        patientsCheckBox.setFont(REGULAR_FONT);
        patientsCheckBox.setBackground(LIGHT_GREEN);

        JCheckBox historyCheckBox = new JCheckBox("History Log", true);
        historyCheckBox.setFont(REGULAR_FONT);
        historyCheckBox.setBackground(LIGHT_GREEN);

        patientsListPanel.setVisible(true);
        historyLogPanel.setVisible(true);

        patientsCheckBox.addActionListener(e -> {
            patientsListPanel.setVisible(patientsCheckBox.isSelected());
            patientsListPanel.getParent().revalidate();
            patientsListPanel.getParent().repaint();
        });

        historyCheckBox.addActionListener(e -> {
            historyLogPanel.setVisible(historyCheckBox.isSelected());
            historyLogPanel.getParent().revalidate();
            historyLogPanel.getParent().repaint();
        });

        panel.add(label);
        panel.add(patientsCheckBox);
        panel.add(historyCheckBox);

        return panel;
    }

    private void showPanelTambahDokter() {
        cardLayout.show(mainContentPanel, "DoctorConfig");
    }

    private void showDashboardPanel() {
        cardLayout.show(mainContentPanel, "Dashboard");
    }

    private JPanel createDashboardPanelWithHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createHeaderPanel(), BorderLayout.NORTH);
        panel.add(createDashboardScrollPane(), BorderLayout.CENTER);
        return panel;
    }

    private JScrollPane createDashboardScrollPane() {
        JPanel dashboardPanel = createDashboardPanel();
        JScrollPane scrollPane = new JScrollPane(
            dashboardPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().addMouseWheelListener(e -> {
            if (e.isShiftDown()) {
                JScrollBar hBar = scrollPane.getHorizontalScrollBar();
                int amount = e.getUnitsToScroll() * hBar.getUnitIncrement();
                hBar.setValue(hBar.getValue() + amount);
                e.consume();
            }
        });
        return scrollPane;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new AdminDashboard());
    }
}
