import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationManager {
    private final MyHashMap<LocalDateTime, MyArrayList<Reservation>> reservationsByDateTime;
    private final int tableCapacity;

    public ReservationManager(int tableCapacity) {
        this.reservationsByDateTime = new MyHashMap<>();
        this.tableCapacity = tableCapacity;
    }

    public String bookTable(String customerName, LocalDate date, LocalTime time, int numPeople) {
        // Validate inputs
        if (customerName == null || customerName.trim().isEmpty()) {
            return "Invalid customer name.";
        }
        if (date == null || time == null) {
            return "Invalid date or time.";
        }
        if (numPeople <= 0) {
            return "Number of people must be greater than zero.";
        }

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        MyArrayList<Reservation> reservations = reservationsByDateTime.get(dateTime);

        if (reservations == null) {
            reservations = new MyArrayList<>();
            reservationsByDateTime.put(dateTime, reservations);
        }

        int occupiedSeats = 0;
        for (int i = 0; i < reservations.size(); i++) {
            occupiedSeats += reservations.get(i).getNumPeople();
        }

        if (occupiedSeats + numPeople <= tableCapacity) {
            Reservation reservation = new Reservation(customerName, date, time, numPeople);
            reservations.add(reservation);
            return "Reservation successful: " + reservation;
        } else {
            return "No available tables for the specified date and time.";
        }
    }

    public String cancelReservation(int reservationId) {
        MyArrayList<LocalDateTime> keys = reservationsByDateTime.keySet();
        for (int i = 0; i < keys.size(); i++) {
            LocalDateTime key = keys.get(i);
            MyArrayList<Reservation> reservations = reservationsByDateTime.get(key);
            for (int j = 0; j < reservations.size(); j++) {
                Reservation reservation = reservations.get(j);
                if (reservation.getReservationId() == reservationId) {
                    reservations.remove(reservation);
                    return "Reservation cancelled: " + reservation;
                }
            }
        }
        return "Reservation not found.";
    }

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

    public int checkAvailability(LocalDate date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        MyArrayList<Reservation> reservations = reservationsByDateTime.get(dateTime);
        if (reservations == null) {
            return tableCapacity;
        }

        int occupiedSeats = 0;
        for (int i = 0; i < reservations.size(); i++) {
            occupiedSeats += reservations.get(i).getNumPeople();
        }
        return tableCapacity - occupiedSeats;
    }

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
