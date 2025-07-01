package model;

import model.source.RekamMedis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RekamMedisModel {

    private Connection conn;

    public RekamMedisModel(Connection connection) {
        this.conn = connection;
    }

    public boolean tambahRekamMedis(RekamMedis rekamMedis) {
        String sql = "INSERT INTO tb_rekam_medis (id_rekam, id_pasien, id_dokter, tanggal, diagnosa) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rekamMedis.getIdRekam());
            stmt.setInt(2, rekamMedis.getIdPasien());
            stmt.setInt(3, rekamMedis.getIdDokter());
            stmt.setDate(4, new java.sql.Date(rekamMedis.getTanggal().getTime()));
            stmt.setString(5, rekamMedis.getDiagnosa());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRekamMedis(RekamMedis rekamMedis) {
        String sql = "UPDATE tb_rekam_medis SET id_pasien=?, id_dokter=?, tanggal=?, diagnosa=? WHERE id_rekam=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rekamMedis.getIdPasien());
            stmt.setInt(2, rekamMedis.getIdDokter());
            stmt.setDate(3, new java.sql.Date(rekamMedis.getTanggal().getTime()));
            stmt.setString(4, rekamMedis.getDiagnosa());
            stmt.setInt(5, rekamMedis.getIdRekam());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusRekamMedis(int idRekam) {
        String sql = "DELETE FROM tb_rekam_medis WHERE id_rekam=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRekam);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public RekamMedis getRekamMedisById(int idRekam) {
        String sql = "SELECT * FROM tb_rekam_medis WHERE id_rekam=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRekam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parseResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RekamMedis> getAllRekamMedis() {
        List<RekamMedis> daftarRekamMedis = new ArrayList<>();
        String sql = "SELECT * FROM tb_rekam_medis";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarRekamMedis.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarRekamMedis;
    }

    private RekamMedis parseResultSet(ResultSet rs) throws SQLException {
        return new RekamMedis(
                rs.getInt("id_rekam"),
                rs.getInt("id_pasien"),
                rs.getInt("id_dokter"),
                rs.getDate("tanggal"),
                rs.getString("diagnosa")
        );
    }
}
