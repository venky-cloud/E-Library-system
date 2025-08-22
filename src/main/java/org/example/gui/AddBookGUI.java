package org.example.gui;

import org.example.dao.BookDAO;
import org.example.model.Book;

import javax.swing.*;

public class AddBookGUI extends JFrame {
    private JTextField titleField, authorField, genreField;
    private JComboBox<String> availabilityBox;

    public AddBookGUI() {
        setTitle("Add New Book");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        titleField = new JTextField();
        authorField = new JTextField();
        genreField = new JTextField();
        availabilityBox = new JComboBox<>(new String[]{"Available", "Checked Out"});

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> addBook());

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Author:"));
        add(authorField);
        add(new JLabel("Genre:"));
        add(genreField);
        add(new JLabel("Availability:"));
        add(availabilityBox);
        add(addButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        String availability = (String) availabilityBox.getSelectedItem();

        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title and Author cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book newBook = new Book(title, author, genre, availability);
        if (BookDAO.addBook(newBook)) {
            JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add book!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
