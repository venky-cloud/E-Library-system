package org.example.gui;

import org.example.dao.BookDAO;
import org.example.model.Book;

import javax.swing.*;
import java.util.List;

public class SearchBookGUI extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;

    public SearchBookGUI() {
        setTitle("Search Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        searchButton.addActionListener(e -> searchBook());

        add(new JLabel("Enter Book Title or ID:"));
        add(searchField);
        add(searchButton);
        add(new JScrollPane(resultArea));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void searchBook() {
        String query = searchField.getText();
        resultArea.setText("");

        try {
            int bookId = Integer.parseInt(query);
            Book book = BookDAO.searchBookById(bookId);
            if (book != null) {
                resultArea.append("ID: " + book.getId() + "\nTitle: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nGenre: " + book.getGenre() + "\nAvailability: " + book.getAvailability());
            } else {
                resultArea.append("No book found with ID " + bookId);
            }
        } catch (NumberFormatException e) {
            List<Book> books = BookDAO.searchBookByTitle(query);
            if (!books.isEmpty()) {
                for (Book book : books) {
                    resultArea.append("ID: " + book.getId() + "\nTitle: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nGenre: " + book.getGenre() + "\nAvailability: " + book.getAvailability() + "\n\n");
                }
            } else {
                resultArea.append("No books found with title: " + query);
            }
        }
    }
}
