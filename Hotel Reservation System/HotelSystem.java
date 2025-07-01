import java.util.Scanner;

public class HotelSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n=== HOTEL RESERVATION SYSTEM ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    hotel.displayAvailableRooms();
                    break;

                case "2":
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room category (Standard/Deluxe/Suite): ");
                    String category = scanner.nextLine();
                    System.out.print("Enter payment amount to simulate ($100+): ");
                    double payment = Double.parseDouble(scanner.nextLine());

                    if (payment < 100) {
                        System.out.println("Payment too low. Booking failed.");
                        break;
                    }

                    Booking booking = hotel.bookRoom(name, category);
                    if (booking != null) {
                        System.out.println("Booking successful!");
                        System.out.println(booking);
                    } else {
                        System.out.println("No available room in this category.");
                    }
                    break;

                case "3":
                    System.out.print("Enter Booking ID to cancel: ");
                    String bookingId = scanner.nextLine();
                    boolean success = hotel.cancelBooking(bookingId);
                    System.out.println(success ? "Booking cancelled." : "Booking not found.");
                    break;

                case "4":
                    hotel.viewBookings();
                    break;

                case "5":
                    System.out.println("Exiting system.");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
