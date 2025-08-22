package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JButton addBookBtn = new JButton("Add Book");
        JButton viewBooksBtn = new JButton("View Books");
        JButton searchBookBtn = new JButton("Search Book");
        JButton deleteBookBtn = new JButton("Delete Book");
        JButton exitBtn = new JButton("Exit");

        addBookBtn.addActionListener(e -> new AddBookGUI());
        viewBooksBtn.addActionListener(e -> new ViewBooksGUI());
        searchBookBtn.addActionListener(e -> new SearchBookGUI());
        deleteBookBtn.addActionListener(e -> new DeleteBookGUI());
        exitBtn.addActionListener(e -> System.exit(0));

        add(addBookBtn);
        add(viewBooksBtn);
        add(searchBookBtn);
        add(deleteBookBtn);
        add(exitBtn);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
