package model.source;

public class JadwalPeriksa {
    private int idJadwal;
    private int idDokter;
    private String hari;
    private String jamMulai;
    private String jamSelesai;

    public JadwalPeriksa(int idJadwal, int idDokter, String hari, String jamMulai, String jamSelesai) {
        setIdJadwal(idJadwal);
        setIdDokter(idDokter);
        setHari(hari);
        setJamMulai(jamMulai);
        setJamSelesai(jamSelesai);
    }

    public void setIdJadwal(int idJadwal) { 
        this.idJadwal = idJadwal; 
    }
    public int getIdJadwal() { return idJadwal; }

    public int getIdDokter() {
        return idDokter; 
    }
    public void setIdDokter(int idDokter) { this.idDokter = idDokter; }

    public void setHari(String hari) {
        this.hari = hari; 
    }
    public String getHari() { return hari; }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai; 
    }
    public String getJamMulai() { return jamMulai; }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai; 
    }
    public String getJamSelesai() { return jamSelesai; }
}
