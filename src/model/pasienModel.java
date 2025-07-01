package model;

import model.source.Pasien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasienModel {
    private Connection conn;

    public PasienModel(Connection connection) {
        this.conn = connection;
    }

    public boolean tambahPasien(Pasien pasien) {
        String sql = "INSERT INTO tb_pasien (nik, nama, alamat, kelamin, no_telp, tgl_lahir) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pasien.getNik());
            stmt.setString(2, pasien.getNama());
            stmt.setString(3, pasien.getAlamat());
            stmt.setString(4, pasien.getKelamin());
            stmt.setString(5, pasien.getNoTelp());
            stmt.setDate(6, java.sql.Date.valueOf(pasien.getTanggalLahir()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePasien(Pasien pasien) {
        String sql = "UPDATE tb_pasien SET nik = ?, nama = ?, alamat = ?, kelamin = ?, no_telp = ?, tgl_lahir = ? WHERE id_pasien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pasien.getNik());
            stmt.setString(2, pasien.getNama());
            stmt.setString(3, pasien.getAlamat());
            stmt.setString(4, pasien.getKelamin());
            stmt.setString(5, pasien.getNoTelp());
            stmt.setDate(6, java.sql.Date.valueOf(pasien.getTanggalLahir()));
            stmt.setInt(7, pasien.getIdPasien());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusPasien(int idPasien) {
        String sql = "DELETE FROM tb_pasien WHERE id_pasien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPasien);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pasien getPasienById(int idPasien) {
        String sql = "SELECT * FROM tb_pasien WHERE id_pasien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPasien);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parseResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pasien> getAllPasien() {
        List<Pasien> daftarPasien = new ArrayList<>();
        String sql = "SELECT * FROM tb_pasien";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarPasien.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarPasien;
    }

    private Pasien parseResultSet(ResultSet rs) 
    throws SQLException {
        java.sql.Date sqlDate = rs.getDate("tgl_lahir");
        String tglLahirStr = (sqlDate != null) ? sqlDate.toString() : null;
        return new Pasien(
            rs.getInt("id_pasien"),
            rs.getString("nik"),
            rs.getString("nama"),
            rs.getString("alamat"),
            rs.getString("kelamin"),
            rs.getString("no_telp"),
            tglLahirStr
        );
    }
}