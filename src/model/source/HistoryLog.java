package model.source;

import util.Validator;

public class HistoryLog {

    private int idLog; 
    private String username;
    private String action; 
    private String timestamp; 

    public HistoryLog() {
    // Konstruktor kosong untuk kebutuhan setter
    }

    public HistoryLog(int idLog, String username, String action, String timestamp) {
        this.idLog = idLog;
        this.username = username;
        this.action = action;
        this.timestamp = timestamp;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog; 
    }
    public int getIdLog() { return idLog; }

    public void setUsername(String username) { 
        if (!Validator.isValidUsername(username)) {
            throw new IllegalArgumentException("Username tidak valid.");
        }
        this.username = username; 
    }
    public String getUsername() { return username; }

    public void setAction(String action) { 
        if (action == null || (!action.equalsIgnoreCase("LOGIN") && !action.equalsIgnoreCase("LOGOUT"))) {
            throw new IllegalArgumentException("Action harus 'LOGIN' atau 'LOGOUT'.");
        }
        this.action = action; 
    }
    public String getAction() { return action; }

    public void setTimestamp(String timestamp) { 
        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp tidak valid.");
        }
        this.timestamp = timestamp; 
    }
    public String getTimestamp() { return timestamp; }

}