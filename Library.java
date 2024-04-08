import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Peter Pileta, Software Development I, 04/07/2024
 * Class Name: Library
 * This class manages a collection of books, including operations to add, remove,
 * check out, and check in books. It acts as the database for the library system.
 */
public class Library {
    public Map<Integer, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.put(book.getId(), book);
    }
    
    // Read books from a text file
    public void readFile() {
    	String file;
    	Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the filename to load books: ");
            file = scanner.nextLine();
    	    String currentUsersHomeDir = System.getProperty("user.dir");
    	    String fileDir = currentUsersHomeDir + "\\" + "src"+ "\\" + file;
            try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    addBook(new Book(id, title, author));
                }
                break; // Successfully loaded, break the loop
            } catch (IOException e) {
                System.out.println("File not found, please try again.");
            }
        }
       
    }

    // Remove a book from the library by ID
    public boolean removeBookById(int id) {
        if (books.containsKey(id)) {
            books.remove(id);
            return true;
        }
        return false;
    }

    // Remove a book from the library by title
    public boolean removeBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book.getId());
                return true;
            }
        }
        return false;
    }

    // Check out a book
    public boolean checkOutBook(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isCheckedOut()) {
                book.setCheckedOut(true);
                return true;
            }
        }
        return false;
    }

    // Check in a book
    public boolean checkInBook(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isCheckedOut()) {
                book.setCheckedOut(false);
                return true;
            }
        }
        return false;
    }

    // Print all books in the library
    public void printBooks() {
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }
}
