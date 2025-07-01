package model.source;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.DokterModel;
import util.DBConnection;

public class JadwalDokter {
    private List<DokterSchedule> dokterSchedules;
    private final DokterModel doktermodel;
    private final String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};

    public JadwalDokter() {
        Connection connection = DBConnection.getConnection();
        this.doktermodel = new DokterModel(connection);
        this.dokterSchedules = new ArrayList<>();
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        List<Dokter> allDokter = doktermodel.getAllDokter();
        for (Dokter d : allDokter) {
            if (d != null) {
                String nama = d.getNama();
                String nip = d.getNip();
                String spesialis = d.getSpesialis();
                String hari = this.hari[(int) (Math.random() * this.hari.length)];
                String jam = "08:00 - 14:00";
                DokterSchedule schedule = new DokterSchedule(nama, nip, spesialis, hari, jam);
                dokterSchedules.add(schedule);
            }
        }
    }

    public List<DokterSchedule> getAllSchedules() {
        return new ArrayList<>(dokterSchedules); // Return a copy to prevent external modification
    }

    public boolean updateSchedule(int index, String hari, String jam) {
        if (index >= 0 && index < dokterSchedules.size()) {
            DokterSchedule schedule = dokterSchedules.get(index);
            schedule.setHari(hari);
            schedule.setJam(jam);
            return true;
        }
        return false;
    }
    
    /**
     * Closes resources when object is no longer needed
     */
    public void close() {
    }
}