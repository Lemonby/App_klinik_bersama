package controller;

import model.source.Dokter;
import model.source.DokterSchedule;
import model.source.JadwalDokter;
import model.DokterModel;
import util.DBConnection;
import util.Validator;

import java.sql.Connection;
import java.util.List;

public class DokterController {
    private final DokterModel doktermodel;
    private final JadwalDokter jadwalDokter;

    public DokterController() {
        Connection connection = DBConnection.getConnection();
        this.doktermodel = new DokterModel(connection);
        this.jadwalDokter = new JadwalDokter();
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
        return doktermodel.tambahDokter(dokter);
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
        return doktermodel.updateDokter(dokter);
    }

    // Hapus dokter berdasarkan NIP (id)
    public boolean hapusDokter(String nip) {
        if (nip == null || nip.isEmpty()) {
            System.out.println("NIP dokter tidak valid.");
            return false;
        }
        try {
            int nipInt = Integer.parseInt(nip);
            return doktermodel.hapusDokter(nipInt);
        } catch (NumberFormatException e) {
            System.out.println("Format NIP tidak valid.");
            return false;
        }
    }

    // Ambil semua dokter
    public List<Dokter> getAllDokter() {
        return doktermodel.getAllDokter();
    }

    // Ambil dokter berdasarkan NIP
    public Dokter getDokterByNip(String nip) {
        try {
            int nipInt = Integer.parseInt(nip);
            return doktermodel.getDokterById(nipInt);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<DokterSchedule> getAllJadwalDokter() {
        return jadwalDokter.getAllSchedules();
    }

    // Update jadwal dokter pada index tertentu
    public void updateJadwalDokter(int index, String hari, String jam) {
        jadwalDokter.updateSchedule(index, hari, jam);
    }
}
