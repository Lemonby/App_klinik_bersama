package view.panel;

import javax.swing.*;
// import model.Dokter;
import util.ButtonFactory;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import view.panel.PanelTambahDokter;

public class PanelTambahDokter extends JPanel {
    private ButtonFactory btn = new ButtonFactory();

    public PanelTambahDokter() {
        setLayout(null);
        setBackground(Color.WHITE);

        JTextField namaField = new JTextField();
        namaField.setBounds(100, 50, 300, 40);
        namaField.setBorder(BorderFactory.createTitledBorder("Nama Dokter"));
        add(namaField);

        JTextField nipField = new JTextField();
        nipField.setBounds(100, 120, 300, 40);
        nipField.setBorder(BorderFactory.createTitledBorder("NIP Dokter"));
        add(nipField);

        JTextField spesialisField = new JTextField();
        spesialisField.setBounds(100, 190, 300, 40);
        spesialisField.setBorder(BorderFactory.createTitledBorder("Spesialis Dokter"));
        add(spesialisField);

        JButton tambahButton = btn.createRoundedButton("Tambah Dokter", Color.WHITE);
        tambahButton.setBounds(100, 270, 300, 45);
        add(tambahButton);

        tambahButton.addActionListener(e -> {
            String nama = namaField.getText().trim();
            String nip = nipField.getText().trim();
            String spesialis = spesialisField.getText().trim();

            if (nama.isEmpty() || nip.isEmpty() || spesialis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nip.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "NIP hanya boleh angka!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tambahDokterKeFile(nama, nip, spesialis);

            JOptionPane.showMessageDialog(this, "Dokter baru berhasil ditambahkan!");
            namaField.setText("");
            nipField.setText("");
            spesialisField.setText("");
        });
    }

    private void tambahDokterKeFile(String nama, String nip, String spesialis) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dokterBaru.txt", true))) {
            writer.write(nama + "," + nip + "," + spesialis);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}