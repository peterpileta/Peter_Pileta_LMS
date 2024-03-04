/**
 * Peter Pileta, Software Development I, 03/04/2024
 * Class Name: Book
 * This class represents a book with a unique ID, title, and author. 
 * It's used to create book objects to be managed within the library system.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isCheckedOut;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isCheckedOut = false; // Books are available by default
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Checked Out: " + isCheckedOut;
    }
}
