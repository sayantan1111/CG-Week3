import java.util.ArrayList;
import java.util.List;

abstract class LibraryItem {
    private String itemId;
    private String title;
    private String author;

    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public abstract int getLoanDuration();

    public void getItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Loan Duration: " + getLoanDuration() + " days");
    }
}

class Book extends LibraryItem {
    private int pages;

    public Book(String itemId, String title, String author, int pages) {
        super(itemId, title, author);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public int getLoanDuration() {
        return 21;
    }
}

class Magazine extends LibraryItem {
    private String issueDate;

    public Magazine(String itemId, String title, String author, String issueDate) {
        super(itemId, title, author);
        this.issueDate = issueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    @Override
    public int getLoanDuration() {
        return 7;
    }
}

class DVD extends LibraryItem {
    private int duration;

    public DVD(String itemId, String title, String author, int duration) {
        super(itemId, title, author);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int getLoanDuration() {
        return 3;
    }
}

interface Reservable {
    void reserveItem();
    boolean checkAvailability();
}

class LibrarY {
    public static void main(String[] args) {
        List<LibraryItem> libraryItems = new ArrayList<>();
        libraryItems.add(new Book("B001", "The Lord of the Rings", "J.R.R. Tolkien", 1200));
        libraryItems.add(new Magazine("M002", "National Geographic", "Various", "April 2025"));
        libraryItems.add(new DVD("D003", "Inception", "Christopher Nolan", 148));

        for (LibraryItem item : libraryItems) {
            item.getItemDetails();
            System.out.println("--------------------");
        }

        LibraryItem book = new Book("B004", "Pride and Prejudice", "Jane Austen", 432);
        System.out.println("Loan duration for " + book.getTitle() + ": " + book.getLoanDuration() + " days");

        LibraryItem magazine = new Magazine("M005", "Time", "Various", "May 2025");
        System.out.println("Loan duration for " + magazine.getTitle() + ": " + magazine.getLoanDuration() + " days");
    }
}