import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    String transactionID;
    String memberID;
    String bookID;
    LocalDate issueDate;
    LocalDate dueDate;
    LocalDate returnDate;
    double fineAmount;

    public Transaction(String transactionID, String memberID, String bookID) {
        this.transactionID = transactionID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(3);
        this.returnDate = null;
        this.fineAmount = 0.0;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void displayInfo(){
        System.out.println(
                "Transaction ID: " + transactionID
               + ", Member ID: " + memberID
                + ", Book ID: " + bookID
                + ", Date of Issue: " + issueDate
                + ", Date of Return: " + (returnDate != null ? returnDate : "Not Returned")
                + ", Fine Amount: " + fineAmount + " taka"
        );


    }
}
