package view;

import util.ButtonFactory;
import util.FontCreator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // Fonts
    private final Font Poppins;
    private final Font HEADER_FONT;
    // private final Font REGULAR_FONT;
    private final Font SMALL_FONT;
    private final Color DARK_GREEN = Color.decode("#1a5236");
    // private final Color LIGHT_GREEN = Color.decode("#19b973");
    // private final Color YELLOW = Color.decode("#FEBF00");
    
    public MainFrame() {
        setTitle("Klinik Bersama Apps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 4, screenSize.height /2 );      
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(true);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPanel.setBackground(DARK_GREEN);

        add(Box.createVerticalGlue());

        // Use FontCreator to get the font
        Font loadedFont = FontCreator.getPopins();
        Poppins = loadedFont;

        // Use deriveFont for different styles and sizes
        HEADER_FONT = Poppins.deriveFont(Font.BOLD, 25f);
        // REGULAR_FONT = Poppins.deriveFont(Font.PLAIN, 14f);
        SMALL_FONT = Poppins.deriveFont(Font.PLAIN, 12f);

        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/img_src/logoKlinik.png"));
            if (logo.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Gambar tidak bisa dimuat!");
            }

            Image scaledImage = logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel logoLabel = new JLabel(scaledIcon);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(logoLabel);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        } catch (Exception ex) {
            System.err.println("Error loading logo: " + ex.getMessage());
        }
        JPanel logotextPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        logotextPanel.setOpaque(true);
        logotextPanel.setBackground(DARK_GREEN);
        logotextPanel.setPreferredSize(new Dimension(150,90));

        JLabel klinikLabel = new JLabel("Klinik");
        klinikLabel.setFont(HEADER_FONT);
        klinikLabel.setHorizontalAlignment(SwingConstants.CENTER);
        klinikLabel.setForeground(Color.WHITE);
        JLabel bersamaLabel = new JLabel("Bersama");
        bersamaLabel.setFont(HEADER_FONT);
        bersamaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bersamaLabel.setForeground(Color.WHITE);
        JLabel alamatLabel = new JLabel("Jl. Sendiri No.1, Kel. Senang, Kec.Bahagia, Kota Asmara");
        alamatLabel.setFont(SMALL_FONT);
        alamatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alamatLabel.setForeground(Color.WHITE);
        logotextPanel.add(klinikLabel);
        logotextPanel.add(bersamaLabel);
        logotextPanel.add(alamatLabel);

        contentPanel.add(logotextPanel);

        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setOpaque(true);
        // Set a larger width for the cardPanel to allow buttons to expand
        cardPanel.setPreferredSize(new Dimension(130, 200));
        cardPanel.setBackground(DARK_GREEN);

        ButtonFactory buttonFactory = new ButtonFactory();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0);

        JButton loginButton = buttonFactory.createRoundedButton("LOGIN", Color.WHITE);
        JButton registerButton = buttonFactory.createRoundedButton("REGISTER", Color.WHITE);

        // Set button width (e.g., 160px wide, 40px tall)
        Dimension buttonSize = new Dimension(120, 50);
        loginButton.setPreferredSize(buttonSize);
        loginButton.setMinimumSize(buttonSize);
        loginButton.setMaximumSize(buttonSize);
        registerButton.setPreferredSize(buttonSize);
        registerButton.setMinimumSize(buttonSize);
        registerButton.setMaximumSize(buttonSize);

        loginButton.addActionListener(e -> {
            dispose();
            new FrameLogin().setVisible(true);
        });

        registerButton.addActionListener(e -> {
            dispose();
            new FrameRegister().setVisible(true);
        });

        gbc.gridy = 0;
        cardPanel.add(loginButton, gbc);

        gbc.gridy = 1;
        cardPanel.add(registerButton, gbc);

        contentPanel.add(cardPanel);
        contentPanel.add(Box.createVerticalGlue());

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1.0;
        mainGbc.weighty = 1.0;
        mainGbc.fill = GridBagConstraints.BOTH;

        add(contentPanel, mainGbc);
    }
}
