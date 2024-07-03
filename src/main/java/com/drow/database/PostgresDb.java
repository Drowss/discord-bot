package com.drow.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PostgresDb {
    private static final String url = System.getenv("db_url");

    public static void initDb() {
        // Initialize database
        try (Connection conn = DriverManager.getConnection(url, System.getenv("db_user"), System.getenv("db_password"))) {
            conn.prepareStatement(
                    """
                                                
                            CREATE TABLE IF NOT EXISTS punch_data(
                      name VARCHAR(100),
                      nameHit VARCHAR NOT NULL,
                      times INT DEFAULT 1
                                                );
                    """
            ).execute();
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insertData(String name, String nameHit) {
        // Insert data into database
        try (Connection conn = DriverManager.getConnection(url, System.getenv("db_user"), System.getenv("db_password"))) {
            // Verifica si ya existe un registro con la combinación name y nameHit
            String query = "SELECT times FROM punch_data WHERE name = ? AND nameHit = ?";
            PreparedStatement pstmtSelect = conn.prepareStatement(query);
            pstmtSelect.setString(1, name);
            pstmtSelect.setString(2, nameHit);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                // Si ya existe, incrementa times
                int currentTimes = rs.getInt("times");
                int newTimes = currentTimes + 1;

                // Actualiza el registro con el nuevo valor de times
                String updateQuery = "UPDATE punch_data SET times = ? WHERE name = ? AND nameHit = ?";
                PreparedStatement pstmtUpdate = conn.prepareStatement(updateQuery);
                pstmtUpdate.setInt(1, newTimes);
                pstmtUpdate.setString(2, name);
                pstmtUpdate.setString(3, nameHit);
                pstmtUpdate.executeUpdate();
                System.out.println("Records updated successfully");
            } else {
                // Si no existe, inserta un nuevo registro con times = 1
                String insertQuery = "INSERT INTO punch_data(name, nameHit) VALUES (?, ?)";
                PreparedStatement pstmtInsert = conn.prepareStatement(insertQuery);
                pstmtInsert.setString(1, name);
                pstmtInsert.setString(2, nameHit);
                pstmtInsert.executeUpdate();
                System.out.println("Records created successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int selectHit(String name, String nameHit) {
        // Select data from database
        String sql = "SELECT times FROM punch_data WHERE name = ? AND nameHit = ?";
        try (Connection conn = DriverManager.getConnection(url, System.getenv("db_user"), System.getenv("db_password"));
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, nameHit);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("times");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
