package model.source;

import util.Validator;

public class HistoryLog {

    private int idLog; 
    private int id_akun; 
    private String username;
    private String action; 
    private long timestamp; 

    public HistoryLog() {
    // Konstruktor kosong untuk kebutuhan setter
    }

    public HistoryLog(int idLog, int id_akun, String username, String action, String userType, long timestamp) {
        setIdLog(idLog);
        setUserId(id_akun);
        setUsername(username);
        setAction(action);
        setTimestamp(timestamp);
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog; 
    }
    public int getIdLog() { return idLog; }

    public void setUserId(int id_akun) { 
        if (id_akun <= 0) {
            throw new IllegalArgumentException("User ID harus lebih besar dari 0.");
        }
        this.id_akun = id_akun; 
    }
    public int getUserId() { return id_akun; }

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

    public void setTimestamp(long timestamp) { 
        if (timestamp <= 0) {
            throw new IllegalArgumentException("Timestamp tidak valid.");
        }
        this.timestamp = timestamp; 
    }
    public long getTimestamp() { return timestamp; }

}