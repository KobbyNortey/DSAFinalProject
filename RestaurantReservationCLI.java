import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class RestaurantReservationCLI {
    private static final ReservationManager reservationManager = new ReservationManager(50);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Restaurant Reservation System");
            System.out.println("1. Book Table");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Check Availability");
            System.out.println("5. Generate Summary");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            if (option == 1) {
                bookTable(scanner);
            } else if (option == 2) {
                cancelReservation(scanner);
            } else if (option == 3) {
                viewReservations(scanner);
            } else if (option == 4) {
                checkAvailability(scanner);
            } else if (option == 5) {
                generateSummary(scanner);
            } else if (option == 6) {
                System.out.println("Exiting the system.");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void bookTable(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter time (HH:mm): ");
        String time = scanner.nextLine();
        System.out.print("Enter number of people: ");
        int numPeople = Integer.parseInt(scanner.nextLine());

        String result = reservationManager.bookTable(customerName, date, LocalTime.parse(time), numPeople);
        System.out.println(result);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = Integer.parseInt(scanner.nextLine());

        String result = reservationManager.cancelReservation(reservationId);
        System.out.println(result);
    }

    private static void viewReservations(Scanner scanner) {
        System.out.print("Enter filter (date or customer name): ");
        String filter = scanner.nextLine();

        MyArrayList<Reservation> reservations = reservationManager.viewReservations(filter);
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println(reservations.get(i));
        }
    }

    private static void checkAvailability(Scanner scanner) {
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter time (HH:mm): ");
        String time = scanner.nextLine();

        int availableSeats = reservationManager.checkAvailability(date, LocalTime.parse(time));
        System.out.println("Available seats: " + availableSeats);
    }

    private static void generateSummary(Scanner scanner) {
        System.out.print("Enter start date (yyyy-mm-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDate = scanner.nextLine();

        String summary = reservationManager.generateSummary(startDate, LocalDate.parse(endDate));
        System.out.println(summary);
    }
}
