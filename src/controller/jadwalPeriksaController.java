package controller;

import model.source.JadwalPeriksa;
import model.jadwalPeriksaModel;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class jadwalPeriksaController {
    private final jadwalPeriksaModel periksaModel;

    public jadwalPeriksaController() {
        Connection connection = DBConnection.getConnection();
        this.periksaModel = new jadwalPeriksaModel(connection);
    }

    public boolean tambahJadwalPeriksa(JadwalPeriksa jadwal) {
        if (jadwal == null) {
            System.out.println("Data jadwal tidak boleh null.");
            return false;
        }
        if (jadwal.getIdDokter() <= 0) {
            System.out.println("ID Dokter tidak valid.");
            return false;
        }
        if (jadwal.getHari() == null || jadwal.getHari().isEmpty()) {
            System.out.println("Hari tidak boleh kosong.");
            return false;
        }
        if (jadwal.getJamMulai() == null || jadwal.getJamMulai().isEmpty()) {
            System.out.println("Jam mulai tidak boleh kosong.");
            return false;
        }
        if (jadwal.getJamSelesai() == null || jadwal.getJamSelesai().isEmpty()) {
            System.out.println("Jam selesai tidak boleh kosong.");
            return false;
        }
        return periksaModel.tambahJadwalPeriksa(jadwal);
    }

    public boolean updateJadwalPeriksa(JadwalPeriksa jadwal) {
        if (jadwal == null) {
            System.out.println("Data jadwal tidak boleh null.");
            return false;
        }
        if (jadwal.getIdJadwal() <= 0) {
            System.out.println("ID Jadwal tidak valid.");
            return false;
        }
        if (jadwal.getIdDokter() <= 0) {
            System.out.println("ID Dokter tidak valid.");
            return false;
        }
        if (jadwal.getHari() == null || jadwal.getHari().isEmpty()) {
            System.out.println("Hari tidak boleh kosong.");
            return false;
        }
        if (jadwal.getJamMulai() == null || jadwal.getJamMulai().isEmpty()) {
            System.out.println("Jam mulai tidak boleh kosong.");
            return false;
        }
        if (jadwal.getJamSelesai() == null || jadwal.getJamSelesai().isEmpty()) {
            System.out.println("Jam selesai tidak boleh kosong.");
            return false;
        }
        return periksaModel.updateJadwalPeriksa(jadwal);
    }

    public boolean hapusJadwalPeriksa(int id) {
        if (id <= 0) {
            System.out.println("ID Jadwal tidak valid.");
            return false;
        }
        return periksaModel.hapusJadwalPeriksa(id);
    }

    public JadwalPeriksa getJadwalPeriksaById(int id) {
        if (id <= 0) {
            System.out.println("ID Jadwal tidak valid.");
            return null;
        }
        return periksaModel.getJadwalPeriksaById(id);
    }

    public List<JadwalPeriksa> getAllJadwalPeriksa() {
        return periksaModel.getAllJadwalPeriksa();
    }   
}
