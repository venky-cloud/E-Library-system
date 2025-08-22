package org.example;

import org.example.dao.DatabaseConnection;

public class TestDB {
    public static void main(String[] args) {
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println(" Database Connection Failed.");
        }
    }
}

