import java.util.*;

public class HotelManager {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public HotelManager() {
        // Predefined Rooms
        rooms.add(new Room(101, "Single", 1000));
        rooms.add(new Room(102, "Double", 2000));
        rooms.add(new Room(201, "Deluxe", 3500));
    }

    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked()) available.add(room);
        }
        return available;
    }

    public Booking bookRoom(String name, String contact, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isBooked()) {
                Customer customer = new Customer(name, contact);
                Booking booking = new Booking(customer, room);
                bookings.add(booking);
                return booking;
            }
        }
        return null;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}

