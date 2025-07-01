package model;

import model.source.DokterSchedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JadwalDokterModel {
    private Connection connection;

    public  JadwalDokterModel(Connection connection) {
        this.connection = connection;
    }

    public boolean createSchedule(DokterSchedule schedule) throws SQLException {
        String sql = "INSERT INTO tb_jadwal_dokter (nama, nip, spesialis, hari, jam) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, schedule.getNama());
            statement.setString(2, schedule.getNip());
            statement.setString(3, schedule.getSpesialis());
            statement.setString(4, schedule.getHari());
            statement.setString(5, schedule.getJam());
            statement.executeUpdate();
            return true;
        }
    }

    public List<DokterSchedule> getAllSchedules() throws SQLException {
        List<DokterSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM tb_jadwal_dokter";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DokterSchedule schedule = new DokterSchedule(
                    resultSet.getString("nama"),
                    resultSet.getString("nip"),
                    resultSet.getString("spesialis"),
                    resultSet.getString("hari"),
                    resultSet.getString("jam")
                );
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    public boolean updateSchedule(DokterSchedule schedule) throws SQLException {
        String sql = "UPDATE tb_jadwal_dokter SET hari = ?, jam = ? WHERE nip = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, schedule.getHari());
            statement.setString(2, schedule.getJam());
            statement.setString(3, schedule.getNip());
            statement.executeUpdate();
            return true;
        }
    }

    public boolean deleteSchedule(String nip) throws SQLException {
        String sql = "DELETE FROM tb_jadwal_dokter WHERE nip = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nip);
            statement.executeUpdate();
            return true;
        }
    }
}