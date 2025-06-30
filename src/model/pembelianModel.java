package model;

import model.source.Pembelian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class pembelianModel {

    private Connection conn;

    public pembelianModel(Connection connection) {
        this.conn = connection;
    }

    public boolean tambahPembelian(Pembelian pembelian) {
        String sql = "INSERT INTO tb_pembelian_obat (id_pasien, id_obat, jumlah_obat, total, tanggal_pembelian) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pembelian.getIdPasien());
            stmt.setInt(2, pembelian.getIdObat());
            stmt.setInt(3, pembelian.getJumlahObat());
            stmt.setDouble(4, pembelian.getTotalHarga());
            stmt.setDate(5, new java.sql.Date(pembelian.getTanggalPembelian().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePembelian(Pembelian pembelian) {
        String sql = "UPDATE tb_pembelian_obat SET id_pasien=?, id_obat=?, jumlah_obat=?, total=?, tanggal_pembelian=? WHERE id_pembelian=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pembelian.getIdPasien());
            stmt.setInt(2, pembelian.getIdObat());
            stmt.setInt(3, pembelian.getJumlahObat());
            stmt.setDouble(4, pembelian.getTotalHarga());
            stmt.setDate(5, new java.sql.Date(pembelian.getTanggalPembelian().getTime()));
            stmt.setInt(6, pembelian.getIdPembelian());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusPembelian(int idPembelian) {
        String sql = "DELETE FROM tb_pembelian_obat WHERE id_pembelian=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPembelian);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pembelian getPembelianObatById(int idPembelian) {
        String sql = "SELECT * FROM tb_pembelian_obat WHERE id_pembelian=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPembelian);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parseResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pembelian> getAllPembelianObat() {
        List<Pembelian> daftarPembelianObat = new ArrayList<>();
        String sql = "SELECT * FROM tb_pembelian_obat";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarPembelianObat.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarPembelianObat;
    }

    private Pembelian parseResultSet(ResultSet rs) throws SQLException {
        return new Pembelian(
                rs.getInt("id_pembelian"),
                rs.getInt("id_pasien"),
                rs.getInt("id_obat"),
                rs.getInt("jumlah_obat"),
                rs.getDouble("total"),
                rs.getDate("tanggal_pembelian")
        );
    }
}
