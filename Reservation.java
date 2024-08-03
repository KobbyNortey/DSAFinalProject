import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    /* Static counter to generate unique reservation IDs */
    private static int idCounter = 1;

    /* Unique identifier for each reservation */
    private final int reservationId;
    /* Name of the customer who made the reservation */
    private final String customerName;
    /* Date of the reservation */
    private final LocalDate date;
    /* Time of the reservation */
    private final LocalTime time;
    /* Number of people in the reservation */
    private final int numPeople;

    public Reservation(String customerName, LocalDate date, LocalTime time, int numPeople) {
        /* Assign a unique ID to the reservation */
        this.reservationId = idCounter++;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.numPeople = numPeople;
    }

    public int getReservationId() {
        return reservationId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getDate() {
        return date;
    }


    public LocalTime getTime() {
        return time;
    }

    public int getNumPeople() {
        return numPeople;
    }

    /* Returns a string representation of the reservation*/
    public String toString() {
        return "Reservation [ID=" + reservationId + ", Name=" + customerName + ", Date=" + date + ", Time=" + time + ", People=" + numPeople + "]";
    }
}
