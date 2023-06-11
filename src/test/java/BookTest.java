import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookTest {

    private Book book;
    private List<Author> authors;

    @BeforeEach
    public void setup() {
        authors = new ArrayList<>();
        authors.add(new Author("Author 1", "Author Last Name", "Bulgaria", LocalDate.of(1999,1,1), LocalDate.of(2022,3,3)));
        authors.add(new Author("Author 1", "Author Last Name2", "Bulgaria2", LocalDate.of(1979,3,1), LocalDate.of(2002,3,4)));
        List<String> tags = new ArrayList<>();
        tags.add("Tag 1");
        tags.add("Tag 2");
        book = new Book("Title", authors, "Genre", "Summary", "ISBN123", tags);
    }

    @Test
    public void testGetTitle() {
        String title = book.getTitle();
        Assertions.assertEquals("Title", title);
    }

    @Test
    public void testSetTitle() {
        book.setTitle("New Title");
        String title = book.getTitle();
        Assertions.assertEquals("New Title", title);
    }

    @Test
    public void testGetAuthors() {
        List<Author> bookAuthors = book.getAuthors();
        Assertions.assertEquals(authors, bookAuthors);
    }

    @Test
    public void testSetAuthors() {
        List<Author> newAuthors = new ArrayList<>();
        newAuthors.add((new Author("Author 1", "Author Last Name", "Bulgaria", LocalDate.of(1999,1,1), LocalDate.of(2022,3,3))));
        newAuthors.add((new Author("Author 2", "Author Last Name2", "Bulgaria2", LocalDate.of(1979,4,7), LocalDate.of(2012,4,3))));
        book.setAuthors(newAuthors);
        List<Author> bookAuthors = book.getAuthors();
        Assertions.assertEquals(newAuthors, bookAuthors);
    }

    @Test
    public void testGetGenre() {
        String genre = book.getGenre();
        Assertions.assertEquals("Genre", genre);
    }

    @Test
    public void testSetGenre() {
        book.setGenre("New Genre");
        String genre = book.getGenre();
        Assertions.assertEquals("New Genre", genre);
    }

    @Test
    public void testGetSummary() {
        String summary = book.getSummary();
        Assertions.assertEquals("Summary", summary);
    }

    @Test
    public void testSetSummary() {
        book.setSummary("New Summary");
        String summary = book.getSummary();
        Assertions.assertEquals("New Summary", summary);
    }

    @Test
    public void testGetISBN() {
        String isbn = book.getISBN();
        Assertions.assertEquals("ISBN123", isbn);
    }

    @Test
    public void testSetISBN() {
        book.setISBN("NewISBN");
        String isbn = book.getISBN();
        Assertions.assertEquals("NewISBN", isbn);
    }

    @Test
    public void testGetTags() {
        List<String> tags = book.getTags();
        List<String> expectedTags = new ArrayList<>();
        expectedTags.add("Tag 1");
        expectedTags.add("Tag 2");
        Assertions.assertEquals(expectedTags, tags);
    }

    @Test
    public void testSetTags() {
        List<String> newTags = new ArrayList<>();
        newTags.add("New Tag 1");
        newTags.add("New Tag 2");
        book.setTags(newTags);
        List<String> tags = book.getTags();
        Assertions.assertEquals(newTags, tags);
    }

    @Test
    public void testGetBorrowedBooks() {
        List<BorrowedBook> borrowedBooks = book.getBorrowedBooks();
        Assertions.assertNotNull(borrowedBooks);
        Assertions.assertTrue(borrowedBooks.isEmpty());
    }
    @Test
    public void testGetLoanDurationForPaperBook() {
        PaperBook paperBook = new PaperBook("Title", authors, "Genre", "Summary", "ISBN123", new ArrayList<>(), 5, 4);
        int loanDuration = paperBook.getLoanDuration();
        Assertions.assertEquals(14, loanDuration);
    }

    @Test
    public void testGetLoanDurationForEBook() {
        Book eBook = new EBook("Title", authors, "Genre", "Summary", "ISBN123", new ArrayList<>(), "URL", true, "www.downloadLink.com");
        int loanDuration = eBook.getLoanDuration();
        Assertions.assertEquals(14, loanDuration);
    }
    @Test
    public void testGetLoanDurationForOtherBook() {
        Book otherBook = new Book("Title", authors, "Genre", "Summary", "ISBN123", new ArrayList<>());
        int loanDuration = otherBook.getLoanDuration();
        Assertions.assertEquals(14, loanDuration);
    }

    @Test
    public void testGetFinalReturnDateForBorrowedBook() {
        User user = new User();
        BorrowedBook borrowedBook = new BorrowedBook(user, book);
        LocalDate returnDate = LocalDate.now().plusDays(7);
        borrowedBook.setReturnDate(returnDate);
        book.getBorrowedBooks().add(borrowedBook);
        LocalDate finalReturnDate = book.getFinalReturnDate(user);
        Assertions.assertEquals(returnDate, finalReturnDate);
    }

    @Test
    public void testGetFinalReturnDateForNonBorrowedBook() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                book.getFinalReturnDate(user)
        );
    }

    @Test
    public void testEqualsWithSameObject() {
        Assertions.assertEquals(book, book);
    }

    @Test
    public void testEqualsWithEqualBooks() {
        Book otherBook = new Book("Title", authors, "Genre", "Summary", "ISBN123", new ArrayList<>());
        Assertions.assertEquals(book, otherBook);
    }

    @Test
    public void testEqualsWithDifferentBooks() {
        Book otherBook = new Book("Different Title", authors, "Genre", "Summary", "ISBN123", new ArrayList<>());
        Assertions.assertNotEquals(book, otherBook);
    }

    @Test
    public void testHashCode() {
        Book otherBook = new Book("Title", authors, "Genre", "Summary", "ISBN123", new ArrayList<>());
        Assertions.assertEquals(book.hashCode(), otherBook.hashCode());
    }

}