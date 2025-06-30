package controller;

import model.source.Pembelian;
import model.pembelianModel;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class pembelianController {
    private final pembelianModel pembelianmodel;

    public pembelianController() {
        Connection conn = DBConnection.getConnection();
        this.pembelianmodel = new pembelianModel(conn);
    }

    public boolean tambahPembelian(Pembelian pembelian) {
        if (pembelian == null) {
            System.out.println("Pembelian tidak boleh null");
            return false;
        }

        if (pembelian.getIdPasien() <= 0) {
            System.out.println("ID tidak valid");
            return false;
        }

        if(pembelian.getIdObat() <= 0) {
            System.out.println("ID tidak valid");
            return false;
        }

        if(pembelian.getJumlahObat() <= 0) {
            System.out.println("Jumlah tidak boleh negatif atau nol");
            return false;
        }

        if(pembelian.getTotalHarga() < 0) {
            System.out.println("Total Harga tidak boleh negatif");
            return false;
        }

        if(pembelian.getTanggalPembelian() == null) {
            System.out.println("Tanggal Pembelian tidak boleh kosong");
            return false;
        }

        return pembelianmodel.tambahPembelian(pembelian);
    }

    public boolean updatePembelian(Pembelian pembelian) {
        if(pembelian == null || pembelian.getIdPembelian() <= 0) {
            System.out.println("Pembelian tidak boleh null");
            return false;
        }

        if(pembelian.getIdPasien() <= 0) {
            System.out.println("ID tidak valid");
            return false;
        }

        if(pembelian.getIdObat() <= 0) {
            System.out.println("ID tidak valid");
            return false;
        }

        if(pembelian.getJumlahObat() <= 0) {
            System.out.println("Jumlah tidak boleh negatif atau nol");
            return false;
        }

        if(pembelian.getTotalHarga() < 0) {
            System.out.println("Total Harga tidak boleh negatif");
            return false;
        }

        if(pembelian.getTanggalPembelian() == null) {
            System.out.println("Tanggal Pembelian tidak boleh kosong");
            return false;
        }

        return pembelianmodel.updatePembelian(pembelian);
    }

    public boolean hapusPembelian(int idPembelian) {
        if(idPembelian <= 0) {
            System.out.println("ID Pembelian tidak valid");
            return false;
        }
        return pembelianmodel.hapusPembelian(idPembelian);
    }

    public Pembelian getPembelianObatById(int idPembelian) {
        if(idPembelian <= 0) {
            System.out.println("ID Pembelian tidak valid");
            return null;
        }
        return pembelianmodel.getPembelianObatById(idPembelian);
    }

    public List<Pembelian> getAllPembelianObat() {
        return pembelianmodel.getAllPembelianObat();
    }
}
