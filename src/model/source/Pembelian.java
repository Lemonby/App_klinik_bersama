    package model.source;

    import java.util.Date;

    import util.DateUtil;

    public class Pembelian {
        private int idPembelian;
        private int idPasien;
        private int idObat;
        private int jumlahObat;
        private double totalHarga;
        private Date tanggalPembelian;
        
        public Pembelian(int idPembelian, int idPasien, int idObat, int jumlahObat, double totalHarga, Date tanggalPembelian) {
            setIdPembelian(idPembelian);
            setIdPasien(idPasien);
            setIdObat(idObat);
            setJumlahObat(jumlahObat);
            setTanggalPembelian(tanggalPembelian);
            setTotalHarga(totalHarga);
        }

        public int getIdPembelian() { return idPembelian; }
        public void setIdPembelian(int idPembelian) { this.idPembelian = idPembelian; }

        public int getIdPasien() { return idPasien; }
        public void setIdPasien(int idPasien) { this.idPasien = idPasien; }
        
        public int getIdObat() { return idObat; }
        public void setIdObat(int idObat) { this.idObat = idObat; }

        public int getJumlahObat() { return jumlahObat; }
        public void setJumlahObat(int jumlahObat) {   
            if (jumlahObat < 0){
                throw new IllegalArgumentException("Jumlah Obat tidak boleh negatif");
            }
            this.jumlahObat = jumlahObat;
        }

        public double getTotalHarga() { return totalHarga; }
        public void setTotalHarga(double totalHarga) {   
            if (totalHarga < 0){
                throw new IllegalArgumentException("Total harga tidak boleh negatif");
            }
            this.totalHarga = totalHarga;
        }

        public Date getTanggalPembelian() { return tanggalPembelian; }
        public void setTanggalPembelian(Date tanggalPembelian) {  
            if (tanggalPembelian == null){
                throw new IllegalArgumentException("Tanggal Pembelian tidak boleh kosong");
            }
            Date today = new Date();
            if (tanggalPembelian.after(today)) {
                throw new IllegalArgumentException("Tanggal Pembelian tidak boleh di masa depan");
            }
            this.tanggalPembelian = tanggalPembelian;
        }
        public String getTanggalPembelianFormatted() { return DateUtil.Format(tanggalPembelian); }
    }
