package model.source;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import util.Validator;

public class Pasien {
    private int idPasien;
    private String nik;
    private String nama;
    private String kelamin;
    private String tglLahir;
    private String noTelp;
    private String alamat;
    private LocalDate birthDate; // Add this field

    public Pasien(int idPasien, String nik, String nama, String alamat, String kelamin, String noTelp, String tglLahir) {
        setIdPasien(idPasien);
        setNik(nik);
        setKelamin(kelamin);
        setNama(nama);
        setTanggalLahir(tglLahir);
        setNoTelp(noTelp);
        setAlamat(alamat);
    }

    public void setIdPasien(int idPasien) { this.idPasien = idPasien; }
    public int getIdPasien() { return idPasien; }

    public void setNik(String nik) {
        if (!Validator.isValidNIK(nik)){
            throw new IllegalArgumentException("NIK harus terdiri dari 16 digit angka");
        }
        this.nik = nik;
    }
    public String getNik() { return nik; }

    public void setNama(String nama) {
        if (!Validator.isNotName(nama)){
            throw new IllegalArgumentException("Nama hanya berisi huruf dan spasi");
        }
        this.nama = nama;
    }
    public String getNama() { return nama; }

    public void setKelamin(String kelamin) {
        if (!Validator.isValidGender(kelamin)){
            throw new IllegalArgumentException("Jenis kelamin harus 'L' atau 'P'");
        }
        this.kelamin = kelamin;
    }
    public String getKelamin() { return kelamin; }

    public void setTanggalLahir(String tglLahir) {
        try {
            this.birthDate = LocalDate.parse(tglLahir);
            this.tglLahir = tglLahir;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use yyyy-MM-dd.");
            this.birthDate = null;
            this.tglLahir = null;
        }
    }
    public String getTanggalLahirFormatted() { 
        return tglLahir; 
    }
    public LocalDate getTanggalLahir() { 
        return birthDate; 
    }

    public void setNoTelp(String noTelp) { 
        if (!Validator.isValidPhone(noTelp)){
            throw new IllegalArgumentException("Nomor Telepon hanya berisi 10-15 digit angka");
        }
        this.noTelp = noTelp;
    }
    public String getNoTelp() { return noTelp; }

    public void setAlamat(String alamat) { 
        if (!Validator.isNotEmpty(alamat)){
            throw new IllegalArgumentException("Alamat tidak boleh kosong");
        }
        this.alamat = alamat;
    }
    public String getAlamat() { return alamat; }
}
