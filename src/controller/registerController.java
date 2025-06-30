package controller;

import model.source.Pasien;
import model.pasienModel;
import model.source.Akun;
import model.akunModel;

import util.Validator;
import util.DBConnection;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

public class registerController {
    private final pasienModel pasienmodel;
    private final akunModel akunmodel;
    public enum RegisterResult {
        SUCCESS, USERNAME, PASSWORD, NAME, ADDRESS, PHONE, INVALID
    }

    public registerController() {
        Connection conn = DBConnection.getConnection();
        this.pasienmodel = new pasienModel(conn);
        this.akunmodel = new akunModel(conn);
    }

    public RegisterResult registrasiPasien(String uname, String pasword, String nama, String alamat, String nik, String kelamain, String noTelepon, String tglLahir) {
       // Validasi input
        if (!Validator.isValidUsername(uname)) {
           System.out.println("Username tidak valid");
           return RegisterResult.USERNAME;
        }
        if (!Validator.isValidPassword(pasword)) {
            System.out.println("Password tidak valid");
            return RegisterResult.PASSWORD;
        }
        if (!Validator.isNotName(nama)) {
            System.out.println("Nama tidak valid");
            return RegisterResult.NAME;
        }
        if (!Validator.isNotEmpty(alamat)) {
            System.out.println("Alamat tidak valid");
            return RegisterResult.ADDRESS;
        }
        if (!Validator.isValidGender(kelamain)) {
            System.out.println("Jenis kelamin tidak valid");
            return RegisterResult.INVALID;
        }
        if (!Validator.isValidPhone(noTelepon)) {
            System.out.println("Nomor telepon tidak valid");
            return RegisterResult.PHONE;
        }
        if (!Validator.isValidNIK(nik)) {
            System.out.println("NIK harus terdiri dari 16 digit angka");
            return RegisterResult.INVALID;
        }
        
        try {
            LocalDate birthDate = LocalDate.parse(tglLahir);
            java.sql.Date sqlDate = java.sql.Date.valueOf(birthDate);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Tanggal lahir tidak valid. Gunakan format YYYY-MM-DD.");
            return RegisterResult.INVALID;
        }

        // Buat objek Akun & Pasien
        Akun akun = new Akun(0, uname, pasword);
        Pasien pasien = new Pasien(0, nik, nama, alamat, kelamain, noTelepon, tglLahir);

        // Simpan akun dan pasien ke database
        boolean akunSaved = akunmodel.tambahAkun(akun);
        if (!akunSaved) {
            System.out.println("Gagal menyimpan akun");
            return RegisterResult.INVALID;
        }

        boolean pasienSaved = pasienmodel.tambahPasien(pasien);
        if (!pasienSaved) {
            System.out.println("Gagal menyimpan pasien");
            return RegisterResult.INVALID;
        }

        JOptionPane.showMessageDialog(null, "Registrasi berhasil! Silakan login.");
        return RegisterResult.SUCCESS;
    }
}
