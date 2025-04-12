class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    Ticket head;

    public TicketReservationSystem() {
        this.head = null;
    }

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            head.next = head;
            return;
        }
        Ticket tail = head;
        while (tail.next != head) {
            tail = tail.next;
        }
        tail.next = newTicket;
        newTicket.next = head;
    }

    public void removeTicket(int ticketId) {
        if (head == null) {
            return;
        }
        if (head.ticketId == ticketId) {
            if (head.next == head) {
                head = null;
            } else {
                Ticket tail = head;
                while (tail.next != head) {
                    tail = tail.next;
                }
                head = head.next;
                tail.next = head;
            }
            return;
        }
        Ticket current = head;
        Ticket prev = null;
        while (current.next != head) {
            prev = current;
            current = current.next;
            if (current.ticketId == ticketId) {
                prev.next = current.next;
                return;
            }
        }
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        System.out.println("Current Booked Tickets:");
        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketId);
            System.out.println("Customer Name: " + current.customerName);
            System.out.println("Movie Name: " + current.movieName);
            System.out.println("Seat Number: " + current.seatNumber);
            System.out.println("Booking Time: " + current.bookingTime);
            System.out.println("--------------------");
            current = current.next;
        } while (current != head);
    }

    public void searchTicketByCustomerName(String customerName) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        boolean found = false;
        Ticket current = head;
        do {
            if (current.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket ID: " + current.ticketId);
                System.out.println("Customer Name: " + current.customerName);
                System.out.println("Movie Name: " + current.movieName);
                System.out.println("Seat Number: " + current.seatNumber);
                System.out.println("Booking Time: " + current.bookingTime);
                System.out.println("--------------------");
                found = true;
            }
            current = current.next;
        } while (current != head);
        if (!found) {
            System.out.println("No tickets found for customer: " + customerName);
        }
    }

    public void searchTicketByMovieName(String movieName) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        boolean found = false;
        Ticket current = head;
        do {
            if (current.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket ID: " + current.ticketId);
                System.out.println("Customer Name: " + current.customerName);
                System.out.println("Movie Name: " + current.movieName);
                System.out.println("Seat Number: " + current.seatNumber);
                System.out.println("Booking Time: " + current.bookingTime);
                System.out.println("--------------------");
                found = true;
            }
            current = current.next;
        } while (current != head);
        if (!found) {
            System.out.println("No tickets found for movie: " + movieName);
        }
    }

    public int getTotalBookedTickets() {
        if (head == null) {
            return 0;
        }
        int count = 0;
        Ticket current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);
        return count;
    }

    public static void main(String[] args) {
        TicketReservationSystem reservationSystem = new TicketReservationSystem();
        reservationSystem.addTicket(101, "John Doe", "Avengers: Endgame", "A10", "2025-04-16 10:00");
        reservationSystem.addTicket(102, "Jane Smith", "The Lion King", "B5", "2025-04-16 11:30");
        reservationSystem.addTicket(103, "Peter Jones", "Avengers: Endgame", "A11", "2025-04-16 10:05");
        reservationSystem.displayTickets();
        System.out.println("Total booked tickets: " + reservationSystem.getTotalBookedTickets());
        reservationSystem.searchTicketByCustomerName("Jane Smith");
        reservationSystem.searchTicketByMovieName("Avengers: Endgame");
        reservationSystem.removeTicket(102);
        System.out.println("After removing ticket 102:");
        reservationSystem.displayTickets();
        System.out.println("Total booked tickets: " + reservationSystem.getTotalBookedTickets());
    }
}