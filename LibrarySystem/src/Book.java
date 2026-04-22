public class Book extends LibraryItem{
    private String author;

    public  Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    @Override
    public void displayInfo(){
        System.out.println("ID: " + getId() +
                "\nTitle: " + getTitle() +
                ", Author: " + getAuthor() +
                ", Status: " + (isAvailable() ? "Available" : "Borrowed")
        );
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
