package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/klinik_bersama";
    private static final String user = "root";
    private static final String pass = "";
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, pass);
            }
            System.out.println("Koneksi Berhasil");
        } catch (SQLException e) {
            System.err.println("Koneksi gagal tersambung: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database Connection closed!");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
