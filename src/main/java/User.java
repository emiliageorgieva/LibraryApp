import java.time.LocalDate;
import java.util.*;

public class User {

    private List<Book> borrowedBooks = new ArrayList<>();
    private List<Book> readBooks;
    private List<Book> downloadedBooks;
    private Map<Book, Date> dueDates;
    private int borrowingLimit;
    protected static boolean hasConsentOfGDPR;
    private List<EBook> borrowedEBooks;

    private String username;
    private String password;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String city;
    private String country;
    private String email;

    public User(String username, String password, String name, int age, String gender, String address, String city, String country, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.dueDates = new HashMap<>();
        this.borrowedEBooks = new ArrayList<>();
        this.readBooks = new ArrayList<>();
        this.downloadedBooks = new ArrayList<>();
    }
    public Date getDueDate(Book book) {
        return dueDates.get(book);
    }
    public void setDueDate(Book book, Date dueDate) {
        dueDates.put(book, dueDate);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public User() {
        borrowedBooks = new ArrayList<>();
        readBooks = new ArrayList<>();
        downloadedBooks = new ArrayList<>();
        borrowingLimit = calculateBorrowingLimit();
        hasConsentOfGDPR = false;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    private int calculateBorrowingLimit() {

        int baseLimit = 5;
        int additionalLimit = 0;
        return baseLimit + additionalLimit;
    }
    public void readBookOnline(Book book) {
        if (readBooks == null) {
            readBooks = new ArrayList<>();
        }
        readBooks.add(book);
    }
    public void downloadBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");

        }
        if (downloadedBooks == null) {
            downloadedBooks = new ArrayList<>();
        }
        downloadedBooks.add(book);
    }

    public List<Book> getReadBooks() {
        return readBooks;
    }

    public List<Book> getDownloadedBooks() {
        return downloadedBooks;
    }

    public static void askForConsent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you give consent to manage your data? (yes/no)");
        String consent = scanner.nextLine();
        if (consent.equalsIgnoreCase("yes")) {
            hasConsentOfGDPR = true;
            System.out.println("Thank you for giving consent.");
        } else {
            hasConsentOfGDPR = false;
            System.out.println("You have not given consent to manage your data.");
        }
    }

    public boolean canBorrowEBook() {
        return borrowedEBooks.size() < borrowingLimit;
    }

    public void addBorrowedEBook(EBook eBook) {
        borrowedEBooks.add(eBook);
    }
}
