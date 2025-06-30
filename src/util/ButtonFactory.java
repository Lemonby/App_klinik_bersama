package util;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class ButtonFactory {
    private final Color green  = Color.decode("#19b973");
    private final Color yellow = Color.decode("#FEBF00");
    private final Font Poppins;
    private final Font REGULAR_FONT;
    // private final Font HEADER_FONT;
    // private final Font SMALL_FONT;

    public ButtonFactory() {
        Font loadFont = FontCreator.getPopins();
        Poppins = loadFont;

        REGULAR_FONT = Poppins.deriveFont(Font.PLAIN, 14F);
        // HEADER_FONT = Poppins.deriveFont(Font.BOLD, 25F);
        // SMALL_FONT = Poppins.deriveFont(Font.BOLD, 12F);
    }

    // Tombol dengan sudut berradius dan animasi hover
    public JButton createRoundedButton(String text, Color fgColor) {
        JButton button = new JButton(text) {

            private Color currentColor = green;
            private boolean hover = false;
            private Timer animation;

            // ... override method dan tambahkan properti/behavior baru ...
            {
                setFont(REGULAR_FONT);
                setForeground(fgColor);
                setFocusPainted(false);
                setBorderPainted(false);
                setContentAreaFilled(false);
                setOpaque(false);
                setCursor(new Cursor(Cursor.HAND_CURSOR));

                animation = new Timer (10, e -> {
                    currentColor = animateColor(currentColor, hover ? yellow : green, 0.1f);
                });
                animation.start();

                // menambahkan evento mouse listener
                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) { hover = true; }
                    public void mouseExited(MouseEvent e) { hover = false; }
                });
            }

            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(currentColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.setColor(getForeground());

                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.setFont(getFont());
                g2.drawString(getText(), x, y);

                g2.dispose();
            }

            // digunakan untuk membuat transisi warna (animasi) yang halus
            private Color animateColor(Color from, Color to, float progress) {
                int r = (int) (from.getRed() + (to.getRed() - from.getRed()) * progress);
                int g = (int) (from.getGreen() + (to.getGreen() - from.getGreen()) * progress);
                int b = (int) (from.getBlue() + (to.getBlue() - from.getBlue()) * progress);
                return new Color(r, g, b);
            }
        };

        return button;
    }

    // Tombol dengan ikon dan animasi hover
    public JButton createIconButton(ImageIcon icon, Color bgColor) {
        JButton button = new JButton() {
            private Color currentColor = bgColor;
            private boolean hovering = false;
            private Timer animationTimer;

            {
                setFocusPainted(false);
                setBorderPainted(false);
                setContentAreaFilled(false);
                setOpaque(false);
                setPreferredSize(new Dimension(60, 60));
                setCursor(new Cursor(Cursor.HAND_CURSOR));

                animationTimer = new Timer(15, e -> {
                    currentColor = animateColor(currentColor, hovering ? bgColor.darker() : bgColor, 0.1f);
                    repaint();
                });
                animationTimer.start();

                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) { hovering = true; }
                    public void mouseExited(MouseEvent e) { hovering = false; }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                // pengaturan untuk membuat gambar lebih halus (tidak bergerigi)
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(currentColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

                if (icon != null) {
                    ImageIcon resizedIcon = resizeIcon(icon, getWidth() - 20, getHeight() - 20);
                    resizedIcon.paintIcon(this, g, (getWidth() - resizedIcon.getIconWidth()) / 2, (getHeight() - resizedIcon.getIconHeight()) / 2);
                }

                g2.dispose();
            }

            private Color animateColor(Color from, Color to, float progress) {
                int r = (int) (from.getRed() + (to.getRed() - from.getRed()) * progress);
                int g = (int) (from.getGreen() + (to.getGreen() - from.getGreen()) * progress);
                int b = (int) (from.getBlue() + (to.getBlue() - from.getBlue()) * progress);
                return new Color(r, g, b);
            }

            private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
                Image img = icon.getImage();
                Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(resizedImage);
            }
        };

        return button;
    }

    // Tombol dengan gradien warna
    public JButton createGradientButton(String text, Color startColor, Color endColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2.setPaint(gradient);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.setFont(getFont());
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };

        button.setFont(REGULAR_FONT);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // Tombol dengan efek bayangan
    public JButton createShadowButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Bayangan
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);

                // Tombol utama
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, 20, 20);

                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.setFont(getFont());
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
}
