import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Class Name: LibraryGUI
 * This class represents the GUI for the Library Management System (LMS) application.
 * It allows users to interact with the system by performing various operations such as adding books, removing books,
 * checking books in/out, and displaying the library database.
 */
public class LibraryGUI extends JFrame {

    Library library;
    JTextArea displayArea;

    public LibraryGUI() {
        super("Library Management System");

        // Initialize library
        library = new Library();

        // Set layout
        setLayout(new BorderLayout());

        // Create components
        JPanel topPanel = new JPanel();
        JLabel fileLabel = new JLabel("Enter file name:");
        JTextField fileNameField = new JTextField(20);
        JButton loadButton = new JButton("Load Books");
        topPanel.add(fileLabel);
        topPanel.add(fileNameField);
        topPanel.add(loadButton);

        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel bottomPanel = new JPanel();
        JTextField barcodeField = new JTextField(10);
        JButton removeBarcodeButton = new JButton("Remove by Barcode");
        JTextField titleField = new JTextField(20);
        JButton removeTitleButton = new JButton("Remove by Title");
        JTextField checkOutField = new JTextField(20);
        JButton checkOutButton = new JButton("Check Out");
        JTextField checkInField = new JTextField(20);
        JButton checkInButton = new JButton("Check In");
        JButton exitButton = new JButton("Exit");
        bottomPanel.add(barcodeField);
        bottomPanel.add(removeBarcodeButton);
        bottomPanel.add(titleField);
        bottomPanel.add(removeTitleButton);
        bottomPanel.add(checkOutField);
        bottomPanel.add(checkOutButton);
        bottomPanel.add(checkInField);
        bottomPanel.add(checkInButton);
        bottomPanel.add(exitButton);

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load books button action listener
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameField.getText();
                loadBooksFromFile(fileName);
            }
        });

        // Remove by barcode button action listener
        removeBarcodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int barcode = Integer.parseInt(barcodeField.getText());
                removeBookByBarcode(barcode);
            }
        });

        // Remove by title button action listener
        removeTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                removeBookByTitle(title);
            }
        });

        // Check out button action listener
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = checkOutField.getText();
                checkOutBook(title);
            }
        });

        // Check in button action listener
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = checkInField.getText();
                library.checkInBook(title);
            }
        });

        // Exit button action listener
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* Method to load books from a file and add them to the library */
    private void loadBooksFromFile(String fileName) {
    	Book book;
    	String currentUsersHomeDir = System.getProperty("user.dir");
    	fileName = currentUsersHomeDir + "\\" + "src"+ "\\" + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                library.addBook(book = new Book(id, title, author));
                displayArea.append(book.toString() + "\n");
            }            
        } catch (IOException e) {
            displayArea.append("Error: File not found.\n");
        }
    }

    /* Method to remove a book from the library by barcode */
    private void removeBookByBarcode(int barcode) {
        if (library.removeBookById(barcode)) {
            displayArea.append("Book with barcode " + barcode + " removed successfully.\n");
            appendBooks();
        } else {
            displayArea.append("Error: Book with barcode " + barcode + " not found.\n");
        }
    }

    /* Method to remove a book from the library by title */
    private void removeBookByTitle(String title) {
        if (library.removeBookByTitle(title)) {
            displayArea.append("Book with title \"" + title + "\" removed successfully.\n");
            appendBooks();
        } else {
            displayArea.append("Error: Book with title \"" + title + "\" not found.\n");
        }
    }

    /* Method to check out a book from the library */
    private void checkOutBook(String title) {
        if (library.checkOutBook(title)) {
            displayArea.append("Book \"" + title + "\" checked out successfully.\n");
            appendBooks();
        }
       }
    
    /* Method to display library´s books into the display area */
    private void appendBooks() {
        if (library.books.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            for (Book book : library.books.values()) {
                displayArea.append(book.toString());
            }
        }
        displayArea.append("\n");
    }
    }
