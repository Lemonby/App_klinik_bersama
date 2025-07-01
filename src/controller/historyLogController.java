package controller;

import model.source.HistoryLog;
import model.HistoryLogModel;
import util.DBConnection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.util.List;

public class HistoryLogController {
    private final HistoryLogModel historyLogModel;
    private final HistoryLog historyLog;

    public HistoryLogController() {
        Connection conn = DBConnection.getConnection();
        this.historyLogModel = new HistoryLogModel(conn);
        this.historyLog = new HistoryLog();
    }

    public boolean logAdminActivity(int idLog, String uname, String action, String timestamp) {
        HistoryLog log = new HistoryLog();
        log.setIdLog(idLog);
        log.setUsername(uname);
        log.setAction(action);
        log.setTimestamp(timestamp);

        return historyLogModel.insertLog(log);
    }

    public boolean logActivity(int idLog, String uname, String action, String timestamp) {
        HistoryLog log = new HistoryLog();
        log.setIdLog(idLog);
        log.setUsername(uname);
        log.setAction(action);
        log.setTimestamp(timestamp);

        return historyLogModel.insertLog(log);
    }

    public boolean recordLogin(String uname) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int idLog = historyLog.getIdLog();
        String action = "Login";
        return logActivity(idLog, uname, action, timestamp);
    }

    public boolean recordLogout(String uname) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int idLog = historyLog.getIdLog();
        String action = "Logout";
        return logActivity(idLog, uname, action, timestamp);
    }

    public boolean recordAdminLogin() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int idLog = historyLog.getIdLog();
        String uname = "Admin";
        String action = "Login";
        return logAdminActivity(idLog, uname, action, timestamp);
    }

    public boolean recordAdminLogout() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int idLog = historyLog.getIdLog();
        String uname = "Admin";
        String action = "Logout";
        return logAdminActivity(idLog, uname, action, timestamp);
    }

    public boolean clearLogs() {
        return historyLogModel.deleteAllLogs();
    }

    public List<HistoryLog> getAllLogs() {
        return historyLogModel.getAllLogs();
    }
}
