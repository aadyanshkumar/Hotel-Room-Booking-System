public class Booking {
    private Customer customer;
    private Room room;

    public Booking(Customer customer, Room room) {
        this.customer = customer;
        this.room = room;
        room.bookRoom();
    }

    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }

    @Override
    public String toString() {
        return "Booking: " + customer.getName() + " | Room: " + room.getRoomNumber();
    }
}
