import java.time.LocalDate;

public class BorrowedBook {
    private final User user;
    private final Book book;
    private final LocalDate borrowedDate;
    private LocalDate returnDate;
    private int postponementDays;
    private final int delayDays;

    public static final int MAX_DELAY_DAYS = 14;

    public BorrowedBook(User user, Book book) {
        this.user = user;
        this.book = book;
        this.borrowedDate = LocalDate.now();
        this.returnDate = borrowedDate.plusDays(book.getLoanDuration());
        this.postponementDays = 0;
        this.delayDays = 0;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean canPostpone() {
        return postponementDays < MAX_DELAY_DAYS;
    }
    public void requestPostponement(int days) {
        if (canPostpone()) {
            int remainingDelayDays = MAX_DELAY_DAYS - postponementDays;
            int actualPostponementDays = Math.min(remainingDelayDays, days);
            postponementDays += actualPostponementDays;
        } else {
            throw new IllegalArgumentException("Cannot request postponement. Maximum postponement days reached.");
        }
    }

    public LocalDate getFinalReturnDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate finalReturnDate = returnDate.plusDays(delayDays);
        if (currentDate.isAfter(finalReturnDate)) {
            return currentDate;
        }
        return finalReturnDate;
    }

    public int getDelayDays() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(getFinalReturnDate())) {
            return (int) (currentDate.toEpochDay() - getFinalReturnDate().toEpochDay());
        }
        return 0;
    }

    public void setReturnDate(LocalDate returnDate) {
        if (returnDate.isBefore(borrowedDate)) {
            throw new IllegalArgumentException("Return date cannot be before the borrowed date.");
        }
        this.returnDate = returnDate;
    }
}