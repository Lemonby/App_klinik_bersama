package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopUpUtil {
    private static final Color POPUP_BG_COLOR = new Color(25, 185, 115);

    public static void showPopupDialog(Component parent, Font font, Icon logoIcon, String message, String title, int messageType) {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Icon based on message type
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
        if (font != null) messageLabel.setFont(font.deriveFont(Font.PLAIN, 14f));
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;                                               // casting ke Graphics2D untuk fitur lebih lanjut
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(POPUP_BG_COLOR);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        if (font != null) okButton.setFont(font.deriveFont(Font.BOLD, 13f));
        okButton.setForeground(Color.WHITE);
        okButton.setOpaque(false);
        okButton.setContentAreaFilled(false);
        okButton.setBorderPainted(false);
        okButton.setFocusPainted(false);
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                   // komponen di dalam panel akan diatur secara horizontal dan diratakan ke kanan
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);                                          // Menambahkan panel (buttonPanel) ke panel utama (panel) di sebelah selatan

        final JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), title); // Membuat objek dialog baru (JDialog) dengan parent window yang diambil dari komponen parent (agar dialog muncul di atas window yang benar) dan memberi judul sesuai parameter title.
        dialog.setUndecorated(true);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setSize(dialog.getWidth() + 40, dialog.getHeight() + 20);
        dialog.setLocationRelativeTo(parent);

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

        dialog.getRootPane().setDefaultButton(okButton);                                   // Mengatur tombol default pada dialog menjadi okButton.
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

        dialog.addKeyListener(keyAdapter);                                                // Menambahkan KeyListener ke dialog untuk menangani penekanan tombol Escape dan Enter
        panel.addKeyListener(keyAdapter);
        okButton.addKeyListener(keyAdapter);
        messageLabel.addKeyListener(keyAdapter);
        panel.setFocusable(true);

        dialog.setVisible(true);
    }
}