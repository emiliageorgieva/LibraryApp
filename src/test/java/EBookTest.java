import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EBookTest {

    @Test
    void testGetOnlineReadLink() {
        EBook eBook = new EBook("Title", null, null, null, null, null, "https://example.com/read", true, "https://example.com/download");
        assertEquals("https://example.com/read", eBook.getOnlineReadLink());
    }

    @Test
    void testSetOnlineReadLink() {
        EBook eBook = new EBook("Title", null, null, null, null, null, null, true, "https://example.com/download");
        eBook.setOnlineReadLink("https://example.com/read");
        assertEquals("https://example.com/read", eBook.getOnlineReadLink());
    }

    @Test
    void testGetDownloadLink() {
        EBook eBook = new EBook("Title", null, null, null, null, null, "https://example.com/read", true, "https://example.com/download");
        assertEquals("https://example.com/download", eBook.getDownloadLink());
    }

    @Test
    void testSetDownloadLink() {
        EBook eBook = new EBook("Title", null, null, null, null, null, "https://example.com/read", true, null);
        eBook.setDownloadLink("https://example.com/download");
        assertEquals("https://example.com/download", eBook.getDownloadLink());
    }

    @Test
    void testGetLoanDuration() {
        EBook eBook = new EBook("Title", null, null, null, null, null, "https://example.com/read", true, "https://example.com/download");
        assertEquals(14, eBook.getLoanDuration());
    }

    @Test
    void testIsDownloadable() {
        EBook downloadableEBook = new EBook("Title", null, null, null, null, null, "https://example.com/read", true, "https://example.com/download");
        assertTrue(downloadableEBook.isDownloadable());

        EBook nonDownloadableEBook = new EBook("Title", null, null, null, null, null, "https://example.com/read", false, "https://example.com/download");
        assertFalse(nonDownloadableEBook.isDownloadable());
    }
}