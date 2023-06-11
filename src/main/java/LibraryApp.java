import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        User.askForConsent();
        //  library.initializeBooks();
        //  library.initializeUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library App!");

        int choice;
        do {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. Borrow a Book");
                System.out.println("2. Return a Book");
                System.out.println("3. View Borrowed Books");
                System.out.println("4. View Overdue Books");
                System.out.println("5. Postpone Return Date");
                System.out.println("6. Search Books");
                System.out.println("7. Exit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter your username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter the title of the book you want to borrow: ");
                        String bookTitle = scanner.nextLine();
                        User user = library.findUserByUsername(username);
                        Book book = library.getBookByTitle(bookTitle);
                        if (user != null && book != null) {
                            library.borrowBook(user, book);
                            if (library.getBorrowedBooks(user).contains(book)) {
                                System.out.println("Book successfully borrowed by the user.");
                            } else {
                                System.out.println("Failed to borrow the book. Please check eligibility and availability.");
                            }
                        } else {
                            System.out.println("Invalid username or book title.");
                        }
                        break;
                    case 2:
                        System.out.println("Return a Book");
                        System.out.print("Enter your username: ");
                        String returnUsername = scanner.nextLine();
                        User user2 = library.findUserByUsername(returnUsername);
                        if (user2 != null) {
                            System.out.print("Enter the name of the book to return: ");
                            String bookTitle2 = scanner.nextLine();
                            Book selectedBook2 = library.getBookByTitle(bookTitle2);
                            if (selectedBook2 != null) {
                                boolean isReturned = library.returnBook(user2, selectedBook2);
                                if (isReturned) {
                                    System.out.println("Book successfully returned.");
                                } else {
                                    System.out.println("Failed to return the book. Please check your borrowed books.");
                                }
                            } else {
                                System.out.println("Book not found.");
                            }
                        } else {
                            System.out.println("User not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter your username: ");
                        username = scanner.nextLine();
                        user = library.findUserByUsername(username);
                        if (user != null) {
                            List<Book> borrowedBooks = library.getBorrowedBooks(user);
                            if (!borrowedBooks.isEmpty()) {
                                System.out.println("Borrowed Books:");
                                for (Book borrowedBook : borrowedBooks) {
                                    Date returnDate = user.getDueDate(borrowedBook); //make it LocalDate
                                    if (returnDate != null) {
                                        System.out.println(borrowedBook.getTitle() + " (Return Date: " + returnDate + ")");
                                    } else {
                                        System.out.println(borrowedBook.getTitle());
                                    }
                                }
                            } else {
                                System.out.println("You have not borrowed any books.");
                            }
                        } else {
                            System.out.println("Invalid username.");
                        }
                        break;
                    case 4:
                        System.out.println("View Overdue Books");
                        System.out.print("Enter your username: ");
                        String username2 = scanner.nextLine();
                        User user4 = library.findUserByUsername(username2);
                        if (user4 != null) {
                            List<Book> overdueBooks = library.getOverdueBooks(user4);
                            if (overdueBooks.isEmpty()) {
                                System.out.println("No overdue books found.");
                            } else {
                                System.out.println("Overdue Books:");
                                for (Book overdueBook : overdueBooks) {
                                    System.out.println(overdueBook.getTitle());
                                }
                            }
                        } else {
                            System.out.println("User not found.");
                        }
                        break;
                    case 5:
                        System.out.println("Postpone Return Date");
                        System.out.print("Enter your username: ");
                        username = scanner.nextLine();
                        user = library.findUserByUsername(username);
                        if (user != null) {
                            List<Book> borrowedBooks = library.getBorrowedBooks(user);
                            if (!borrowedBooks.isEmpty()) {
                                System.out.println("Borrowed Books:");
                                int bookIndex = 1;
                                for (Book borrowedBook : borrowedBooks) {
                                    System.out.println(bookIndex + ". " + borrowedBook.getTitle());
                                    bookIndex++;
                                }
                                System.out.print("Select a book to postpone the return date (enter the book number): ");
                                int bookNumber = Integer.parseInt(scanner.nextLine());

                                if (bookNumber >= 1 && bookNumber <= borrowedBooks.size()) {
                                    Book selectedBook = borrowedBooks.get(bookNumber - 1);

                                    System.out.print("Enter the number of days to postpone the return date: ");
                                    int days = Integer.parseInt(scanner.nextLine());
                                    boolean isPostponed = library.postponeReturnDate(user, selectedBook, days);
                                    if (isPostponed) {
                                        System.out.println("Return date postponed successfully.");
                                    } else {
                                        System.out.println("Failed to postpone the return date.");
                                    }
                                } else {
                                    System.out.println("Invalid book number.");
                                }
                            } else {
                                System.out.println("You have no borrowed books.");
                            }
                        } else {
                            System.out.println("Invalid username.");
                        }
                        break;
                    case 6:
                        System.out.println("Search Books");
                        System.out.print("Enter a keyword to search for books: ");
                        String keyword = scanner.nextLine();
                        List<Book> searchResults = library.searchBooks(keyword);
                        if (!searchResults.isEmpty()) {
                            System.out.println("Search Results:");
                            for (Book searchBook : searchResults) {
                                System.out.println(searchBook.getTitle() + " by " + searchBook.getAuthors());
                            }
                        } else {
                            System.out.println("No books found matching the search criteria.");
                        }
                        break;
                    case 7:
                        System.out.println("Exiting the Library App. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 7.");
                        break;
                }
                if (choice != 7) {
                    System.out.print("Do you want to choose another option? (yes/no): ");
                    String continueChoice = scanner.nextLine();
                    if (!continueChoice.equalsIgnoreCase("yes")) {
                        choice = 7;
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                choice = 7;
            }
        }   while (choice != 7);
        scanner.close();
    }
}