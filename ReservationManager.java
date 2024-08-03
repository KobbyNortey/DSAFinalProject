import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationManager {
    /* Map to store reservations keyed by date and time */
    private final MyHashMap<LocalDateTime, MyArrayList<Reservation>> reservationsByDateTime;
    /* Maximum capacity of tables available */
    private final int tableCapacity;

    /* Constructor to initialize the reservation manager with the given table capacity */
    public ReservationManager(int tableCapacity) {
        this.reservationsByDateTime = new MyHashMap<>();
        this.tableCapacity = tableCapacity;
    }

    /* Method to book a table for a given customer name, date, time, and number of people */
    public String bookTable(String customerName, LocalDate date, LocalTime time, int numPeople) {
        /* Validate inputs */
        if (customerName == null || customerName.trim().isEmpty()) {
            return "Invalid customer name.";
        }
        if (date == null || time == null) {
            return "Invalid date or time.";
        }
        if (numPeople <= 0) {
            return "Number of people must be greater than zero.";
        }

        /* Combine date and time into a single LocalDateTime object */
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        MyArrayList<Reservation> reservations = reservationsByDateTime.get(dateTime);

        /* If no reservations exist for this date and time, create a new list */
        if (reservations == null) {
            reservations = new MyArrayList<>();
            reservationsByDateTime.put(dateTime, reservations);
        }

        /* Calculate the total occupied seats for the given date and time */
        int occupiedSeats = 0;
        for (int i = 0; i < reservations.size(); i++) {
            occupiedSeats += reservations.get(i).getNumPeople();
        }

        /* Check if there is enough capacity to accommodate the new reservation */
        if (occupiedSeats + numPeople <= tableCapacity) {
            /* Create a new reservation and add it to the list */
            Reservation reservation = new Reservation(customerName, date, time, numPeople);
            reservations.add(reservation);
            return "Reservation successful: " + reservation;
        } else {
            return "No available tables for the specified date and time.";
        }
    }

    /* Method to cancel a reservation by its ID */
    public String cancelReservation(int reservationId) {
        MyArrayList<LocalDateTime> keys = reservationsByDateTime.keySet();
        for (int i = 0; i < keys.size(); i++) {
            LocalDateTime key = keys.get(i);
            MyArrayList<Reservation> reservations = reservationsByDateTime.get(key);
            for (int j = 0; j < reservations.size(); j++) {
                Reservation reservation = reservations.get(j);
                if (reservation.getReservationId() == reservationId) {
                    /* Remove the reservation from the list */
                    reservations.remove(reservation);
                    return "Reservation cancelled: " + reservation;
                }
            }
        }
        return "Reservation not found.";
    }

    /* Method to view reservations based on a filter (either date or customer name) */
    public MyArrayList<Reservation> viewReservations(String filter) {
        MyArrayList<Reservation> filteredReservations = new MyArrayList<>();
        MyArrayList<LocalDateTime> keys = reservationsByDateTime.keySet();
        for (int i = 0; i < keys.size(); i++) {
            LocalDateTime key = keys.get(i);
            MyArrayList<Reservation> reservations = reservationsByDateTime.get(key);
            for (int j = 0; j < reservations.size(); j++) {
                Reservation reservation = reservations.get(j);
                if (reservation.getDate().toString().equals(filter) ||
                    reservation.getCustomerName().equalsIgnoreCase(filter)) {
                    filteredReservations.add(reservation);
                }
            }
        }
        return filteredReservations;
    }

    /* Method to check the availability of tables for a given date and time */
    public int checkAvailability(LocalDate date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        MyArrayList<Reservation> reservations = reservationsByDateTime.get(dateTime);
        if (reservations == null) {
            return tableCapacity;
        }

        /* Calculate the total occupied seats */
        int occupiedSeats = 0;
        for (int i = 0; i < reservations.size(); i++) {
            occupiedSeats += reservations.get(i).getNumPeople();
        }
        return tableCapacity - occupiedSeats;
    }

    /* Method to generate a summary of reservations between two dates */
    public String generateSummary(LocalDate startDate, LocalDate endDate) {
        StringBuilder summary = new StringBuilder();
        MyArrayList<LocalDateTime> keys = reservationsByDateTime.keySet();
        for (int i = 0; i < keys.size(); i++) {
            LocalDateTime dateTime = keys.get(i);
            if (!dateTime.toLocalDate().isBefore(startDate) && !dateTime.toLocalDate().isAfter(endDate)) {
                MyArrayList<Reservation> reservations = reservationsByDateTime.get(dateTime);
                for (int j = 0; j < reservations.size(); j++) {
                    summary.append(reservations.get(j)).append("\n");
                }
            }
        }
        return summary.toString();
    }
}
