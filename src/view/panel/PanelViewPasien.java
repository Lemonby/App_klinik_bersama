package view.panel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import controller.PasienController;
import model.source.Pasien;

public class PanelViewPasien extends JPanel {
    
    private DefaultTableModel tableModel;
    private JTable table;
    private PasienController pasienController;

    public PanelViewPasien() {
        // Initialize the controller
        pasienController = new PasienController();
        
        // Initialize UI components
        initializeUI();
        
        // Load data into the table
        refreshData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        
        // Create table model with column names
        String[] columnNames = {"ID Pasien", "NIK", "Nama", "Alamat", "Kelamin", "No.Telp", "Tanggal Lahir"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // Create table and add it to a scroll pane
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add a refresh button
        JButton refreshButton = new JButton("Refresh Data");
        refreshButton.addActionListener(e -> refreshData());
        
        // Add components to the panel
        add(scrollPane, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
    }

    public void refreshData() {
        tableModel.setRowCount(0); // Clear existing data
        
        // Get all patients from the controller
        List<Pasien> daftarPasien = pasienController.getallPasien();
        
        // Add each patient to the table model
        for (Pasien pasien : daftarPasien) {
            Object[] rowData = {
                pasien.getIdPasien(),
                pasien.getNik(),
                pasien.getNama(),
                pasien.getAlamat(),
                pasien.getKelamin(),
                pasien.getNoTelp(),
                pasien.getTanggalLahirFormatted()
            };
            tableModel.addRow(rowData);
        }
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public PasienController getPasienController() {
        return pasienController;
    }
}