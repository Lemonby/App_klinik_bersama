package model;

import model.source.Akun;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class akunModel {

    private Connection conn;

    public akunModel(Connection connection) {
        this.conn = connection;
    }

    public boolean AdminLogin(Akun akun) {
        String sql = "SELECT * FROM tb_akun WHERE username=? AND password=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, akun.getUsername());
            stmt.setString(2, akun.getPassword());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean tambahAkun(Akun akun) {
        String sql = "INSERT INTO tb_akun (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, akun.getUsername());
            stmt.setString(2, akun.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAkun(Akun akun) {
        String sql = "UPDATE tb_akun SET username=? WHERE id_akun=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, akun.getIdAkun());
            stmt.setString(2, akun.getUsername());
            stmt.setString(3, akun.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusAkun(int idAkun) {
        String sql = "DELETE FROM tb_akun WHERE id_akun=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAkun);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Akun getAkunById(int idAkun) {
        String sql = "SELECT * FROM tb_akun WHERE id_akun=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAkun);
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

    public Akun getAkunByUsername(String username) {
        String sql = "SELECT * FROM tb_akun WHERE username=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
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

    public List<Akun> getAllAkun() {
        List<Akun> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_akun";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Akun parseResultSet(ResultSet rs) throws SQLException {
        return new Akun(
                rs.getInt("id_akun"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
