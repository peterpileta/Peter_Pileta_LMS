import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Peter Pileta, Software Development I, 04/07/2024
 * Class Name: LibraryGUI
 * This class represents the GUI for the Library Management System (LMS) application.
 * It allows users to interact with the library database by performing various operations such as adding books, removing books,
 * checking books in/out, and displaying the library database.
 */
public class LibraryGUI extends JFrame {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pezespada1";
    Library library;
    JTextArea displayArea;

    public LibraryGUI() {
        super("Library Management System");


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
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        // Remove by barcode button action listener
        removeBarcodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "DELETE FROM books WHERE barcode = ?";
                try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, barcodeField.getText());
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        displayArea.append("Book with barcode " + barcodeField.getText() + " removed successfully.\n");
                 	   Statement st;
                 	   ResultSet rs;
                 	   st = conn.createStatement();
                 	   rs = st.executeQuery("select * from books");
                 	    ResultSetMetaData metadata = rs.getMetaData();
                 	    int columnCount = metadata.getColumnCount();  
                 	    String content = "";
                 	    while (rs.next()) {
                 	        String row = "";
                 	        for (int i = 1; i <= columnCount; i++) {
                 	            row += rs.getString(i) + ", ";          
                 	        }
                 	        content += row + "\n";
                 	    }
                 	    displayArea.append(content);
                    } else {
                    	displayArea.append("No book found with barcode \"" + barcodeField.getText() + "\".");
                    }
                } catch (SQLException event) {
                    System.err.println("Error removing book by barcode: " + event.getMessage());
                }
            }
        });

        // Remove by title button action listener
        removeTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "DELETE FROM books WHERE title = ?";
                try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, titleField.getText());
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                    	displayArea.append("Book with title " + titleField.getText() + " removed successfully.\n");
                 	   Statement st;
                 	   ResultSet rs;
                 	   st = conn.createStatement();
                 	   rs = st.executeQuery("select * from books");
                 	    ResultSetMetaData metadata = rs.getMetaData();
                 	    int columnCount = metadata.getColumnCount();  
                 	    String content = "";
                 	    while (rs.next()) {
                 	        String row = "";
                 	        for (int i = 1; i <= columnCount; i++) {
                 	            row += rs.getString(i) + ", ";          
                 	        }
                 	        content += row + "\n";
                 	    }
                 	    displayArea.append(content);
                    } else {
                    	displayArea.append("No book found with title \"" + titleField.getText() + "\".");
                    }
                } catch (SQLException event) {
                    System.err.println("Error removing book by title: " + event.getMessage());
                }
            }
        });

        // Check out button action listener
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            	LocalDate localDate = LocalDate.now().plusWeeks(4);
                try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                	   PreparedStatement stmt = conn.prepareStatement("UPDATE books SET checked_out = 1, due_date = " + dtf.format(localDate) + " WHERE title = ? AND checked_out = 0")) {
                       stmt.setString(1, checkOutField.getText());
                       int rowsUpdated = stmt.executeUpdate();
                       if (rowsUpdated > 0) {
                    	   displayArea.append("Book \"" + checkOutField.getText() + "\" checked out successfully.");
                    	   Statement st;
                    	   ResultSet rs;
                    	   st = conn.createStatement();
                    	   rs = st.executeQuery("select * from books");
                    	    ResultSetMetaData metadata = rs.getMetaData();
                    	    int columnCount = metadata.getColumnCount();  
                    	    String content = "";
                    	    while (rs.next()) {
                    	        String row = "";
                    	        for (int i = 1; i <= columnCount; i++) {
                    	            row += rs.getString(i) + ", ";          
                    	        }
                    	        content += row + "\n";
                    	    }
                    	    displayArea.append(content);
                       } else {
                    	   displayArea.append("Book \"" + checkOutField.getText() + "\" not found or already checked out.");
                       }
                   } catch (SQLException event) {
                       event.printStackTrace();
                   }
            }
        });

        // Check in button action listener
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            		   PreparedStatement stmt = conn.prepareStatement("UPDATE books SET checked_out = 0, due_date = NULL WHERE title = ? AND checked_out = 1")) {
                       stmt.setString(1, checkInField.getText());
                       int rowsUpdated = stmt.executeUpdate();
                       if (rowsUpdated > 0) {
                    	   displayArea.append("Book \"" + checkInField.getText() + "\" checked in successfully.");
                    	   Statement st;
                    	   ResultSet rs;
                    	   st = conn.createStatement();
                    	   rs = st.executeQuery("select * from books");
                    	    ResultSetMetaData metadata = rs.getMetaData();
                    	    int columnCount = metadata.getColumnCount();  
                    	    String content = "";
                    	    while (rs.next()) {
                    	        String row = "";
                    	        for (int i = 1; i <= columnCount; i++) {
                    	            row += rs.getString(i) + ", ";          
                    	        }
                    	        content += row + "\n";
                    	    }
                    	    displayArea.append(content);
                       } else {
                    	   displayArea.append("Book \"" + checkInField.getText() + "\" not found or already checked in.");
                       }
                   } catch (SQLException event) {
                       event.printStackTrace();
                   }
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
    }
