package org.example;

import org.example.dao.DatabaseConnection;
import org.example.gui.MainMenu;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
        }

        System.out.println("Starting server on port: " + port);
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = DatabaseConnection.getConnection();
                if (connection != null) {
                    new MainMenu().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
