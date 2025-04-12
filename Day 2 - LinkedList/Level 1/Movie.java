class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieDoublyLinkedList {
    Movie head;
    Movie tail;

    public MovieDoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = newMovie;
            tail = newMovie;
            return;
        }
        newMovie.next = head;
        head.prev = newMovie;
        head = newMovie;
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = newMovie;
            tail = newMovie;
            return;
        }
        newMovie.prev = tail;
        tail.next = newMovie;
        tail = newMovie;
    }

    public void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 0) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            if (position == 1) {
                head = newMovie;
                tail = newMovie;
            }
            return;
        }
        Movie current = head;
        int count = 1;
        while (count < position - 1 && current != null) {
            current = current.next;
            count++;
        }
        if (current == null) {
            if (count == position - 1) {
                addAtEnd(title, director, year, rating);
            }
            return;
        }
        newMovie.next = current.next;
        newMovie.prev = current;
        if (current.next != null) {
            current.next.prev = newMovie;
        } else {
            tail = newMovie;
        }
        current.next = newMovie;
    }

    public void removeByTitle(String title) {
        if (head == null) {
            return;
        }
        if (head.title.equals(title)) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            return;
        }
        Movie current = head;
        while (current != null && !current.title.equals(title)) {
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

    public void searchByDirector(String director) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.director.equals(director)) {
                System.out.println("Title: " + current.title);
                System.out.println("Director: " + current.director);
                System.out.println("Year: " + current.year);
                System.out.println("Rating: " + current.rating);
                System.out.println("--------------------");
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found directed by " + director);
        }
    }

    public void searchByRating(double rating) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating == rating) {
                System.out.println("Title: " + current.title);
                System.out.println("Director: " + current.director);
                System.out.println("Year: " + current.year);
                System.out.println("Rating: " + current.rating);
                System.out.println("--------------------");
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found with rating " + rating);
        }
    }

    public void displayForward() {
        Movie current = head;
        if (current == null) {
            System.out.println("No movie records found.");
            return;
        }
        System.out.println("Movie records in forward order:");
        while (current != null) {
            System.out.println("Title: " + current.title);
            System.out.println("Director: " + current.director);
            System.out.println("Year: " + current.year);
            System.out.println("Rating: " + current.rating);
            System.out.println("--------------------");
            current = current.next;
        }
    }

    public void displayBackward() {
        Movie current = tail;
        if (current == null) {
            System.out.println("No movie records found.");
            return;
        }
        System.out.println("Movie records in reverse order:");
        while (current != null) {
            System.out.println("Title: " + current.title);
            System.out.println("Director: " + current.director);
            System.out.println("Year: " + current.year);
            System.out.println("Rating: " + current.rating);
            System.out.println("--------------------");
            current = current.prev;
        }
    }

    public void updateRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                current.rating = newRating;
                System.out.println("Rating updated for movie '" + title + "' to " + newRating);
                return;
            }
            current = current.next;
        }
        System.out.println("Movie with title '" + title + "' not found.");
    }

    public static void main(String[] args) {
        MovieDoublyLinkedList movieList = new MovieDoublyLinkedList();
        movieList.addAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        movieList.addAtBeginning("The Shawshank Redemption", "Frank Darabont", 1994, 9.3);
        movieList.addAtPosition(2, "Pulp Fiction", "Quentin Tarantino", 1994, 8.9);
        movieList.displayForward();
        movieList.displayBackward();
        movieList.searchByDirector("Christopher Nolan");
        movieList.searchByRating(8.9);
        movieList.removeByTitle("Pulp Fiction");
        System.out.println("After removing Pulp Fiction:");
        movieList.displayForward();
        movieList.updateRating("Inception", 9.0);
        movieList.searchByDirector("Christopher Nolan");
    }
}