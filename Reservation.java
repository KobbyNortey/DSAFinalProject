import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private static int idCounter = 1;
    private final int reservationId;
    private final String customerName;
    private final LocalDate date;
    private final LocalTime time;
    private final int numPeople;

    public Reservation(String customerName, LocalDate date, LocalTime time, int numPeople) {
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


    public String toString() {
        return "Reservation [ID=" + reservationId + ", Name=" + customerName + ", Date=" + date + ", Time=" + time + ", People=" + numPeople + "]";
    }
}
