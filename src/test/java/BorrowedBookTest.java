import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class BorrowedBookTest {

    @Test
    void testGetUser() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        assertEquals(user, borrowedBook.getUser());
    }

    @Test
    void testGetBook() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        assertEquals(book, borrowedBook.getBook());
    }

    @Test
    void testGetBorrowedDate() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        assertEquals(LocalDate.now(), borrowedBook.getBorrowedDate());
    }

    @Test
    void testGetReturnDate() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        LocalDate expectedReturnDate = LocalDate.now().plusDays(book.getLoanDuration());
        assertEquals(expectedReturnDate, borrowedBook.getReturnDate());
    }

    @Test
    void testCanPostpone() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        assertTrue(borrowedBook.canPostpone());
    }

    @Test
    void testRequestPostponement() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        borrowedBook.requestPostponement(5);
        LocalDate expectedReturnDate = LocalDate.now().plusDays(book.getLoanDuration() + 5);
        assertNotEquals(expectedReturnDate, borrowedBook.getReturnDate());
    }

    @Test
    void testGetFinalReturnDate() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        LocalDate expectedFinalReturnDate = LocalDate.now().plusDays(book.getLoanDuration());
        assertEquals(expectedFinalReturnDate, borrowedBook.getFinalReturnDate());
    }
    @Test
    public void testGetDelayDays_ReturnsZeroIfNotDelayed() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        assertEquals(0, borrowedBook.getDelayDays());
    }
    @Test
    void testSetReturnDate() {
        User user = new User("john", "123456", "John", 56, "male", "Positano Str", "Sofia", "Bulgaria", "john@gmail.com");
        Book book = new Book("Title", null, null, null, null, null);
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        LocalDate newReturnDate = LocalDate.now().plusDays(10);
        borrowedBook.setReturnDate(newReturnDate);
        assertEquals(newReturnDate, borrowedBook.getReturnDate());
    }
}