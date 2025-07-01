import java.io.*;
import java.util.*;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public Hotel() {
        loadRooms();
        loadBookings();
    }

    private void loadRooms() {
        // Simulate predefined room list
        for (int i = 1; i <= 10; i++) rooms.add(new Room(i, "Standard"));
        for (int i = 11; i <= 15; i++) rooms.add(new Room(i, "Deluxe"));
        for (int i = 16; i <= 18; i++) rooms.add(new Room(i, "Suite"));
    }

    public void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room r : rooms) {
            if (!r.isBooked())
                System.out.println(r);
        }
    }

    public Room findAvailableRoom(String category) {
        for (Room r : rooms) {
            if (!r.isBooked() && r.getCategory().equalsIgnoreCase(category))
                return r;
        }
        return null;
    }

    public Booking bookRoom(String guestName, String category) {
        Room room = findAvailableRoom(category);
        if (room == null) return null;

        room.setBooked(true);
        String bookingId = UUID.randomUUID().toString().substring(0, 8);
        Booking booking = new Booking(guestName, room.getRoomNumber(), category, bookingId);
        bookings.add(booking);
        saveBookings();
        return booking;
    }

    public boolean cancelBooking(String bookingId) {
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking b = iterator.next();
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                // Free up room
                for (Room r : rooms) {
                    if (r.getRoomNumber() == b.getRoomNumber()) {
                        r.setBooked(false);
                        break;
                    }
                }
                iterator.remove();
                saveBookings();
                return true;
            }
        }
        return false;
    }

    public void viewBookings() {
        System.out.println("\n--- Current Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                System.out.println(b);
                System.out.println("----------");
            }
        }
    }

    private void saveBookings() {
        try (PrintWriter writer = new PrintWriter("bookings.txt")) {
            for (Booking b : bookings) {
                writer.printf("%s,%s,%d,%s\n",
                        b.getBookingId(), b.guestName, b.getRoomNumber(), b.category);
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

    private void loadBookings() {
        File file = new File("bookings.txt");
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length != 4) continue;
                String bookingId = data[0];
                String guestName = data[1];
                int roomNumber = Integer.parseInt(data[2]);
                String category = data[3];

                bookings.add(new Booking(guestName, roomNumber, category, bookingId));

                // Mark room as booked
                for (Room r : rooms) {
                    if (r.getRoomNumber() == roomNumber) {
                        r.setBooked(true);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading bookings.");
        }
    }
}
