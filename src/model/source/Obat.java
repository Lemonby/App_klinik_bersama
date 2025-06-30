package model.source;

import util.Validator;

public class Obat {
    private int idObat;
    private String namaObat;
    private int stok;
    private double harga;

    public Obat(int idObat, String namaObat, int stok, double harga) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.stok = stok;
        this.harga = harga;
    }

    public void setIdObat(int idObat) { 
        this.idObat = idObat; 
    }
    public int getIdObat() { return idObat; }

    public void setNamaObat(String namaObat) {
        if (!Validator.isNotEmpty(namaObat)){
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (!Validator.isNotName(namaObat)){
            throw new IllegalArgumentException("Nama hanya berisi huruf dan spasi");
        }
        this.namaObat = namaObat;
    }
    public String getNamaObat() { return namaObat; }

    public void setStok(int stok) {  
        if (stok < 0){
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }
        this.stok = stok;
    }
    public int getStok() { return stok; }

    public void setHarga(double harga) { 
        if (harga < 0){
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }
        this.harga = harga;
    }
    public double getHarga() { return harga; }

    
}
