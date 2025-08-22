# 📚 Library Management System — Interview Preparation Guide

---

## 1. Project Overview

The **Library Management System** is a Java Swing-based desktop application that allows users to manage a library's book inventory using a MySQL database. The application supports adding, searching, deleting, and viewing books. It follows the **MVC (Model-View-Controller)** architecture, ensuring separation of concerns and easy maintainability.

---

## 2. Technologies Used

- **Java** (JDK 11 or later)
- **Swing** (for GUI)
- **MySQL** (database)
- **JDBC** (database connectivity)
- **Maven** (dependency management)

---

## 3. Project Structure & File Explanations

```
src/main/java/org/example/
│
├── dao/
│   ├── BookDAO.java
│   └── DatabaseConnection.java
│
├── model/
│   └── Book.java
│
├── gui/
│   ├── MainMenu.java
│   ├── AddBookGUI.java
│   ├── ViewBooksGUI.java
│   ├── SearchBookGUI.java
│   └── DeleteBookGUI.java
│
├── Main.java
└── TestDB.java
```

### Top-Level Files
- **README.md**: Documentation, setup, and usage instructions.
- **Dockerfile**: Steps to containerize the app for deployment.
- **pom.xml**: Maven build and dependency file.
- **dependency-reduced-pom.xml**: Maven file for shaded builds.
- **render.yaml**: Deployment/CI configuration.
- **.gitignore, .github, .idea**: Git and IDE configuration.
- **target/**: Maven build output.

---

### Source Code

#### Main Application Entry
- **Main.java**
  - Entry point of the application.
  - Checks for a database connection before launching the main menu GUI.
  - Uses `SwingUtilities.invokeLater` for thread-safe GUI launch.

#### Database Testing
- **TestDB.java**
  - Simple utility to test database connectivity.
  - Prints success or failure message to the console.

#### Data Access Layer (DAO)
- **dao/BookDAO.java**
  - Handles all CRUD operations for books.
  - Methods include:
    - `addBook(Book book)`: Inserts a new book into the DB.
    - `updateBookAvailability(int bookId, String availability)`: Updates book status.
    - `getAllBooks()`: Retrieves all books.
    - `searchBookById(int id)`, `searchBookByTitle(String title)`: Search functionality.
    - `deleteBook(int id)`: Removes a book from the DB.
  - Uses prepared statements to prevent SQL injection.

- **dao/DatabaseConnection.java**
  - Manages the MySQL connection.
  - Singleton pattern for connection reuse.
  - Handles connection open/close with error handling.
  - JDBC URL, username, and password are set here.

#### Model Layer
- **model/Book.java**
  - Represents a book entity.
  - Fields: `id`, `title`, `author`, `genre`, `availability`.
  - Constructors for new and existing books.
  - Getters and setters for all fields.

#### GUI Layer
- **gui/MainMenu.java**
  - Main navigation window (JFrame).
  - Buttons for Add, View, Search, Delete, and Exit.
  - Opens corresponding GUI windows for each action.

- **gui/AddBookGUI.java**
  - Form to add a new book.
  - Validates user input.
  - Calls `BookDAO.addBook()` to persist data.

- **gui/ViewBooksGUI.java**
  - Displays all books in a table.
  - Allows refreshing and updating book availability.

- **gui/SearchBookGUI.java**
  - Lets users search for books by title or ID.
  - Displays results in a text area.

- **gui/DeleteBookGUI.java**
  - Allows deletion of a book by ID.
  - Calls `BookDAO.deleteBook()` and shows success/failure.

---

## 4. Database Schema

```sql
CREATE DATABASE LibraryDB;
USE LibraryDB;

CREATE TABLE books (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  genre VARCHAR(255),
  availability VARCHAR(50) NOT NULL
);
```

---

## 5. Build & Run

1. **Set up MySQL** and run the schema above.
2. **Configure credentials** in `DatabaseConnection.java`.
3. **Build the project**:
   ```sh
   mvn clean install
   ```
4. **Run the application**:
   ```sh
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

---

## 6. Detailed Interview Insights

### MVC Pattern
- **Model**: `Book.java` — represents data.
- **View**: GUI classes — handle user interaction.
- **Controller**: `BookDAO.java`, GUI event handlers — business logic & DB access.
- **Benefit**: Promotes separation of concerns, easier testing, and maintainability.

### JDBC & Security
- Uses **prepared statements** to prevent SQL injection.
- Connection is managed centrally for efficiency and error handling.

### Swing GUI
- Event-driven programming.
- Modular windows for each operation (add, view, search, delete).
- Uses `JFrame`, `JButton`, `JTextField`, `JTable`, etc.

### Error Handling
- Database connection and query errors are caught and reported to the user.
- User input is validated before DB operations.

### Extensibility
- Modular design allows for easy addition of features (e.g., book lending, user management).

---

## 7. Potential Interview Questions

- How does the DAO pattern improve maintainability?
- How does the application handle SQL injection?
- What are the benefits of using MVC in desktop applications?
- How would you add a new feature (e.g., book lending)?
- How does the system ensure only valid data is added to the database?
- How is error handling managed in both the GUI and DAO layers?
- How could you refactor the code for unit testing?
- What are the advantages and disadvantages of using Swing for GUIs?

---

## 8. Example User Stories

- **As a librarian**, I want to add new books so that the inventory is up to date.
- **As a user**, I want to search for books by title or ID so I can find what I need quickly.
- **As a librarian**, I want to delete books that are no longer available.
- **As a librarian**, I want to update the availability status of a book.

---

## 9. Best Practices Demonstrated

- **Separation of concerns** via MVC.
- **Secure database access** with prepared statements.
- **User-friendly error messages** and validations.
- **Extensible codebase** for future features.

---

## 10. Further Improvements (for discussion)

- Add user authentication and roles.
- Implement book lending and return features.
- Add reporting (e.g., most borrowed books).
- Improve UI/UX with a more modern toolkit (e.g., JavaFX).
- Write unit and integration tests for DAO and GUI.

---

*Prepared for technical interviews. Good luck!*
