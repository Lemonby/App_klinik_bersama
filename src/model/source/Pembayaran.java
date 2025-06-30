package model.source;

import java.util.Date;

import util.DateUtil;

public class Pembayaran {
    private int idPembayaran;
    private int idRekam;
    private double total;
    private Date tanggal;

    public Pembayaran(int idPembayaran, int idRekam, double total, Date tanggal) {
        setIdPembayaran(idPembayaran);
        setIdRekam(idRekam);
        setTotal(total);
        setTanggal(tanggal);
    }

    public void setIdPembayaran(int idPembayaran) { this.idPembayaran = idPembayaran; }
    public int getIdPembayaran() { return idPembayaran; }

    public void setIdRekam(int idRekam) { this.idRekam = idRekam; }
    public int getIdRekam() { return idRekam; }

    public void setTotal(double total) {  
        if (total < 0){
            throw new IllegalArgumentException("Total harga tidak boleh negatif");
        }
        this.total = total;
    }
    public double getTotal() { return total; }

    public void setTanggal(Date tanggal) {   
        if (tanggal == null){
            throw new IllegalArgumentException("Tanggal Pembayaran tidak boleh kosong");
        }
        Date today = new Date();
        if (tanggal.after(today)) {
            throw new IllegalArgumentException("Tanggal Pembayaran tidak boleh di masa depan");
        }
        this.tanggal = tanggal;
    }
    public Date getTanggal() { return tanggal; }

    public String getTanggalFormatted() { return DateUtil.Format(tanggal); }
}