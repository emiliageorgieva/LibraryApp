import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void testGetFirstName() {
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980, 1, 1), (LocalDate) null );
        assertEquals("John", author.getFirstName());
    }

    @Test
    void testSetFirstName() {
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980,1,1), (LocalDate) null);
        author.setFirstName("Jane");
        assertEquals("Jane", author.getFirstName());
    }

    @Test
    void testGetLastName() {
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980,1,1), (LocalDate) null);
        assertEquals("Doe", author.getLastName());
    }

    @Test
    void testSetLastName() {
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980, 1, 1), (LocalDate) null);
        author.setLastName("Smith");
        assertEquals("Smith", author.getLastName());
    }

    @Test
    void testGetCountry() {
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980, 1, 1), (LocalDate) null);
        assertEquals("USA", author.getCountry());
    }

    @Test
    void testSetCountry() {
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980, 1, 1), (LocalDate) null);
        author.setCountry("Canada");
        assertEquals("Canada", author.getCountry());
    }

    @Test
    void testGetDateOfBirth() {
        LocalDate dateOfBirth = LocalDate.of(1980, 1, 1);
        Author author = new Author("John", "Doe", "USA", dateOfBirth, (LocalDate) null);
        assertEquals(dateOfBirth, author.getDateOfBirth());
    }

    @Test
    void testSetDateOfBirth() {
        LocalDate dateOfBirth = LocalDate.of(1980, 1, 1);
        Author author = new Author("John", "Doe", "USA", null, (LocalDate) null);
        author.setDateOfBirth(dateOfBirth);
        assertEquals(dateOfBirth, author.getDateOfBirth());
    }

    @Test
    void testGetDateOfDeath() {
        LocalDate dateOfDeath = LocalDate.of(2022, 6, 1);
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980, 1, 1), dateOfDeath);
        assertEquals(dateOfDeath, author.getDateOfDeath());
    }

    @Test
    void testSetDateOfDeath() {
        LocalDate dateOfDeath = LocalDate.of(2022, 6, 1);
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980, 1, 1), null);
        author.setDateOfDeath(dateOfDeath);
        assertEquals(dateOfDeath, author.getDateOfDeath());
    }
    @Test
    public void testConstructor() {
        Author author = new Author("John", "Doe", "USA", LocalDate.of(1980, 1, 1), null);
        assertEquals("John", author.getFirstName());
        assertEquals("Doe", author.getLastName());
        assertEquals("USA", author.getCountry());
        assertEquals(LocalDate.of(1980, 1, 1), author.getDateOfBirth());
        assertNull(author.getDateOfDeath());
    }
}
