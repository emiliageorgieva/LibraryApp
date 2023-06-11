import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    private final List<Book> books;
    private final List<User> users;
    private final List<BorrowedBook> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> searchBooksByGenre(String genre) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> searchBooksByTag(String tag) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTags().contains(tag)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> searchBooksByAuthor(String authorFirstName, String authorLastName) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                if (author.getFirstName().equalsIgnoreCase(authorFirstName) && author.getLastName().equalsIgnoreCase(authorLastName)) {
                    matchingBooks.add(book);
                    break;
                }
            }
        }
        return matchingBooks;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

//    public void initializeUsers() {
//        User user1 = new User("john", "pass", "John Doe", 26, "male", "Positano Str", "Sofia", "Bulgaria", "john@example.com");
//        User user2 = new User("jane", "word", "Jane Smith", 24, "female", "Stefan Danailov Str", "Sofia", "Bulgaria", "jane@example.com");
//
//        users.add(user1);
//        users.add(user2);
//    }
//
//    public void initializeBooks() {
//        Author author1 = new Author("Mario", "Puzo", "USA", LocalDate.of(1920, 10, 15), LocalDate.of(1999, 7, 2));
//        Author author2 = new Author("Harper", "Lee", "USA", LocalDate.of(1926, 4, 28), LocalDate.of(2016, 2, 19));
//
//        Book book1 = new Book("The Great Gatsby", List.of(author1), "Fiction", "A classic tale of love and tragedy", "1234567890", List.of("classic"));
//        Book book2 = new Book("To Kill a Mockingbird", List.of(author2), "Fiction", "A story of racial injustice and moral growth", "0987654321", List.of("classics"));
//
//        books.add(book1);
//        books.add(book2);
//
//        borrowedBooks = new ArrayList<>();
//    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(User user, Book book) {
        if (user == null || book == null) {
            return;
        }

        if (canBorrowBook(user, book)) {
            BorrowedBook borrowedBook = new BorrowedBook(user, book);
            LocalDate returnDate = calculateReturnDate();
            System.out.println("Return Date: " + returnDate);
            borrowedBook.setReturnDate(returnDate);
            borrowedBooks.add(borrowedBook);
            if (book instanceof PaperBook paperBook) {
                if (paperBook.getAvailableCopies() > 0) {
                    paperBook.setAvailableCopies(paperBook.getAvailableCopies() - 1);
                } else {
                    throw new IllegalArgumentException("No available copies of the book.");
                }
            } else if (book instanceof EBook eBook) {
                if (user.canBorrowEBook()) {
                    user.addBorrowedEBook(eBook);
                    System.out.println("eBook successfully borrowed by the user.");
                } else {
                    throw new IllegalArgumentException("User cannot borrow an eBook.");
                }
            }
        } else {
            throw new IllegalArgumentException("Failed to borrow the book. Please check eligibility and availability.");
        }
    }

    private LocalDate calculateReturnDate() {
        return LocalDate.now().plusDays(14);
    }

    public boolean returnBook(User user, Book book) {
        Iterator<BorrowedBook> iterator = borrowedBooks.iterator();

        while (iterator.hasNext()) {
            BorrowedBook borrowedBook = iterator.next();
            if (borrowedBook.getBook().equals(book) && borrowedBook.getUser().equals(user)) {
                iterator.remove();
                return true;
            }
        }

        throw new IllegalArgumentException("Book not found or not borrowed by the user.");
    }

    private BorrowedBook findBorrowedBook(User user, Book book) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getUser().equals(user) && borrowedBook.getBook().equals(book)) {
                return borrowedBook;
            }
        }
        return null;
    }

    public List<Book> getBorrowedBooks(User user) {
        List<Book> borrowedBooks = new ArrayList<>();
        for (BorrowedBook borrowedBook : this.borrowedBooks) {
            if (borrowedBook.getUser().equals(user)) {
                borrowedBooks.add(borrowedBook.getBook());
            }
        }
        return borrowedBooks;
    }

    public boolean canBorrowBook(User user, Book book) {
        if (borrowedBooks == null) {
            return true;
        }
        if (book instanceof PaperBook paperBook) {
            return paperBook.getAvailableCopies() > 0;
        } else if (book instanceof EBook eBook) {
            return eBook.isDownloadable() && eBook.getDownloadLink() != null;
        }
        return true;
    }

    public List<Book> getOverdueBooks(User user) {
        List<Book> overdueBooks = new ArrayList<>();
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getUser().equals(user) && borrowedBook.getReturnDate().isBefore(LocalDate.now())) {
                overdueBooks.add(borrowedBook.getBook());
            }
        }
        return overdueBooks;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> matchingBooks = new ArrayList<>();
        String lowercaseKeyword = keyword.toLowerCase();

        for (Book book : books) {
            final String bookTitle = book.getTitle().toLowerCase();
            final String bookGenre = book.getGenre().toLowerCase();

            if (bookTitle.contains(lowercaseKeyword)
                    || bookGenre.contains(lowercaseKeyword)
                    || book.getTags().stream().anyMatch(tag -> tag.toLowerCase().contains(lowercaseKeyword))
                    || book.getAuthors().stream()
                    .anyMatch(author -> author.getFirstName().toLowerCase().contains(lowercaseKeyword)
                            || author.getLastName().toLowerCase().contains(lowercaseKeyword))) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public boolean postponeReturnDate(User user, Book book, int days) {
        BorrowedBook borrowedBook = findBorrowedBook(user, book);
        if (borrowedBook != null) {
            LocalDate newReturnDate = borrowedBook.getReturnDate().plusDays(days);
            borrowedBook.setReturnDate(newReturnDate);
        }
        return true;
    }
}