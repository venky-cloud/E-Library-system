package org.example.gui;

import org.example.dao.BookDAO;
import org.example.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewBooksGUI extends JFrame {
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton, updateButton;
    private BookDAO bookDAO;

    public ViewBooksGUI() {
        bookDAO = new BookDAO();

        setTitle("View and Update Books");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table setup
        String[] columnNames = {"ID", "Title", "Author", "Genre", "Availability"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Buttons
        refreshButton = new JButton("Refresh");
        updateButton = new JButton("Update Availability");

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.add(refreshButton);
        panel.add(updateButton);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        loadBooks();
        refreshButton.addActionListener(e -> loadBooks());
        updateButton.addActionListener(e -> updateAvailability());

        setVisible(true);
    }

    private void loadBooks() {
        tableModel.setRowCount(0); // Clear table
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            tableModel.addRow(new Object[]{
                    book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getAvailability()
            });
        }
    }

    private void updateAvailability() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a book to update.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int bookId = (int) tableModel.getValueAt(selectedRow, 0);
        String currentStatus = (String) tableModel.getValueAt(selectedRow, 4);
        String newStatus = currentStatus.equals("Available") ? "Checked Out" : "Available";

        int confirm = JOptionPane.showConfirmDialog(this, "Change status to " + newStatus + "?", "Confirm Update", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            bookDAO.updateBookAvailability(bookId, newStatus);
            loadBooks();
        }
    }
}
