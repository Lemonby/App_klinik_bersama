package model;

import model.source.JadwalPeriksa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JadwalPeriksaModel {

    private Connection conn;

    public JadwalPeriksaModel(Connection connection) {
        this.conn = connection;
    }

    public boolean tambahJadwalPeriksa(JadwalPeriksa jadwal) {
        String sql = "INSERT INTO tb_jadwal_periksa (id_dokter, hari, jam_mulai, jam_selesai) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jadwal.getIdDokter());
            stmt.setString(2, jadwal.getHari());
            stmt.setString(3, jadwal.getJamMulai());
            stmt.setString(4, jadwal.getJamSelesai());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateJadwalPeriksa(JadwalPeriksa jadwal) {
        String sql = "UPDATE tb_jadwal_periksa SET id_dokter=?, hari=?, jam_mulai=?, jam_selesai=? WHERE id_jadwal=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jadwal.getIdDokter());
            stmt.setString(2, jadwal.getHari());
            stmt.setString(3, jadwal.getJamMulai());
            stmt.setString(4, jadwal.getJamSelesai());
            stmt.setInt(5, jadwal.getIdJadwal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusJadwalPeriksa(int idJadwal) {
        String sql = "DELETE FROM tb_jadwal_periksa WHERE id_jadwal=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJadwal);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public JadwalPeriksa getJadwalPeriksaById(int idJadwal) {
        String sql = "SELECT * FROM tb_jadwal_periksa WHERE id_jadwal=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJadwal);
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

    public List<JadwalPeriksa> getAllJadwalPeriksa() {
        List<JadwalPeriksa> daftarJadwalPeriksa = new ArrayList<>();
        String sql = "SELECT * FROM tb_jadwal_periksa";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarJadwalPeriksa.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarJadwalPeriksa;
    }

    private JadwalPeriksa parseResultSet(ResultSet rs) throws SQLException {
        return new JadwalPeriksa(
                rs.getInt("id_jadwal"),
                rs.getInt("id_dokter"),
                rs.getString("hari"),
                rs.getString("jam_mulai"),
                rs.getString("jam_selesai")
        );
    }
}
