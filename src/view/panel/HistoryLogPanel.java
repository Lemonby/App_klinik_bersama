package view.panel;

import controller.HistoryLogController;
import model.source.Akun;
import model.source.HistoryLog;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryLogPanel extends JPanel {

    // UI Components
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTable table;
    private JTextField searchField;
    private JTextField dateField;
    
    // Controller
    private final HistoryLogController historyLogController;

    public HistoryLogPanel() {
        historyLogController = new HistoryLogController();
        initializeUI();
        setupTable();
        setupSearchPanel();
        // customizeTableAppearance();
        loadHistory();
        setVisible(true);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 400));
    }

    private void setupTable() {
        String[] columnNames = {"ID", "Tanggal & Waktu", "Aksi", "Username"};
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setRowHeight(28);

        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // Set preferred column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID column
        table.getColumnModel().getColumn(1).setPreferredWidth(180); // Date & Time
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  // Action
        table.getColumnModel().getColumn(3).setPreferredWidth(120); // Username

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setupSearchPanel() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setPreferredSize(new Dimension(800, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;

        // Search label
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel searchLabel = new JLabel("Cari Username/Aksi:");
        topPanel.add(searchLabel, gbc);

        // Search field
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(120, 28));
        topPanel.add(searchField, gbc);

        // Search button
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton searchButton = new JButton("Cari");
        searchButton.setPreferredSize(new Dimension(70, 28));
        searchButton.addActionListener(e -> performSearch());
        topPanel.add(searchButton, gbc);

        // Date label
        gbc.gridx = 3;
        gbc.weightx = 0;
        JLabel dateLabel = new JLabel("Filter Tanggal (yyyy-MM-dd):");
        topPanel.add(dateLabel, gbc);

        // Date field
        gbc.gridx = 4;
        gbc.weightx = 0.15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(90, 28));
        topPanel.add(dateField, gbc);

        // Date button
        gbc.gridx = 5;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton dateButton = new JButton("Filter");
        dateButton.setPreferredSize(new Dimension(70, 28));
        dateButton.addActionListener(e -> filterByDate());
        topPanel.add(dateButton, gbc);

        // Refresh button
        gbc.gridx = 6;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(80, 28));
        refreshButton.addActionListener(e -> refreshData());
        topPanel.add(refreshButton, gbc);
        
        // Clear logs button
        gbc.gridx = 7;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton clearButton = new JButton("Hapus Semua");
        clearButton.setPreferredSize(new Dimension(110, 28));
        clearButton.addActionListener(e -> clearLogs());
        topPanel.add(clearButton, gbc);

        add(topPanel, BorderLayout.NORTH);
    }

    // private void customizeTableAppearance() {
    //     DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
    //         @Override
    //         public Component getTableCellRendererComponent(JTable table, Object value, 
    //                 boolean isSelected, boolean hasFocus, int row, int column) {
    //             Component c = super.getTableCellRendererComponent(
    //                     table, value, isSelected, hasFocus, row, column);
                
    //             // Highlight rows based on action type and user type
    //             String action = tableModel.getValueAt(
    //                     table.convertRowIndexToModel(row), 2).toString();
    //             String userType = tableModel.getValueAt(
    //                     table.convertRowIndexToModel(row), 4).toString();
                
    //             if (userType.equals("ADMIN")) {
    //                 c.setFont(c.getFont().deriveFont(Font.BOLD));
    //             } else {
    //                 c.setFont(c.getFont().deriveFont(Font.PLAIN));
    //             }
                
    //             if (action.equals("LOGIN")) {
    //                 c.setForeground(new Color(0, 100, 0)); // Dark green for login
    //             } else if (action.equals("LOGOUT")) {
    //                 c.setForeground(new Color(128, 0, 0)); // Dark red for logout
    //             } else {
    //                 c.setForeground(Color.BLACK);
    //             }
    //             return c;
    //         }
    //     };
        
    //     // Apply renderer to all columns
    //     for (int i = 0; i < table.getColumnCount(); i++) {
    //         table.getColumnModel().getColumn(i).setCellRenderer(renderer);
    //     }
    // }

    private void performSearch() {
        String text = searchField.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    private void filterByDate() {
        String date = dateField.getText().trim();
        if (!date.isEmpty()) {
            // A better approach would be to add a method in HistoryLogDAO to filter by date in SQL
            // But for now, we'll filter in the UI layer
            loadHistory();
        } else {
            loadHistory();
        }
    }

    private void refreshData() {
        loadHistory();
    }
    
    private void clearLogs() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Anda yakin ingin menghapus semua riwayat log?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = historyLogController.clearLogs();
            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "Semua riwayat log berhasil dihapus",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshData();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menghapus riwayat log",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadHistory() {
        tableModel.setRowCount(0);
        List<HistoryLog> logs = historyLogController.getAllLogs();
        for (HistoryLog log : logs) {
            Object[] rowData = {
                log.getIdLog(),
                log.getTimestamp(),
                log.getAction(),
                log.getUsername(),
            };
            tableModel.addRow(rowData);
        }
        
    }
}
