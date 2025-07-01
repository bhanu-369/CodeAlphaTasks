public class Booking {
    private String guestName;
    private int roomNumber;
    private String category;
    private String bookingId;

    public Booking(String guestName, int roomNumber, String category, String bookingId) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.bookingId = bookingId;
    }

    public String getBookingId() { return bookingId; }
    public int getRoomNumber() { return roomNumber; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                "\nGuest: " + guestName +
                "\nRoom: " + roomNumber +
                " (" + category + ")";
    }
}
