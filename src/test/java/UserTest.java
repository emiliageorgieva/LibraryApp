import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    private Book book1;

    @BeforeEach
    void setUp() {
        user = new User("john", "password123", "John Doe", 30, "Male", "123 Street", "City", "Country", "john@example.com");
        book1 = new Book("Book 1", new ArrayList<>(), "Genre 1", "Summary 1", "ISBN 1", new ArrayList<>());
    }

    @Test
    public void testGetDueDate_BookNotPresent_ReturnsNull() {
        assertNull(user.getDueDate(book1));
    }

    @Test
    public void testSetDueDate_ValidBookAndDueDate_SetsDueDate() {
        Date dueDate = new Date();
        user.setDueDate(book1, dueDate);
        assertEquals(dueDate, user.getDueDate(book1));
    }

    @Test
    public void testGetUsername_ReturnsUsername() {
        assertEquals("john", user.getUsername());
    }

    @Test
    public void testSetUsername_SetsUsername() {
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void testGetBorrowedBooks_ReturnsBorrowedBooks() {
        assertEquals(0, user.getBorrowedBooks().size());
    }
    @Test
    public void testBorrowBook_ValidBook_BorrowedBookAdded() {
        Book book = new Book("Book 1", new ArrayList<>(), "Genre 1", "Summary 1", "ISBN 1", new ArrayList<>());
        BorrowedBook borrowedBook = new BorrowedBook(user,book);
        int initialBorrowedBooksCount = user.getBorrowedBooks().size();
        user.getBorrowedBooks().add(borrowedBook.getBook());
        List<Book> borrowedBooks = user.getBorrowedBooks();
        assertEquals(initialBorrowedBooksCount + 1, borrowedBooks.size());
        assertTrue(borrowedBooks.contains(book));
    }
    @Test
    public void testReadBookOnline_ValidBook_BookAddedToReadBooks() {
        Book book = new Book("Book 1", new ArrayList<>(), "Genre 1", "Summary 1", "ISBN 1", new ArrayList<>());
        int initialReadBooksCount = user.getReadBooks().size();
        user.readBookOnline(book);
        List<Book> readBooks = user.getReadBooks();
        assertEquals(initialReadBooksCount + 1, readBooks.size());
        assertTrue(readBooks.contains(book));
    }
    @Test
    public void testDownloadBook_ValidBook_BookAddedToDownloadedBooks() {
        Book book = new Book("Book 1", new ArrayList<>(), "Genre 1", "Summary 1", "ISBN 1", new ArrayList<>());
        int initialDownloadedBooksCount = user.getDownloadedBooks().size();
        user.downloadBook(book);
        List<Book> downloadedBooks = user.getDownloadedBooks();
        assertEquals(initialDownloadedBooksCount + 1, downloadedBooks.size());
        assertTrue(downloadedBooks.contains(book));
    }
    @Test
    public void testGetReadBooks_NoBooks_ReturnsEmptyList() {
        List<Book> readBooks = user.getReadBooks();
        assertNotNull(readBooks);
        assertTrue(readBooks.isEmpty());
    }
    @Test
    public void testGetDownloadedBooks_NoBooks_ReturnsEmptyList() {
        List<Book> downloadedBooks = user.getDownloadedBooks();
        assertNotNull(downloadedBooks);
        assertTrue(downloadedBooks.isEmpty());
    }
    @Test
    public void testReadBookOnline_NullBook_DoesNotThrowException() {
        assertDoesNotThrow(() -> user.readBookOnline(null));
    }
    @Test
    public void testGetBorrowedBooks_NoBooks_ReturnsEmptyList() {
        List<Book> borrowedBooks = user.getBorrowedBooks();
        assertNotNull(borrowedBooks);
        assertTrue(borrowedBooks.isEmpty());
    }
    @Test
    public void testDownloadBook_NullBook_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> user.downloadBook(null));
    }
}