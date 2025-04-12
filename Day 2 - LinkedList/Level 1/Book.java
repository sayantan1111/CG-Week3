class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    Book head;
    Book tail;

    public Library() {
        this.head = null;
        this.tail = null;
    }

    public void addBookAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = newBook;
            tail = newBook;
            return;
        }
        newBook.next = head;
        head.prev = newBook;
        head = newBook;
    }

    public void addBookAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = newBook;
            tail = newBook;
            return;
        }
        newBook.prev = tail;
        tail.next = newBook;
        tail = newBook;
    }

    public void addBookAtPosition(int position, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (position <= 0) {
            addBookAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            if (position == 1) {
                head = newBook;
                tail = newBook;
            }
            return;
        }
        Book current = head;
        int count = 1;
        while (count < position - 1 && current != null) {
            current = current.next;
            count++;
        }
        if (current == null) {
            if (count == position - 1) {
                addBookAtEnd(title, author, genre, bookId, isAvailable);
            }
            return;
        }
        newBook.next = current.next;
        newBook.prev = current;
        if (current.next != null) {
            current.next.prev = newBook;
        } else {
            tail = newBook;
        }
        current.next = newBook;
    }

    public void removeBook(int bookId) {
        if (head == null) {
            return;
        }
        if (head.bookId == bookId) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            return;
        }
        Book current = head;
        while (current != null && current.bookId != bookId) {
            current = current.next;
        }
        if (current == null) {
            return;
        }
        if (current == tail) {
            tail = tail.prev;
            tail.next = null;
            return;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
    }

    public void searchBookByTitle(String title) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                System.out.println("Title: " + current.title);
                System.out.println("Author: " + current.author);
                System.out.println("Genre: " + current.genre);
                System.out.println("Book ID: " + current.bookId);
                System.out.println("Availability: " + (current.isAvailable ? "Available" : "Borrowed"));
                return;
            }
            current = current.next;
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void searchBookByAuthor(String author) {
        Book current = head;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) {
                System.out.println("Title: " + current.title);
                System.out.println("Author: " + current.author);
                System.out.println("Genre: " + current.genre);
                System.out.println("Book ID: " + current.bookId);
                System.out.println("Availability: " + (current.isAvailable ? "Available" : "Borrowed"));
                return;
            }
            current = current.next;
        }
        System.out.println("Book by author '" + author + "' not found.");
    }

    public void updateAvailability(int bookId, boolean isAvailable) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                current.isAvailable = isAvailable;
                System.out.println("Availability updated for book ID " + bookId + " to " + (isAvailable ? "Available" : "Borrowed"));
                return;
            }
            current = current.next;
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void displayForward() {
        Book current = head;
        if (current == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("Books in forward order:");
        while (current != null) {
            System.out.println("Title: " + current.title);
            System.out.println("Author: " + current.author);
            System.out.println("Genre: " + current.genre);
            System.out.println("Book ID: " + current.bookId);
            System.out.println("Availability: " + (current.isAvailable ? "Available" : "Borrowed"));
            System.out.println("--------------------");
            current = current.next;
        }
    }

    public void displayBackward() {
        Book current = tail;
        if (current == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("Books in reverse order:");
        while (current != null) {
            System.out.println("Title: " + current.title);
            System.out.println("Author: " + current.author);
            System.out.println("Genre: " + current.genre);
            System.out.println("Book ID: " + current.bookId);
            System.out.println("Availability: " + (current.isAvailable ? "Available" : "Borrowed"));
            System.out.println("--------------------");
            current = current.prev;
        }
    }

    public int countBooks() {
        int count = 0;
        Book current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addBookAtEnd("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 101, true);
        library.addBookAtBeginning("Pride and Prejudice", "Jane Austen", "Romance", 102, false);
        library.addBookAtPosition(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", 103, true);
        library.displayForward();
        library.displayBackward();
        System.out.println("Total number of books: " + library.countBooks());
        library.searchBookByTitle("Pride and Prejudice");
        library.searchBookByAuthor("J.R.R. Tolkien");
        library.updateAvailability(101, false);
        library.displayForward();
        library.removeBook(102);
        System.out.println("After removing Pride and Prejudice:");
        library.displayForward();
        System.out.println("Total number of books: " + library.countBooks());
    }
}