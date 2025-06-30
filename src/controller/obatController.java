package controller;

import model.obatModel;
import model.source.Obat;
import util.DBConnection;
import util.Validator;

import java.sql.Connection;
import java.util.List;

public class obatController {
    private final obatModel obatmodel;
    
    public obatController() {
        Connection conn = DBConnection.getConnection();
        this.obatmodel = new obatModel(conn);
    }

    public boolean tambahObat(Obat obat) {
        if (obat == null) {
            System.out.println("Obat tidak boleh null");
            return false;
        }
        if (!Validator.isValidObat(obat.getNamaObat())) {
            System.out.println("Nama obat tidak valid");
            return false;
        }
        if (obat.getStok() < 0) {
            System.out.println("Stok obat tidak boleh negatif");
            return false;
        }
        if (obat.getHarga() < 0) {
            System.out.println("Harga obat tidak boleh negatif");
            return false;
        }

        return obatmodel.tambahObat(obat);
    }

    public boolean updateObat(Obat obat) {
        if (obat == null) {
            System.out.println("Obat tidak boleh null");
            return false;
        }

        if (!Validator.isValidObat(obat.getNamaObat())) {
            System.out.println("Nama obat tidak valid");
            return false;
        }

        if (obat.getStok() < 0) {
            System.out.println("Stok obat tidak boleh minus");
            return false;
        }
        
        if (obat.getHarga() < 0) {
            System.out.println("Harga obat tidak boleh minus");
            return false;
        }

        return obatmodel.updateObat(obat);
    }

    public boolean hapusObat(int idObat) {
        if (idObat <= 0) {
            System.out.println("ID obat tidak valid");
            return false;
        }
        return obatmodel.hapusObat(idObat);
    }

    public Obat getObatById(int idObat) {
        if (idObat <= 0) {
            System.out.println("ID obat tidak valid");
            return null;
        }
        return obatmodel.getObatById(idObat);
    }

    public List<Obat> getAllObat() {
        return obatmodel.getAllObat();
    }
}