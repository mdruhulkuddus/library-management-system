import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadSampleData();

        // main menu loop
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    addBookFlow();
                    break;
                case 2:
                    library.viewAllBooks();
                    break;
                case 3:
                    searchBooksFlow();
                    break;
                case 4:
                    registerMemberFlow();
                    break;
                case 5:
                    library.viewAllMembers();
                    break;
                case 6:
                    issueBookFlow();
                    break;
                case 7:
                    returnBookFlow();
                    break;
                case 8:
                    library.viewAllTransactions();
                    break;
                case 9:
                    System.out.println("\nThank you for using Library Management System!");
                    return;  // Exit
                default:
                    System.out.println("Invalid choice. Please Try again.\n");
            }
        }
    }

    // ========== MENU DISPLAY ==========

    private static void displayMenu() {
        System.out.println("\n==================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==================================");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Books");
        System.out.println("4. Register Member");
        System.out.println("5. View All Members");
        System.out.println("6. Issue Book");
        System.out.println("7. Return Book");
        System.out.println("8. View All Transactions");
        System.out.println("9. Exit");
        System.out.println("==================================");
    }

    // ========== INPUT HANDLING ==========

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            input = scanner.nextLine().trim();
        }

        return input;
    }
    private static int getIntInput(String prompt) {
        System.out.print(prompt);

        try {
            int input = Integer.parseInt(scanner.nextLine().trim());
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getIntInput(prompt);  // Recursive call
        }
    }

    // ========== SAMPLE DATA ==========

    private static void loadSampleData() {
        // Pre-load books
        library.addBook(new Book("B1", "Java Basics", "James Gosling"));
        library.addBook(new Book("B2", "Clean Code", "Robert Martin"));
        library.addBook(new Book("B3", "Design Patterns", "Gang of Four"));

        // Pre-load members
        library.registerMember(new Member("M1", "Rahim", "rahim@mail.com"));
        library.registerMember(new Member("M2", "Fatima", "fatima@mail.com"));

        System.out.println("Sample data loaded successfully!\n");
    }

    // ========== BOOK FLOWS ==========

    private static void addBookFlow() {
        System.out.println("\n--- Add New Book ---");
        String id = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Book Title: ");
        String author = getStringInput("Enter Author Name: ");

        Book book = new Book(id, title, author);
        library.addBook(book);
    }

    private static void searchBooksFlow() {
        System.out.println("\n--- Search Books ---");
        String keyword = getStringInput("Enter book title or author name: ");
        library.searchBooks(keyword);
    }

    // ========== MEMBER FLOWS ==========

    private static void registerMemberFlow() {
        System.out.println("\n--- Register New Member ---");
        String id = getStringInput("Enter Member ID: ");
        String name = getStringInput("Enter Name: ");
        String email = getStringInput("Enter Email: ");

        Member member = new Member(id, name, email);
        library.registerMember(member);
    }

    // ========== ISSUE/RETURN FLOWS ==========

    private static void issueBookFlow() {
        System.out.println("\n--- Issue Book ---");
        String memberId = getStringInput("Enter Member ID: ");
        String bookId = getStringInput("Enter Book ID: ");

        library.issueBook(memberId, bookId);
    }

    private static void returnBookFlow() {
        System.out.println("\n--- Return Book ---");
        String transactionId = getStringInput("Enter Transaction ID: ");

        library.returnBook(transactionId);
    }


}
