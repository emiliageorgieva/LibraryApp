import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaperBookTest {

    @Test
    void testGetTotalCopies() {
        PaperBook paperBook = new PaperBook("Title", null, null, null, null, null, 5, 3);
        assertEquals(5, paperBook.getTotalCopies());
    }

    @Test
    void testSetTotalCopies() {
        PaperBook paperBook = new PaperBook("Title", null, null, null, null, null, 5, 3);
        paperBook.setTotalCopies(7);
        assertEquals(7, paperBook.getTotalCopies());
    }

    @Test
    void testGetAvailableCopies() {
        PaperBook paperBook = new PaperBook("Title", null, null, null, null, null, 5, 3);
        assertEquals(3, paperBook.getAvailableCopies());
    }

    @Test
    void testSetAvailableCopies() {
        PaperBook paperBook = new PaperBook("Title", null, null, null, null, null, 5, 3);
        paperBook.setAvailableCopies(2);
        assertEquals(2, paperBook.getAvailableCopies());
    }

    @Test
    void testGetLoanDuration() {
        PaperBook paperBook = new PaperBook("Title", null, null, null, null, null, 5, 3);
        assertEquals(14, paperBook.getLoanDuration());
    }
}