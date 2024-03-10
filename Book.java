/**
 * Peter Pileta, Software Development I, 03/04/2024
 * Class Name: Book
 * This class represents a book with a unique ID, title, and author. 
 * It's used to create book objects to be managed within the library system.
 */
import java.time.LocalDate;    
public class Book {
    private int id;
    private String title;
    private String author;
    private String duedate;
    private boolean isCheckedOut;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.duedate = null;
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

    public String getDueDate() {
        return duedate;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
        
        if (checkedOut) {
        LocalDate date = LocalDate.now().plusWeeks(1);  
        duedate = date.toString();
        } else duedate = null;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Checked Out: " + isCheckedOut + duedate != null? "Due date: " + duedate: "";
    }
}
