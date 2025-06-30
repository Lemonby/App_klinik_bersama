package model.source;

import util.Validator;

public class Akun {
    private int id_akun;
    private String userName;
    private String password;

    public Akun (int akun, String uname, String password) {
        this.id_akun = akun;
        this.userName = uname;
        this.password = password;
    }

    public void setIdAkun(int idAkun) {
        this.id_akun = idAkun; 
    }
    public int getIdAkun() { return id_akun; }

    public void setUsername(String username) {
        if (!Validator.isValidUsername(username)) {
            throw new IllegalArgumentException("Username tidak valid");
        }
        this.userName = username;
    }
    public String getUsername() { return userName; }

    public void setPassword(String password) {
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Password tidak valid");
        }
        this.password = password;
    }
    public String getPassword() { return password; }
}
