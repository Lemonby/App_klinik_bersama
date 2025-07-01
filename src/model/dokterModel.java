package model  ;

import model.source.Dokter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DokterModel {

    private Connection conn;

    public DokterModel(Connection connection) {
        this.conn = connection;
        }

    public boolean tambahDokter(Dokter dokter) {
        String sql = "INSERT INTO tb_dokter (nip, nama_dokter, kelamin, no_telp, spesialis_dokter) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dokter.getNip());
            stmt.setString(2, dokter.getNama());
            stmt.setString(3, dokter.getKelamin());
            stmt.setString(4, dokter.getNoTelp());
            stmt.setString(5, dokter.getSpesialis());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDokter(Dokter dokter) {
        String sql = "UPDATE tb_dokter SET nama_dokter=?, kelamin=?, no_telp=?, spesialis_dokter=? WHERE nip=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dokter.getNip());
            stmt.setString(2, dokter.getNama());
            stmt.setString(3, dokter.getKelamin());
            stmt.setString(4, dokter.getNoTelp());
            stmt.setString(5, dokter.getSpesialis());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusDokter(int idDokter) {
        String sql = "DELETE FROM tb_dokter WHERE nip = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDokter);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Dokter getDokterById(int idDokter) {
        String sql = "SELECT * FROM tb_dokter WHERE nip = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDokter);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return parseResultSet(rs);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Dokter> getAllDokter() {
        List<Dokter> daftarDokter = new ArrayList<>();
        String sql = "SELECT * FROM tb_dokter";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarDokter.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarDokter;
    }

    private Dokter parseResultSet(ResultSet rs) throws SQLException {
        return new Dokter(
                rs.getString("nip"),
                rs.getString("nama_dokter"),
                rs.getString("kelamin"),
                rs.getString("no_telp"),
                rs.getString("spesialis_dokter")
        );
    }
}
