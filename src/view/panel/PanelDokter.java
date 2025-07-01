package view.panel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.source.Dokter;
import model.source.DokterSchedule;
import model.source.JadwalDokter;
import util.ButtonFactory;
import controller.DokterController;
import util.RoundedPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// import java.io.*;
import java.util.List;

public class PanelDokter extends JPanel {

    private DefaultTableModel dokterTableModel;
    private DefaultTableModel jadwalTableModel;
    private JTable dokterTable;
    private JTable jadwalTable;
    private TableRowSorter<DefaultTableModel> dokterSorter;
    private TableRowSorter<DefaultTableModel> jadwalSorter;
    private ButtonFactory btn = new ButtonFactory();
    // private File file = new File("jadwalDokter.txt");
    private DokterController controller = new DokterController();
    private List<Dokter> daftarDokter = controller.getAllDokter();
    private List<DokterSchedule> jadwal = controller.getAllJadwalDokter();

    public PanelDokter() {
        setSize(900, 550);

        // Panel utama dengan GridLayout 2 baris
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        mainPanel.setBackground(Color.WHITE);

        // --- Tabel Data Dokter ---
        String[] dokterColumns = {"Nama Dokter", "NIP", "Spesialis"};
        dokterTableModel = new DefaultTableModel(dokterColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dokterTable = new JTable(dokterTableModel);
        dokterSorter = new TableRowSorter<>(dokterTableModel);
        dokterTable.setRowSorter(dokterSorter);

        JScrollPane dokterScrollPane = new JScrollPane(dokterTable);
        JPanel dokterPanel = new RoundedPanel(Color.WHITE); 
        dokterPanel.setLayout(new BorderLayout());
        dokterPanel.setBorder(BorderFactory.createTitledBorder("Data Dokter"));
        dokterPanel.add(dokterScrollPane, BorderLayout.CENTER);

        // --- Tabel Jadwal Dokter ---
        String[] jadwalColumns = {"Nama Dokter", "Hari", "Jam"};
        jadwalTableModel = new DefaultTableModel(jadwalColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jadwalTable = new JTable(jadwalTableModel);
        jadwalSorter = new TableRowSorter<>(jadwalTableModel);
        jadwalTable.setRowSorter(jadwalSorter);

        JScrollPane jadwalScrollPane = new JScrollPane(jadwalTable);
        JPanel jadwalPanel = new RoundedPanel(Color.WHITE); 
        jadwalPanel.setLayout(new BorderLayout());
        jadwalPanel.setBorder(BorderFactory.createTitledBorder("Jadwal Dokter"));
        jadwalPanel.add(jadwalScrollPane, BorderLayout.CENTER);

        // Tambahkan kedua panel ke mainPanel
        mainPanel.add(dokterPanel);
        mainPanel.add(jadwalPanel);

        // Panel atas untuk search dan tombol
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);

        JLabel searchLabel = new JLabel("Cari Nama Dokter:");
        JTextField searchField = new JTextField(20);

        topPanel.add(searchLabel);
        topPanel.add(searchField);

        JButton refreshButton = btn.createRoundedButton("Refresh Data", Color.WHITE);

        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Event search untuk kedua tabel
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText();
                RowFilter<DefaultTableModel, Object> rf = null;
                if (!searchText.trim().isEmpty()) {
                    rf = RowFilter.regexFilter("(?i)" + searchText);
                }
                dokterSorter.setRowFilter(rf);
                jadwalSorter.setRowFilter(rf);
            }
        });

        refreshButton.addActionListener(e -> reloadTables());

        reloadTables();
        setVisible(true);
    }

    public void reloadTables() {

        // Isi tabel dokter
        dokterTableModel.setRowCount(0);
        for (Dokter d : daftarDokter) {
            dokterTableModel.addRow(new Object[]{d.getNama(), d.getNip(), d.getSpesialis()});
        }

        // Isi tabel jadwal
        jadwalTableModel.setRowCount(0);
        for (DokterSchedule js : jadwal) {
            jadwalTableModel.addRow(new Object[]{js.getNama(), js.getHari(), js.getJam()});
        }
    }
}
