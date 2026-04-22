import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    private List<String> borrowedBooksIds;
    private static final int MAX_BORROWED_BOOKS = 3;

    public Member(String id, String name, String email) {
        super(id, name, email); // person constructor call
        this.borrowedBooksIds = new ArrayList<>();
    }

    public List<String> getBorrowedBooksIds() {
        return borrowedBooksIds;
    }

    public void addBook(String bookId) {
        this.borrowedBooksIds.add(bookId);
    }
    public void removeBook(String bookId) {
        this.borrowedBooksIds.remove(bookId);
    }

    public boolean canBorrowMoreBook() {
        return borrowedBooksIds.size() < MAX_BORROWED_BOOKS;
    }

    @Override
    public void displayInfo() {
        System.out.println("Member ID: "+ getId()
                + ", Name: " + getName()
                + ", Email: " + getEmail()
                + ", Books Borrowed: " + borrowedBooksIds.size()
        );
    }

}
