import java.util.List;

public class PaperBook extends Book {
    private int totalCopies;
    private int availableCopies;

    public PaperBook(String title, List<Author> authors, String genre, String summary, String ISBN, List<String> tags, int totalCopies, int availableCopies) {
        super(title, authors, genre, summary, ISBN, tags);
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        if (totalCopies < 0) {
            throw new IllegalArgumentException("Total copies cannot be negative.");
        }
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        if (availableCopies < 0 || availableCopies > totalCopies) {
            throw new IllegalArgumentException("Invalid number of available copies.");
        }
        this.availableCopies = availableCopies;
    }

    @Override
    public int getLoanDuration() {
        return 14;
    }
}
