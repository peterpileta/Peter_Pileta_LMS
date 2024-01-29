import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Peter Pileta, Software Development I, 01/28/2024
 * Book class
 * Represents a book in the library catalog with an ID, title, author, and availability status.
 */
class Book {
    int id;
    String title;
    String author;
    boolean available;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }
}
/**
 * Peter Pileta, Software Development I, 01/28/2024
 * Catalog class
 * Manages the collection of books in the library, providing methods for adding, deleting, searching, and listing books.
 */
class Catalog {
    List<Book> books = new ArrayList<>();
    /**
     * addBook method
     * Adds a book to the catalog.
     * @param book The book to be added to the catalog.
     */
    void addBook(Book book) {
        books.add(book);
    }
    /**
     * deleteBook method
     * Deletes a book from the catalog based on its ID.
     * @param bookId The ID of the book to be deleted.
     */
    void deleteBook(int bookId) {
        books.removeIf(book -> book.id == bookId);
    }
    /**
     * searchBook method
     * Searches for a book in the catalog based on its title.
     * @param title The title of the book to be searched.
     * @return The Book object if found, or null otherwise.
     */
    Book searchBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title)) {
                return book;
            }
        }
        return null;
    }
    /**
     * listAllBooks method
     * Lists all the books in the catalog.
     */
    void listAllBooks() {
        for (Book book : books) {
            System.out.println("ID: " + book.id + ", Title: " + book.title + ", Author: " + book.author);
        }
    }
    /**
    * loadBooksFromFile method
    * Loads books from a text file into the catalog.
    * @param filePath The path of the text file containing book information.
    * @throws IOException If an I/O error occurs while reading the file.
    */
    void loadBooksFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                Book book = new Book(id, title, author);
                addBook(book);
            }
        } 
    }
}
/**
 * Peter Pileta, Software Development I, 01/28/2024
 * SDLC class
 * Manages the overall functionality of the Library Management System.
 */
public class SDLC {
    public static void main(String[] args) {
    	  	Catalog catalog = new Catalog();
    	  	Scanner scanner = new Scanner(System.in);
    	  	Scanner scanner1 = new Scanner(System.in);
    	  	
    	 // Step 1: Read books from a text file
    	    System.out.println("Welcome to the LMS. Enter the file you want to check (without file extension): ");        
    	    String in = scanner.nextLine();
    	    String intxt = (in + ".txt");
    	    String currentUsersHomeDir = System.getProperty("user.dir");
    	    String fileName = currentUsersHomeDir + "\\" + "src"+ "\\" + intxt;

    	 // Step 2: List all books in the collection
    	    catalog.loadBooksFromFile(fileName);

    	 // List the library catalog
    	    catalog.listAllBooks();


        while(true) {
            // Prompt the user for actions
            System.out.println("\nChoose an action:");
            System.out.println("1. Remove a book");
            System.out.println("2. Add more books from another text file");
            System.out.println("3. Exit LMS");
            int choice = scanner.nextInt();

        if (choice == 1) {
            // Remove a book
            System.out.print("Enter the ID of the book to remove: ");
            int bookIdToRemove = scanner.nextInt();
            catalog.deleteBook(bookIdToRemove);
            System.out.println("Book removed successfully.");
            catalog.listAllBooks();
        } if (choice == 2) {
            // Add more books from another text file
            System.out.print("Enter the name of the additional text file: ");
            in = scanner1.nextLine();
            intxt = (in + ".txt");
            currentUsersHomeDir = System.getProperty("user.dir");
            fileName = currentUsersHomeDir + "\\" + "src"+ "\\" + intxt;
            catalog.loadBooksFromFile(fileName);
            System.out.println("Books added successfully.");
            catalog.listAllBooks();
        } if (choice == 3)/*Exit the program*/ System.exit(0);
        	else {
            System.out.println("Invalid choice.");
        }
        }
    }
}
