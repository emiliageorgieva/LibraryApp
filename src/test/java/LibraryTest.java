import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        library = new Library();

        book1 = new Book("The Great Gatsby", List.of(
                new Author("Mario", "Puzo", "USA", LocalDate.of(1920, 10, 15), LocalDate.of(1999, 7, 2))
        ), "Fiction", "A classic tale of love and tragedy", "1234567890", List.of("classic"));
        book2 = new Book("To Kill a Mockingbird", List.of(
                new Author("Harper", "Lee", "USA", LocalDate.of(1926, 4, 28), LocalDate.of(2016, 2, 19))
        ), "Fiction", "A story of racial injustice and moral growth", "0987654321", List.of("classics"));
        library.addBook(book1);
        library.addBook(book2);
        user1 = new User("john", "pass", "John Doe", 26, "male", "Positano Str", "Sofia", "Bulgaria", "john@example.com");
        user2 = new User("jane", "word", "Jane Smith", 24, "female", "Stefan Danailov Str", "Sofia", "Bulgaria", "jane@example.com");
        library.addUser(user1);
        library.addUser(user2);
    }

    @Test
    public void testAddBook() {
        Book book3 = new Book("Book 3", List.of(
                new Author("Author", "Three", "USA", LocalDate.of(1980, 1, 1), LocalDate.now())
        ), "Fiction", "Description", "9876543210", List.of("tag"));
        library.addBook(book3);
        List<Book> books = library.searchBooksByTitle("Book 3");
        Assert.assertEquals(1, books.size());
        Assert.assertEquals(book3, books.get(0));
    }

    @Test
    public void testRemoveBook() {
        library.removeBook(book1);
        List<Book> books = library.searchBooksByTitle("The Great Gatsby");
        Assert.assertEquals(0, books.size());
    }

    @Test
    void testSearchBooksByTitle() {
        List<Book> matchingBooks = library.searchBooksByTitle("To Kill a Mockingbird");
        Assert.assertEquals(1, matchingBooks.size());
        Assert.assertEquals(book2, matchingBooks.get(0));
    }

    @Test
    void testSearchBooksByGenre() {
        List<Book> matchingBooks = library.searchBooksByGenre("Fiction");
        Assert.assertEquals(2, matchingBooks.size());
        Assert.assertTrue(matchingBooks.contains(book1));
        Assert.assertTrue(matchingBooks.contains(book2));
    }

    @Test
    void testSearchBooksByTag() {
        List<Book> matchingBooks = library.searchBooksByTag("classic");
        Assert.assertEquals(1, matchingBooks.size());
        Assert.assertEquals(book1, matchingBooks.get(0));
    }

    @Test
    void testSearchBooksByAuthor() {
        List<Book> matchingBooks = library.searchBooksByAuthor("Harper", "Lee");
        Assert.assertEquals(1, matchingBooks.size());
        Assert.assertEquals(book2, matchingBooks.get(0));
    }

    @Test
    public void testAddUser() {
        User user3 = new User("jack", "password", "Jack Johnson", 30, "male", "Main Street", "New York", "USA", "jack@example.com");
        library.addUser(user3);
        User foundUser = library.findUserByUsername("jack");
        Assert.assertEquals(user3, foundUser);
    }

    @Test
    public void testRemoveUser() {
        library.removeUser(user1);
        User foundUser = library.findUserByUsername("john");
        Assert.assertNull(foundUser);
    }
    @Test
    public void testBorrowBook() {
        library.borrowBook(user1, book1);
        List<Book> borrowedBooks = library.getBorrowedBooks(user1);
        Assert.assertEquals(1, borrowedBooks.size());
        Assert.assertEquals(book1, borrowedBooks.get(0));
    }

    @Test
    void testFindUserByUsername() {
        library.addUser(user1);
        library.addUser(user2);
        User foundUser = library.findUserByUsername(user2.getUsername());
        Assert.assertEquals(user2, foundUser);
    }

    @Test
    void testGetUserByEmail() {
        library.addUser(user1);
        library.addUser(user2);
        User foundUser = library.getUserByEmail(user2.getEmail());
        Assert.assertEquals(user2, foundUser);
    }

    @Test
    void testGetBookByTitle() {
        library.addBook(book1);
        library.addBook(book2);
        Book foundBook = library.getBookByTitle(book2.getTitle());
        Assert.assertEquals(book2, foundBook);
    }

    @Test
    void testReturnBook() {
        library.borrowBook(user1, book1);
        Assert.assertTrue(library.returnBook(user1, book1));
        Assert.assertTrue(library.canBorrowBook(user1, book1));
        Assert.assertFalse(library.getBorrowedBooks(user1).contains(book1));
    }

    @Test
    public void testGetBorrowedBooks() {
        library.borrowBook(user1, book1);
        library.borrowBook(user1, book2);
        List<Book> borrowedBooks = library.getBorrowedBooks(user1);
        Assert.assertEquals(2, borrowedBooks.size());
        Assert.assertTrue(borrowedBooks.contains(book1));
        Assert.assertTrue(borrowedBooks.contains(book2));
    }
    @Test
    public void testCanBorrowBook() {
    }
    @Test
    void testGetOverdueBooks() {
    }
    @Test
    void testRequestPostponement() {
    }
    @Test
    void testSearchBooks() {
    }
    @Test
    void testPostponeReturnDate() {
    }
}