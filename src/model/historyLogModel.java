package model;

import model.source.HistoryLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class historyLogModel {

    private final Connection conn;

    public historyLogModel(Connection connection) {
        this.conn = connection;
    }

    public boolean insertLog(HistoryLog log) {
        String sql = "INSERT INTO tb_history_log (id_akun, uname, action, timestamp) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, log.getUserId());
            stmt.setString(2, log.getUsername());
            stmt.setString(3, log.getAction());
            stmt.setLong(4, log.getTimestamp());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HistoryLog> getAllLogs() {
        List<HistoryLog> daftarHistoryLog = new ArrayList<>();
        String sql = "SELECT * FROM tb_history_log";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarHistoryLog.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarHistoryLog;
    }

    public HistoryLog getLogsByUserId(int userId) {
        String sql = "SELECT * FROM tb_history_log WHERE id_akun = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return parseResultSet(rs);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteAllLogs() {
        String sql = "DELETE FROM tb_history_log";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private HistoryLog parseResultSet(ResultSet rs) throws SQLException {
        return new HistoryLog(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("action"),
                rs.getString("user_type"),
                rs.getLong("timestamp")
        );
    }
}