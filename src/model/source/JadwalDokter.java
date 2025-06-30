package model.source;

public class JadwalDokter {
    private String nama;
    private String nip;
    private String spesialis;
    private String hari;
    private String jam;

    public JadwalDokter(String nama, String nip, String spesialis, String hari, String jam) {
        this.nama = nama;
        this.nip = nip;
        this.spesialis = spesialis;
        this.hari = hari;
        this.jam = jam;
    }

    public String getNama() { return nama; }
    public String getNip() { return nip; }
    public String getSpesialis() { return spesialis; }
    public String getHari() { return hari; }
    public String getJam() { return jam; }

    public void setHari(String hari) { this.hari = hari; }
    public void setJam(String jam) { this.jam = jam; }
}