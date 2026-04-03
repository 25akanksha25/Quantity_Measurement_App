package com.example.UC16;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}


class DBConnection {
    static final String URL = "jdbc:mysql://localhost:3306/Prac";
    static final String USER = "root";
    static final String PASSWORD = "Chopra@10";

    public static Connection getConnection() {
        try {
            return (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Table creation failed: " + e.getMessage());
        }
    }
}
