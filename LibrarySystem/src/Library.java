import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Transaction> transactions;
    private List<Member> members;

    private static final int FINE_PER_DAY = 10;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.transactions = new  ArrayList<>();
    }

    // ========== Book Management ============

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void viewAllBooks() {
        if(books.isEmpty()) {
            System.out.println("No books in library");
            return;
        }
        System.out.println("\n-----All books in library-----");
        for(Book book : books) {
            book.displayInfo();
        }
    }

    public Book findBookByID(String bookID) {
        for(Book book : books) {
            if(book.getId().equals(bookID)) {
                return book;
            }
        }
        return null;
    }

    public void searchBooks(String keyword) {
        System.out.println("\n--- Search Results ---");
        boolean found = false;
        for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
            book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                book.displayInfo();
                found = true;
            }
        }
        if(!found) {
            System.out.println("No such book found with keyword: "+keyword);
        }
    }

    // ========== Member Management ============

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    public void viewAllMembers() {
        if(members.isEmpty()) {
            System.out.println("No members registered");
            return;
        }
        System.out.println("\n--- All Members ---");
        for(Member member : members) {
            member.displayInfo();
        }
    }

    public Member findMemberByID(String memberID) {
        for(Member member : members) {
            if(member.getId().equals(memberID)) {
                return member;
            }
        }
        return null;
    }

    // ========== Issue Book ============

    public void issueBook(String memberID, String bookID) {
        // Step 1: Member আছে কিনা
        Member member = findMemberByID(memberID);
        if(member == null) {
            System.out.println("Member not found with ID: "+ memberID);
            return;
        }

        // Step 2: is Book exist in library?
        Book book = findBookByID(bookID);
        if(book == null) {
            System.out.println("Book not found with ID: "+ bookID);
            return;
        }

        // Step 3: is Book avail?
        if(!book.isAvailable()) {
            System.out.println("Book is already borrowed");
            return;
        }

        // Step 4: Member আরো বই নিতে পারবে কিনা
        if(!member.canBorrowMoreBook()){
            System.out.println("Member reached borrow limit (3 books)");
            return;
        }

        // step 5: Transaction তৈরি (বই, আনা নেয়া হিস্টোরি)
        String transactionId = "T" + (transactions.size() + 1);
        Transaction transaction = new Transaction(transactionId, memberID, bookID);

        // step 6: Book-এর status update
        book.setAvailable(false);

        // step 7: Member-এর list-এ book add
        member.addBook(bookID);

        // step 8: Transaction list-এ save
        transactions.add(transaction);

        System.out.println("Book issued successfully!");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Due Date: " + transaction.getDueDate());
    }

    // ========== Return Book ============
    public void returnBook(String transactionID) {
        // Step 1: Transaction খোঁজা
        Transaction transaction = null;
        for(Transaction t : transactions) {
            if(t.getTransactionID().equals(transactionID)) {
                transaction = t;
                break;
            }
        }

        if(transaction == null) {
            System.out.println("No transaction found with ID: "+ transactionID);
            return;
        }

        // Step 2: Already returned কিনা
        if(transaction.isReturned()) {
            System.out.println("Book has been returned!");
            return;
        }

        // Step 3: Fine calculate
        LocalDate today = LocalDate.now();
        double fine = 0;
        if(today.isAfter(transaction.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(transaction.getDueDate(), today);
            fine = daysLate * FINE_PER_DAY;
        }

        // Step 4: Transaction update
        transaction.setReturnDate(today);
        transaction.setFineAmount(fine);

        // Step 5: Book available করা
        Book book = findBookByID(transaction.getBookID());
        if(book != null){
            book.setAvailable(true);
        }

        // Step 6: Member-এর list থেকে remove
        Member member = findMemberByID(transaction.getMemberID());
        if(member != null) {
            member.removeBook(transaction.getBookID());
        }

        System.out.println("Book has been returned Successfully!");

        if(fine > 0){
            System.out.println("Late return! Fine amount: " + fine + " Taka");
        }else {
            System.out.println("Return on time. No fine");
        }
    }

    // ========== TRANSACTIONS ==========
    public void viewAllTransactions() {
        if(transactions.isEmpty()) {
            System.out.println("No transactions registered");
            return;
        }
        System.out.println("\n-----All transactions in library-----");
        for(Transaction transaction : transactions) {
            transaction.displayInfo();
        }
    }

}
