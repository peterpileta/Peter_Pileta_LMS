import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LMSTest {
	Book testBook2 = new Book(2, "title2", "author2");
	
	@Test
	void testAddbook() {
		Library library = new Library();
		library.addBook(testBook2);
		assertTrue(library.books.containsValue(testBook2));
	}
	
	@Test
	void testRemovebook() {
		Library library = new Library();
		library.addBook(testBook2);
		assertTrue(library.removeBookById(1) && library.removeBookByTitle(testBook2.getTitle()));
	}
	
	@Test
	void testCheckOut() {
		testBook2.setCheckedOut(true);
		
		assertTrue(testBook2.isCheckedOut() && testBook2.getDueDate() != null? true : false);
	}
	
	@Test
	void testCheckIn() {
		testBook2.setCheckedOut(false);
		
		assertTrue(testBook2.isCheckedOut() && testBook2.getDueDate() != null? false : true);
	}
}
