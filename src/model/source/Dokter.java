package model.source;

import java.util.ArrayList;
import java.util.List;

import util.Validator;
// import java.util.List;
// import java.util.ArrayList;

public class Dokter {
    private String nip;
    private String nama;
    private String noTelp;
    private String kelamin;
    private String spesialis;

    public Dokter(String nip, String nama, String notelp, String kelamin, String spesialis) {
        this.nip = nip;
        this.nama = nama;
        this.noTelp = notelp;
        this.kelamin = kelamin;
        this.spesialis = spesialis;
    }

    public void setNip(String nip) {
        if (!Validator.isValidNIP(nip)){
            throw new IllegalArgumentException("NIP harus berisi 18 digit angka");
        }
        this.nip = nip;
    }
    public String getNip() { return nip; }

    public void setNama(String nama) {
        if(!Validator.isNotEmpty(nama)) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        this.nama = nama;
    }
    public String getNama() { return nama; }

    public void setSpesialis(String spesialis) {  
        if (!Validator.isNotEmpty(spesialis)){
            throw new IllegalArgumentException("Spesialis tidak boleh kosong");
        }
        this.spesialis = spesialis;
    }
    public String getSpesialis() { return spesialis; }

    public void setKelamin(String kelamin) {
        if (!Validator.isValidGender(kelamin)){
            throw new IllegalArgumentException("Jenis kelamin harus 'L' atau 'P'");
        }
        this.kelamin = kelamin;
    }
    public String getKelamin() { return kelamin; }

    public void setNoTelp(String noTelp) {  
        if (!Validator.isValidPhone(noTelp)){
            throw new IllegalArgumentException("Nomor Telepon hanya berisi 10-15 digit angka");
        }
        this.noTelp = noTelp;
    } 
    public String getNoTelp() { return noTelp; }

    public List<DokterSchedule> getSchedules(JadwalDokter model) {
        List<DokterSchedule> allSchedules = model.getAllSchedules();
        List<DokterSchedule> mySchedules = new ArrayList<>();
        for (DokterSchedule schedule : allSchedules) {
            if (schedule.getNip().equals(this.nip)) {
                mySchedules.add(schedule);
            }
        }
        return mySchedules;
    }
}
