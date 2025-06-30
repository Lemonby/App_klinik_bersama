package util;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockPanel extends JPanel {

    private JLabel timeLabel;
    private JLabel dateLabel;
    private Timer timer;
    private static ImageIcon cachedLogo;

    public ClockPanel() {
        // Set layout and styling for the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set layout panel vertical
        setBackground(Color.decode("#1a5236"));
        setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        // Add spacing
        add(Box.createRigidArea(new Dimension(0, 0)));

        // Initialize and style time and date labels
        timeLabel = new JLabel();
        dateLabel = new JLabel();

        timeLabel.setFont(new Font("Segoe UI Bold", Font.BOLD, 32));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateLabel.setFont(new Font("Segoe UI Regular", Font.PLAIN, 18));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add labels to the panel
        add(timeLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(dateLabel);

        // Start the timer to update the clock
        startClock();
    }

    private void addLogoPanel() {
        try {
            if (cachedLogo == null) {
            }

            JPanel logoPanel = new JPanel();
            logoPanel.setOpaque(false);
            logoPanel.setPreferredSize(new Dimension(120, 120));
            logoPanel.setLayout(new BorderLayout());

            JLabel logoLabel = new JLabel(cachedLogo);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoLabel.setVerticalAlignment(SwingConstants.CENTER);
            logoPanel.add(logoLabel);

            //Mengatur posisi horizontal panel itu sendiri saat ditambahkan ke parent
            logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(logoPanel);
        } catch (Exception e) {
            System.err.println("Error loading logo: " + e.getMessage());
        }
    }

    private void startClock() {
        timer = new Timer(1000, e -> SwingUtilities.invokeLater(() -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM dd");

            // add label text time, date with current time
            timeLabel.setText(now.format(timeFormat));
            dateLabel.setText(now.format(dateFormat));
        }));
        timer.start();
    }

    public void stopClock() {
        if (timer != null) {
            timer.stop();
        }
    }
}