import java.awt.*;
import javax.swing.*;

public class HotelApp {
    private HotelManager hotelManager;

    public HotelApp() {
        hotelManager = new HotelManager();
        JFrame frame = new JFrame("Hotel Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setResizable(false);

        // Center the frame
        frame.setLocationRelativeTo(null);

        // Title label
        JLabel titleLabel = new JLabel("Hotel Room Booking System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Buttons with larger font and preferred size
        JButton viewRoomsBtn = new JButton("View Available Rooms");
        JButton bookRoomBtn = new JButton("Book a Room");
        JButton viewBookingsBtn = new JButton("View All Bookings");

        Font btnFont = new Font("Arial", Font.PLAIN, 18);
        Dimension btnSize = new Dimension(220, 40);
        for (JButton btn : new JButton[]{viewRoomsBtn, bookRoomBtn, viewBookingsBtn}) {
            btn.setFont(btnFont);
            btn.setPreferredSize(btnSize);
        }

        viewRoomsBtn.addActionListener(e -> showAvailableRooms());
        bookRoomBtn.addActionListener(e -> bookRoomDialog());
        viewBookingsBtn.addActionListener(e -> showAllBookings());

        // Use GridBagLayout for button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        buttonPanel.add(viewRoomsBtn, gbc);
        gbc.gridy = 1;
        buttonPanel.add(bookRoomBtn, gbc);
        gbc.gridy = 2;
        buttonPanel.add(viewBookingsBtn, gbc);

        // Add components to frame
        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void showAvailableRooms() {
        StringBuilder sb = new StringBuilder("Available Rooms:\n");
        for (Room r : hotelManager.getAvailableRooms()) {
            sb.append(r.toString()).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        JOptionPane.showMessageDialog(null, scrollPane, "Available Rooms", JOptionPane.INFORMATION_MESSAGE);
    }

    private void bookRoomDialog() {
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField roomField = new JTextField();

        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        nameField.setFont(fieldFont);
        contactField.setFont(fieldFont);
        roomField.setFont(fieldFont);

        Object[] fields = {
                "Name:", nameField,
                "Contact:", contactField,
                "Room Number:", roomField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Book Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String contact = contactField.getText();
            int roomNum;
            try {
                roomNum = Integer.parseInt(roomField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid room number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Booking booking = hotelManager.bookRoom(name, contact, roomNum);
            if (booking != null) {
                JOptionPane.showMessageDialog(null, "Room booked successfully!\n" + booking.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Room not available!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAllBookings() {
        StringBuilder sb = new StringBuilder("All Bookings:\n");
        for (Booking b : hotelManager.getAllBookings()) {
            sb.append(b.toString()).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        JOptionPane.showMessageDialog(null, scrollPane, "All Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Use system look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        new HotelApp();
    }
}
