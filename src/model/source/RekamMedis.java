package model.source;

import java.util.Date;

import util.DateUtil;
import util.Validator;

public class RekamMedis {
    private int idRekam;
    private int idPasien;
    private int idDokter;
    private Date tanggal;
    private String diagnosa;

    public RekamMedis(int idRekam, int idPasien , int idDokter, Date tanggal, String diagnosa) {
        setIdRekam(idRekam);
        setIdPasien(idPasien);
        setIdDokter(idDokter);  
        setTanggal(tanggal);
        setDiagnosa(diagnosa);
    }

    public int getIdRekam() { return idRekam; }
    public void setIdRekam(int idRekam) { this.idRekam = idRekam; }

    public int getIdPasien() { return idPasien; }
    public void setIdPasien(int idPasien) { this.idPasien = idPasien; }

    public int getIdDokter() { return idDokter; }
    public void setIdDokter(int idDokter) { this.idDokter = idDokter; }

    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { 
        if (tanggal == null){
            throw new IllegalArgumentException("Tanggal tidak boleh kosong");
        }
        this.tanggal = tanggal;
    }
    public String getTanggalFormatted() { return DateUtil.Format(tanggal); }

    public String getDiagnosa() { return diagnosa; }
    public void setDiagnosa(String diagnosa) { 
        if (!Validator.isNotEmpty(diagnosa)){
            throw new IllegalArgumentException("Diagnosa tidak boleh kosong");
        }
        this.diagnosa = diagnosa;
    }
}