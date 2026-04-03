package com.example.UC16;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementRepository {

    public QuantityMeasurementRepository() {
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS measurements (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "operation VARCHAR(50)," +
                "val DOUBLE)";

        try {
            Connection conn = (Connection) DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            stmt.execute(sql);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            throw new DatabaseException("Table creation failed");
        }
    }

    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO measurements(operation, val) VALUES (?, ?)";

        try {
            Connection conn = (Connection) DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, entity.getOperation());
            ps.setDouble(2, entity.getValue());

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (Exception e) {
            throw new DatabaseException("Insert failed");
        }
    }

    public List<QuantityMeasurementEntity> getAll() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM measurements";

        try {
            Connection conn = (Connection) DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String op = rs.getString("operation");
                double val = rs.getDouble("val");

                list.add(new QuantityMeasurementEntity(op, val));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            throw new DatabaseException("Fetch failed");
        }

        return list;
    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM measurements";

        try {
            Connection conn = (Connection) DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;

            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            stmt.close();
            conn.close();

            return count;

        } catch (Exception e) {
            throw new DatabaseException("Count failed");
        }
    }

    public void deleteAll() {

        String sql = "DELETE FROM measurements";

        try {
            Connection conn = (Connection) DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            throw new DatabaseException("Delete failed");
        }
    }
}
