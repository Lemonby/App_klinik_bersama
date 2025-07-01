package model;

import model.source.Akun;
import model.source.HistoryLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryLogModel {
    
    private Akun akun;
    private final Connection conn;

    public HistoryLogModel(Connection connection) {
        this.conn = connection;
    }

    public boolean insertLog(HistoryLog log) {
        String sql = "INSERT INTO tb_history_log (id_historyLog, uname, action, timestamp) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, log.getIdLog());
            stmt.setString(2, log.getUsername());
            stmt.setString(3, log.getAction());
            stmt.setString(4, log.getTimestamp());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HistoryLog> getAllLogs() {
        List<HistoryLog> daftarHistoryLog = new ArrayList<>();
        String sql = "SELECT * FROM tb_history_log ORDER BY timestamp DESC";
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

    public List<HistoryLog> getLogByUsername(String username) {
        List<HistoryLog> userLogs = new ArrayList<>();
        String sql = "SELECT * FROM tb_history_log WHERE uname = ? ORDER BY timestamp DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username = akun.getUsername());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userLogs.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userLogs;
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
                rs.getInt("id_historyLog"),
                rs.getString("username"),
                rs.getString("action"),
                rs.getString("timestamp")
        );
    }
}