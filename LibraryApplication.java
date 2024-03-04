import java.util.Scanner;
/**
 * Peter Pileta, Software Development I, 03/04/2024
 * Class Name: LibraryApplication
 * This class is the entry point for the Library Management System. It handles user interactions,
 * file input for adding books, and manages the operations of checking in/out and removing books.
 * The objective is to provide a simple interface for library management tasks.
 */
public class LibraryApplication {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        
        while(true) {
            // Prompt the user for actions
            System.out.println("\nChoose an action:");
            System.out.println("1. Remove a book by ID");
            System.out.println("2. Remove a book by title");
            System.out.println("3. Check in a book");
            System.out.println("4. Check out a book");
            System.out.println("5. Add books from a text file");
            System.out.println("6. Print library database");
            System.out.println("7. Exit LMS");
            int choice = scanner.nextInt();

        if (choice == 1) {
        	// Removing by ID
            scanner.nextLine(); 
            System.out.print("Enter a book ID to remove: ");
            int idToRemove = scanner.nextInt();
            if (library.removeBookById(idToRemove)) {
                System.out.println("Book removed successfully.");
            } else {
                System.out.println("Book not found.");
            }
            library.printBooks();
        } if (choice == 2) {
            // Removing by Title
            scanner.nextLine(); 
            System.out.print("Enter a book title to remove: ");
            String titleToRemove = scanner.nextLine();
            if (library.removeBookByTitle(titleToRemove)) {
                System.out.println("Book removed successfully.");
            } else {
                System.out.println("Book not found.");
            }
            library.printBooks();
        } if (choice == 3) {
            // Check in a book
        	scanner.nextLine();
            System.out.print("Enter a book title to check in: ");
            String titleToCheckIn = scanner.nextLine();
            if (library.checkInBook(titleToCheckIn)) {
                System.out.println("Book checked in successfully.");
            } else {
                System.out.println("Book not found or not checked out.");
            }
            library.printBooks();
        } if (choice == 4) {
            // Check out a book
        	scanner.nextLine(); // clear buffer
            System.out.print("Enter a book title to check out: ");
            String titleToCheckOut = scanner.nextLine();
            if (library.checkOutBook(titleToCheckOut)) {
                System.out.println("Book checked out successfully.");
            } else {
                System.out.println("Book not found or already checked out.");
            }
            library.printBooks();
        } if (choice == 5) {
            // Add more books from another text file
            library.readFile();
        } if (choice == 6) {
        	  // Print all books in the library
        	  library.printBooks();
        }
        if (choice == 7) {
        	/*Exit the program*/ System.exit(0);
        }
    }
   }
}
