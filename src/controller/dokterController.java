package controller;

import model.source.Dokter;
import model.dokterModel;
import model.source.JadwalDokter;
import model.jadwalDokterModel;
import util.DBConnection;
import util.Validator;

import java.sql.Connection;
import java.util.List;

public class dokterController {
    private final dokterModel dokterModel;
    private final jadwalDokterModel jadwalDokterModel;
    // private final JadwalDokter jadwalDokter;

    public dokterController() {
        Connection connection = DBConnection.getConnection();
        this.dokterModel = new dokterModel(connection);
        this.jadwalDokterModel = new jadwalDokterModel(connection);
        // this.jadwalDokter = new JadwalDokter();
    }

    // Tambah dokter ke database
    public boolean tambahDokter(Dokter dokter) {
        if (dokter == null) {
            System.out.println("Data dokter tidak boleh null.");
            return false;
        }
        if (!Validator.isNotName(dokter.getNama())) {
            System.out.println("Nama dokter tidak boleh kosong.");
            return false;
        }
        if (!Validator.isValidGender(dokter.getKelamin())) {
            System.out.println("Jenis Kelamin tidak sesuai.");
            return false;
        }
        if (!Validator.isNotEmpty(dokter.getSpesialis())) {
            System.out.println("Spesialisasi tidak boleh kosong.");
            return false;
        }
        if (!Validator.isValidPhone(dokter.getNoTelp())) {
            System.out.println("Nomor telepon tidak valid.");
            return false;
        }
        return dokterModel.tambahDokter(dokter);
    }

    // Ubah data dokter
    public boolean updateDokter(Dokter dokter) {
        if (dokter == null) {
            System.out.println("Data dokter tidak boleh null.");
            return false;
        }
        if (!Validator.isNotName(dokter.getNama())) {
            System.out.println("Nama dokter tidak boleh kosong.");
            return false;
        }
        if (!Validator.isValidGender(dokter.getKelamin())) {
            System.out.println("Jenis Kelamin tidak sesuai.");
            return false;
        }
        if (!Validator.isNotEmpty(dokter.getSpesialis())) {
            System.out.println("Spesialisasi tidak boleh kosong.");
            return false;
        }
        if (!Validator.isValidPhone(dokter.getNoTelp())) {
            System.out.println("Nomor telepon tidak valid.");
            return false;
        }
        return dokterModel.updateDokter(dokter);
    }

    // Hapus dokter berdasarkan NIP (id)
    public boolean hapusDokter(String nip) {
        if (nip == null || nip.isEmpty()) {
            System.out.println("NIP dokter tidak valid.");
            return false;
        }
        try {
            int nipInt = Integer.parseInt(nip);
            return dokterModel.hapusDokter(nipInt);
        } catch (NumberFormatException e) {
            System.out.println("Format NIP tidak valid.");
            return false;
        }
    }

    // Ambil semua dokter
    public List<Dokter> getAllDokter() {
        return dokterModel.getAllDokter();
    }

    // Ambil dokter berdasarkan NIP
    public Dokter getDokterByNip(String nip) {
        try {
            int nipInt = Integer.parseInt(nip);
            return dokterModel.getDokterById(nipInt);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // ===================== JADWAL DOKTER =====================

    public boolean createSchedule (JadwalDokter schedule) {
        if (schedule == null) {
            System.out.println("Data jadwal tidak boleh null.");
            return false;
        }
        if (!Validator.isNotName(schedule.getNama())) {
            System.out.println("Nama dokter tidak boleh kosong.");
            return false;
        }
        if (!Validator.isValidNIP(schedule.getNip())) {
            System.out.println("NIP dokter tidak valid.");
            return false;
        }
        if (!Validator.isNotEmpty(schedule.getSpesialis())) {
            System.out.println("Spesialisasi tidak boleh kosong.");
            return false;
        }
        if (!Validator.isNotEmpty(schedule.getHari())) {
            System.out.println("Hari tidak boleh kosong.");
            return false;
        }
        if (!Validator.isNotEmpty(schedule.getJam())) {
            System.out.println("Jam tidak boleh kosong.");
            return false;
        }
        try {
            return jadwalDokterModel.createSchedule(schedule);
        } catch (Exception e) {
            System.out.println("Gagal membuat jadwal: " + e.getMessage());
            return false;
        }
    }

    // Ambil semua jadwal dokter
    public List<JadwalDokter> getAllJadwalDokter() {
        try {
            return jadwalDokterModel.getAllSchedules();
        } catch (Exception e) {
            System.out.println("Gagal mengambil jadwal: " + e.getMessage());
            return null;
        }
    }

    public boolean updateSchedule(JadwalDokter schedule) {
        if (schedule == null) {
            System.out.println("Data jadwal tidak boleh null.");
            return false;
        }
        if (!Validator.isNotEmpty(schedule.getHari())) {
            System.out.println("Hari tidak boleh kosong.");
            return false;
        }
        if (!Validator.isNotEmpty(schedule.getJam())) {
            System.out.println("Jam tidak boleh kosong.");
            return false;
        }
        if(!Validator.isValidNIP(schedule.getNip())) {
            System.out.println("NIP dokter tidak valid.");
            return false;
        }
        try {
            return jadwalDokterModel.updateSchedule(schedule);
        } catch (Exception e) {
            System.out.println("Gagal memperbarui jadwal: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteSchedule(String nip) {
        if (nip == null || nip.isEmpty()) {
            System.out.println("NIP dokter tidak valid.");
            return false;
        }
        try {
            return jadwalDokterModel.deleteSchedule(nip);
        } catch (Exception e) {
            System.out.println("Gagal menghapus jadwal: " + e.getMessage());
            return false;
        }
    }
    // // Load semua jadwal dokter (dari file atau DB)
    // public List<JadwalDokter> getAllJadwalDokter() {
    //     jadwalDokter.loadData();
    //     return jadwalDokter.getAllSchedules();
    // }

    // // Update jadwal dokter pada index tertentu
    // public void updateJadwalDokter(int index, String hari, String jam) {
    //     jadwalDokter.updateSchedule(index, hari, jam);
    //     jadwalDokter.saveToFile();
    // }

    // // Simpan semua jadwal ke file
    // public boolean simpanJadwalKeFile() {
    //     return jadwalDokter.saveToFile();
    // }
}
