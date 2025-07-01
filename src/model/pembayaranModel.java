package model;

import model.source.Pembayaran;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PembayaranModel {

    private Connection conn;

    public PembayaranModel(Connection connection) {
        this.conn = connection;
    }

    public boolean tambahPembayaran(Pembayaran pembayaran) {
        String sql = "INSERT INTO tb_pembayaran (id_rekam, total, tanggal) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pembayaran.getIdRekam());
            stmt.setDouble(2, pembayaran.getTotal());
            stmt.setDate(3, new java.sql.Date(pembayaran.getTanggal().getTime()));
            stmt.executeUpdate();   
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePembayaran(Pembayaran pembayaran) {
        String sql = "UPDATE tb_pembayaran SET id_rekam=?, total=?, tanggal=? WHERE id_pembayaran=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pembayaran.getIdRekam());
            stmt.setDouble(2, pembayaran.getTotal());
            stmt.setDate(3, new java.sql.Date(pembayaran.getTanggal().getTime()));
            stmt.setInt(4, pembayaran.getIdPembayaran());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusPembayaran(int idPembayaran) {
        String sql = "DELETE FROM tb_pembayaran WHERE id_pembayaran=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPembayaran);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pembayaran getPembayaranById(int idPembayaran) {
        String sql = "SELECT * FROM tb_pembayaran WHERE id_pembayaran=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPembayaran);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parseResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pembayaran> getAllPembayaran() {
        List<Pembayaran> daftaPembayaran = new ArrayList<>();
        String sql = "SELECT * FROM tb_pembayaran";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftaPembayaran.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftaPembayaran;
    }

    private Pembayaran parseResultSet(ResultSet rs) throws SQLException {
        return new Pembayaran(
                rs.getInt("id_pembayaran"),
                rs.getInt("id_rekam"),
                rs.getDouble("total"),
                rs.getDate("tanggal")
        );
    }
}
