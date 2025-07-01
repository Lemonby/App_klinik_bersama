package controller;

import model.source.Pasien;
import model.PasienModel;
import util.DBConnection;
import util.Validator;

import java.sql.Connection;
import java.util.List;

public class PasienController {
    private final PasienModel pasienmodel;

    public PasienController() {
        Connection conn = DBConnection.getConnection();
        this.pasienmodel = new PasienModel(conn);
    }

    public boolean tambahPasien(Pasien pasien) {
        if (pasien == null) {
            System.out.println("Pasien tidak boleh null");
            return false;
        }

        if (!Validator.isValidNIK(pasien.getNik())) {
            System.out.println("NIK tidak valid");
            return false;
        }

        if (!Validator.isNotName(pasien.getNama())) {
            System.out.println("Nama tidak valid");
            return false;
        }

        if (!Validator.isValidGender(pasien.getKelamin())) {
            System.out.println("Jenis kelamin harus 'L' atau 'P'");
            return false;
        }
        
        if (!Validator.isValidPhone(pasien.getNoTelp())) {
            System.out.println("Nomor telepon tidak valid");
            return false;
        }
        
        if (!Validator.isNotEmpty(pasien.getAlamat())) {
            System.out.println("Alamat tidak boleh kosong");
            return false;
        }

        return pasienmodel.tambahPasien(pasien);
    }

    public boolean updatePasien(Pasien pasien) {
        if (pasien == null) {
            System.out.println("Pasien tidak boleh null");
            return false;
        }
        if (!Validator.isValidNIK(pasien.getNik())) {
            System.out.println("NIK tidak valid");
            return false;
        }
        if (!Validator.isNotName(pasien.getNama())) {
            System.out.println("Nama tidak valid");
            return false;
        }
        if (!Validator.isValidGender(pasien.getKelamin())) {
            System.out.println("Jenis kelamin harus 'L' atau 'P'");
            return false;
        }
        if (!Validator.isValidPhone(pasien.getNoTelp())) {
            System.out.println("Nomor telepon tidak valid");
            return false;
        }
        if (!Validator.isNotEmpty(pasien.getAlamat())) {
            System.out.println("Alamat tidak boleh kosong");
            return false;
        }

        return pasienmodel.updatePasien(pasien);
    }

    public boolean hapusPasien(int idPasien) {
        if (idPasien <= 0) {
            System.out.println("ID pasien tidak valid");
            return false;
        } 
        return pasienmodel.hapusPasien(idPasien);
    }

    public Pasien getPasienById(int idPasien) {
        if (idPasien <= 0) {
            System.out.println("ID pasien tidak valid");
            return null;
        }
        return pasienmodel.getPasienById(idPasien);
    }

    public List<Pasien> getallPasien() {
        return pasienmodel.getAllPasien();
    }
}
