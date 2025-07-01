package controller;

import model.source.RekamMedis;
import model.RekamMedisModel;
import util.DBConnection;
import util.Validator;

import java.sql.Connection;
import java.util.List;

public class RekamMedisController {
    private final RekamMedisModel rekamMedis;

    public RekamMedisController() {
        Connection conn = DBConnection.getConnection();
        this.rekamMedis = new RekamMedisModel(conn);
    }

    public boolean tambahRekamMedis(RekamMedis rekammedis) {
        if (rekammedis == null || rekammedis.getIdRekam() <= 0 ) {
            System.out.println("Rekam medis tidak boleh null");
        }
        if (rekammedis.getIdPasien() <= 0 || rekammedis.getIdDokter() <= 0) {
            System.out.println("ID Pasien dan Dokter harus valid");
        }
        if (rekammedis.getIdDokter() <= 0) {
            System.out.println("ID Dokter harus valid");
        }
        if (rekammedis.getTanggal() == null) {
            System.out.println("Tanggal tidak boleh kosong");
        }
        if (!Validator.isNotEmpty(rekammedis.getDiagnosa())) {
            System.out.println("Diagnosa tidak boleh kosong");
        }

        return rekamMedis.tambahRekamMedis(rekammedis);
    }

    public boolean updateRekamMedis(RekamMedis rekammedis) {
        if (rekammedis == null || rekammedis.getIdRekam() <= 0) {
            System.out.println("Rekam medis tidak boleh null");
        }
        if (rekammedis.getIdPasien() <= 0 || rekammedis.getIdDokter() <= 0) {
            System.out.println("ID Pasien dan Dokter harus valid");
        }
        if (rekammedis.getTanggal() == null) {
            System.out.println("Tanggal tidak boleh kosong");
        }
        if (!Validator.isNotEmpty(rekammedis.getDiagnosa())) {
            System.out.println("Diagnosa tidak boleh kosong");
        }

        return rekamMedis.updateRekamMedis(rekammedis);
    }

    public boolean hapusRekamMedis(int idRekam) {
        if (idRekam <= 0) {
            System.out.println("ID Rekam Medis harus valid");
            return false;
        }
        return rekamMedis.hapusRekamMedis(idRekam);
    }

    public RekamMedis getRekamMedisById(int idRekam) {
        if (idRekam <= 0) {
            System.out.println("ID Rekam Medis harus valid");
            return null;
        }
        return rekamMedis.getRekamMedisById(idRekam);
    }

    public List<RekamMedis> getAllRekamMedis() {
        return rekamMedis.getAllRekamMedis();
    }
}
