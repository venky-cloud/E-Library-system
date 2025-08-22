package org.example.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String availability;

    public Book(int id, String title, String author, String genre, String availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public Book(String title, String author, String genre, String availability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailability() {
        return availability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
