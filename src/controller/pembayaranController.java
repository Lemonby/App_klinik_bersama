package controller;

import model.source.Pembayaran;
import model.pembayaranModel;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class pembayaranController {
    private final pembayaranModel pembayaranmodel;

    public pembayaranController() {
        Connection conn = DBConnection.getConnection();
        this.pembayaranmodel = new pembayaranModel(conn);
    }

    public boolean tambahPembayaran(Pembayaran pembayaran) {
        if (pembayaran == null) {
            System.out.println("Pembayaran tidakboleh null");
            return false;
        }

        if (pembayaran.getIdRekam() <= 0) {
            System.out.println("ID Rekam harus lebih besar dari 0");
            return false;
        }

        if (pembayaran.getTotal() < 0) {
            System.out.println("Total pembayaran tidak boleh minus");
            return false;
        }

        if (pembayaran.getTanggal() == null) {
            System.out.println("Tanggal pembayaran tidak boleh kosong");
            return false;
        }

        return pembayaranmodel.tambahPembayaran(pembayaran);
    }

    public boolean updatePembayaran(Pembayaran pembayaran) {
        if (pembayaran == null || pembayaran.getIdPembayaran() <= 0) {
        System.out.println("Pembayaran tidak boleh null");
            return false;
        }

        if (pembayaran.getIdRekam() <= 0) {
            System.out.println("ID Rekam harus lebih besar dari 0");
            return false;
        }

        if (pembayaran.getTotal() < 0) {
            System.out.println("Total pembayaran tidak boleh minus");
            return false;
        }

        if (pembayaran.getTanggal() == null) {
            System.out.println("Tanggal pembayaran tidak boleh kosong");
            return false;
        }

        return pembayaranmodel.updatePembayaran(pembayaran);
    }

    public boolean hapusPembayaran(int idPembayaran) {
        if (idPembayaran <= 0) {
            System.out.println("ID Pembayaran harus lebih besar dari 0");
            return false;
        }
        return pembayaranmodel.hapusPembayaran(idPembayaran);
    }

    public Pembayaran getPembayaranById(int idPembayaran) {
        if (idPembayaran <= 0) {
            System.out.println("ID Pembayaran harus lebih besar dari 0");
            return null;
        }
        return pembayaranmodel.getPembayaranById(idPembayaran);
    }

    public List<Pembayaran> getAllPembayaran() {
        return pembayaranmodel.getAllPembayaran();
    }
}
