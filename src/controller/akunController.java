package controller;

import model.AkunModel;
import model.source.Akun;
import util.DBConnection;
import util.Validator;
import util.FontCreator;

import java.awt.*;
import java.util.List;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AkunController {
    private final Font Poppins;
    private final Font SMALL_FONT;
    private final Font HEADER_FONT;
    private final Font REGULER_FONT;
    private final ImageIcon logo;
    private final AkunModel akunModel;
    public enum LoginResult { ADMIN, USER, INVALID, NOT_FOUND }

    public AkunController() {
        // Constructor logic if needed
        Connection conn = DBConnection.getConnection();
        this.logo = new ImageIcon();
        this.akunModel = new AkunModel(conn);

        Font loader = FontCreator.getPopins();
        Poppins = loader;
        SMALL_FONT = Poppins.deriveFont(Font.PLAIN, 12f);
        HEADER_FONT = Poppins.deriveFont(Font.BOLD, 18f);
        REGULER_FONT = Poppins.deriveFont(Font.PLAIN, 14f);

        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/assets/logo.png")); // to get the path to your logo image
            logo = new ImageIcon(logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Logo not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public LoginResult login(String username, String password) {
        if(username.equals("admin") || password.equals("admin123")) {
            return LoginResult.ADMIN;
        }

        Akun registeredUser = akunModel.getAkunByUsername(username);
        if (registeredUser == null) {
            return LoginResult.NOT_FOUND;
        }

        if (!password.equals(registeredUser.getPassword())) {
            return LoginResult.INVALID;
        }

        return LoginResult.USER;
    }

    public boolean tambahAkun (Akun akun) {
        if (akun == null) {
            JOptionPane.showMessageDialog(null, "Akun tidak di temukan", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validator.isValidUsername(akun.getUsername())) {
            JOptionPane.showMessageDialog(null, "Username tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validator.isValidPassword(akun.getPassword())) {
            JOptionPane.showMessageDialog(null, "Password tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return akunModel.tambahAkun(akun);
    }

    public boolean updateAkun(Akun akun) {
        if (akun == null || akun.getIdAkun() <= 0) {
            JOptionPane.showMessageDialog(null, "Akun tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validator.isValidUsername(akun.getUsername())) {
            JOptionPane.showMessageDialog(null, "Username tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validator.isValidPassword(akun.getPassword())) {
            JOptionPane.showMessageDialog(null, "Password tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return akunModel.updateAkun(akun);
    }

    public boolean hapusAkun(int id) {
        if (id <= 0) {
            System.out.println("ID tidak valid.");
            return false;
        }

        return akunModel.hapusAkun(id);
    }

    public Akun getAkunById(int id) {
        if (id <= 0) {
            System.out.println("ID tidak valid.");
            return null;
        }

        return akunModel.getAkunById(id);
    }

    public List<Akun> getAllAkun() {
        return akunModel.getAllAkun();
    }
}
