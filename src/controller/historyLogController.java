package controller;

import model.source.HistoryLog;
import model.historyLogModel;
import util.DBConnection;
import model.akunModel;
import util.Validator;

import java.sql.Connection;
import java.util.List;

public class historyLogController {
    private final historyLogModel historyLogModel;
    private final akunModel akunModel;

    public historyLogController() {
        Connection conn = DBConnection.getConnection();
        this.historyLogModel = new historyLogModel(conn);
        this.akunModel = new akunModel(conn);
    }

    public boolean insertLog(int userid, String action, String userType) {
        if (userid <= 0) {
            return false;
        }
        if (!Validator.isValidUserType(userType)) {
            return false;
        }
        if (!Validator.isValidAction(action)) {
            return false;
        }

        String username = akunModel.getAkunById(userid).getUsername();
        if (username == null || !Validator.isValidUsername(username)) {
            System.out.println("Username tidak valid.");
            return false;
        }

        HistoryLog log = new HistoryLog();
        log.setUserId(userid);
        log.setUsername(username);
        log.setAction(action);
        log.setTimestamp(System.currentTimeMillis());

        return historyLogModel.insertLog(log);        
    }

    public List<HistoryLog> getAllLogs() {
        return historyLogModel.getAllLogs();
    }

    public HistoryLog getLogsByUserId(int userId) {
        if (userId <= 0) {
            System.out.println("User ID tidak valid. != 0");
            return null;
        }
        return historyLogModel.getLogsByUserId(userId);
    }

    public boolean clearLogs() {
        return historyLogModel.deleteAllLogs();
    }
}
