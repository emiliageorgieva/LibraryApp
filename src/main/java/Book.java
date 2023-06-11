import java.lang.IllegalArgumentException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    private String title;
    private List<Author> authors;
    private String genre;
    private String summary;
    private String ISBN;
    private List<String> tags;
    private int loanDuration;
    private final List<BorrowedBook> borrowedBooks;

    public Book(String title, List<Author> authors, String genre, String summary, String ISBN, List<String> tags) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.summary = summary;
        this.ISBN = ISBN;
        this.tags = tags;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getLoanDuration() {
        if (this instanceof PaperBook) {
            return 14;
        } else if (this instanceof EBook) {
            return 14;
        } else {
            return 14;
        }
    }

    public LocalDate getFinalReturnDate(User user) throws IllegalArgumentException {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getUser().equals(user)) {
                return borrowedBook.getFinalReturnDate();
            }
        }
        throw new IllegalArgumentException("The book is not currently borrowed by the user.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book otherBook = (Book) obj;
        return Objects.equals(title, otherBook.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
