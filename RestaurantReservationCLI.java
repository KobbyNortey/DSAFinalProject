import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/* Class to provide a command-line interface for the restaurant reservation system */
public class RestaurantReservationCLI {
    /* Create an instance of ReservationManager with a total capacity of 50 */
    private static final ReservationManager reservationManager = new ReservationManager(50);

    /* Main method to start the CLI */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            /* Display the main menu */
            System.out.println("Restaurant Reservation System");
            System.out.println("1. Book Table");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Check Availability");
            System.out.println("5. Generate Summary");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            /* Handle the selected option */
            if (option == 1) {
                bookTable(scanner); /* Call method to book a table */
            } else if (option == 2) {
                cancelReservation(scanner); /* Call method to cancel a reservation */
            } else if (option == 3) {
                viewReservations(scanner); /* Call method to view reservations */
            } else if (option == 4) {
                checkAvailability(scanner); /* Call method to check availability */
            } else if (option == 5) {
                generateSummary(scanner); /* Call method to generate a summary of reservations */
            } else if (option == 6) {
                /* Exit the system */
                System.out.println("Exiting the system.");
                scanner.close();
                return;
            } else {
                /* Handle invalid options */
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /* Method to book a table based on user input */
    private static void bookTable(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter time (HH:mm): ");
        String time = scanner.nextLine();
        System.out.print("Enter number of people: ");
        int numPeople = Integer.parseInt(scanner.nextLine());

        /* Attempt to book a table and print the result */
        String result = reservationManager.bookTable(customerName, date, LocalTime.parse(time), numPeople);
        System.out.println(result);
    }

    /* Method to cancel a reservation based on user input */
    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = Integer.parseInt(scanner.nextLine());

        /* Attempt to cancel the reservation and print the result */
        String result = reservationManager.cancelReservation(reservationId);
        System.out.println(result);
    }

    /* Method to view reservations based on a filter */
    private static void viewReservations(Scanner scanner) {
        System.out.print("Enter filter (date or customer name): ");
        String filter = scanner.nextLine();

        /* Retrieve and print the list of reservations based on the filter */
        MyArrayList<Reservation> reservations = reservationManager.viewReservations(filter);
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println(reservations.get(i));
        }
    }

    /* Method to check the availability of seats */
    private static void checkAvailability(Scanner scanner) {
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter time (HH:mm): ");
        String time = scanner.nextLine();

        /* Check and print the number of available seats */
        int availableSeats = reservationManager.checkAvailability(date, LocalTime.parse(time));
        System.out.println("Available seats: " + availableSeats);
    }

    /* Method to generate a summary of reservations within a date range */
    private static void generateSummary(Scanner scanner) {
        System.out.print("Enter start date (yyyy-mm-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDate = scanner.nextLine();

        /* Generate and print the summary of reservations */
        String summary = reservationManager.generateSummary(startDate, LocalDate.parse(endDate));
        System.out.println(summary);
    }
}
