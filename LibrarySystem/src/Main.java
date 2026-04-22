import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        Member Member1 = new Member("M1", "Ruhul", "ruhul@gmail.com");
//        Member1.displayInfo();
//        Member1.addBook("Rich Dad");
//        Member1.addBook("clean code");
//        Member1.displayInfo();

        Library library = new Library();

        // Books add
        library.addBook(new Book("B1", "Never Stop L", "Ayman sadik"));
        library.addBook(new Book("B2", "Java Basic", "Ruhul"));

        // Member register
        library.registerMember(new Member("M1", "Rahim", "rahim@gmail.com"));

        // view
        library.viewAllBooks();
        library.viewAllMembers();

        // Issue
        library.issueBook("M1", "B1");

        // view again
        library.viewAllBooks();
        library.viewAllTransactions();

        // Return
        library.returnBook("T1");

        library.viewAllBooks();
        library.viewAllTransactions();
    }
}
