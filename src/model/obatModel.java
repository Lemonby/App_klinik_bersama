package model;

import model.source.Obat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObatModel {

    private Connection conn;

    public ObatModel(Connection connection) {
        this.conn = connection;
    }

    public boolean tambahObat(Obat obat) {
        String sql = "INSERT INTO tb_obat (id_obat, nama_obat, stok_obat, harga) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obat.getNamaObat());
            stmt.setInt(2, obat.getStok());
            stmt.setDouble(3, obat.getHarga());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateObat(Obat obat) {
        String sql = "UPDATE tb_obat SET nama_obat=?, stok_obat=?, harga=? WHERE id_obat=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obat.getNamaObat());
            stmt.setInt(2, obat.getStok());
            stmt.setDouble(3, obat.getHarga());
            stmt.setInt(4, obat.getIdObat());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusObat(int idObat) {
        String sql = "DELETE FROM tb_obat WHERE id_obat=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idObat);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Obat getObatById(int idObat) {
        String sql = "SELECT * FROM tb_obat WHERE id_obat=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idObat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return parseResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Obat> getAllObat() {
        List<Obat> daftarObat = new ArrayList<>();
        String sql = "SELECT * FROM tb_obat";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarObat.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarObat;
    }

    private Obat parseResultSet(ResultSet rs) throws SQLException {
        return new Obat(
                rs.getInt("id_obat"),
                rs.getString("nama_obat"),
                rs.getInt("stok_obat"),
                rs.getDouble("harga")
        );
    }
}
