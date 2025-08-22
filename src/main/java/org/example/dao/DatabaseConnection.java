package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; // Change if necessary
    private static final String PASSWORD = ""; // Add your password if needed

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            synchronized (DatabaseConnection.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("✅ Database connected successfully!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(" MySQL JDBC Driver not found!", e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println(" Error closing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
