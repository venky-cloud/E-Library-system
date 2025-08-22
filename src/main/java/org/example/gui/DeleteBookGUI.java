package org.example.gui;

import org.example.dao.BookDAO;

import javax.swing.*;

public class DeleteBookGUI extends JFrame {
    private JTextField bookIdField;

    public DeleteBookGUI() {
        setTitle("Delete Book");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        bookIdField = new JTextField();
        JButton deleteButton = new JButton("Delete Book");

        deleteButton.addActionListener(e -> deleteBook());

        add(new JLabel("Enter Book ID:"));
        add(bookIdField);
        add(deleteButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void deleteBook() {
        int bookId = Integer.parseInt(bookIdField.getText());
        if (BookDAO.deleteBook(bookId)) {
            JOptionPane.showMessageDialog(this, "Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Book deletion failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
