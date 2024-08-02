package com.example.consoleUI;

import com.example.datamodel.Booking;
import com.example.datamodel.Customer;
import com.example.datamodel.Room;
import com.example.services.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleUI {

    private final HotelService hotelService;
    private final Scanner scanner;

    @Autowired
    public ConsoleUI(HotelService hotelService) {
        this.hotelService = hotelService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayMenu();
        int choice = getUserChoice();

        switch (choice) {
            case 1 -> displayRoomOptions();
            case 2 -> displayCustomerOptions();
            case 3 -> displayBookingsOptions();
            case 4 -> {
                System.out.println("Exiting...");
                return;
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }

        start(); // Restart the console UI
    }

    private void displayMenu() {
        System.out.println("=============================================");
        System.out.println("          Hotel Management System            ");
        System.out.println("=============================================");
        System.out.println("1. Room Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Booking Management");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void displayBookingsOptions() {
        System.out.println("-----------------------------------------------");
        System.out.println("           Booking Management Menu             ");
        System.out.println("-----------------------------------------------");
        System.out.println("1. View All Bookings");
        System.out.println("2. Create Booking");
        System.out.println("3. View Booking by ID");
        System.out.println("4. Update Booking");
        System.out.println("5. Delete Booking");
        System.out.print("Enter your choice: ");
        int choice = getUserChoice();
        System.out.println();
        switch (choice) {
            case 1 -> viewAllBookings();
            case 2 -> createBooking();
            case 3 -> viewBookingById();
            case 4 -> updateBooking();
            case 5 -> deleteBooking();
            default -> System.out.println("Invalid choice. Please try again.");
        }
        System.out.println();
    }

    private void displayRoomOptions() {
        System.out.println("-----------------------------------------------");
        System.out.println("           Room Management Options:            ");
        System.out.println("-----------------------------------------------");
        System.out.println("1. View All Rooms");
        System.out.println("2. Add a Room");
        System.out.println("3. Update a Room");
        System.out.println("4. Delete a Room");
        System.out.print("Enter your choice: ");

        int choice = getUserChoice();
        System.out.println();
        switch (choice) {
            case 1 -> displayAllRooms();
            case 2 -> addRoom();
            case 3 -> updateRoom();
            case 4 -> deleteRoom();
            default -> System.out.println("Invalid choice. Please try again.");
        }
        System.out.println();
    }

    private void displayCustomerOptions() {
        System.out.println("-----------------------------------------------");
        System.out.println("         Customer Management Options:          ");
        System.out.println("-----------------------------------------------");
        System.out.println("1. View All Customers");
        System.out.println("2. Add a Customer");
        System.out.println("3. Update a Customer");
        System.out.println("4. Delete a Customer");
        System.out.print("Enter your choice: ");

        int choice = getUserChoice();
        System.out.println();
        switch (choice) {
            case 1 -> displayAllCustomers();
            case 2 -> addCustomer();
            case 3 -> updateCustomer();
            case 4 -> deleteCustomer();
            default -> System.out.println("Invalid choice. Please try again.");
        }
        System.out.println();
    }


    // methods related to Rooms
    private void displayAllRooms() {
        List<Room> rooms = hotelService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            System.out.println("Rooms:");
            displayRoomsData(rooms);
        }
    }

    private void addRoom() {
        System.out.println("Enter room details:");
        System.out.print("Room number: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setCapacity(capacity);
        room.setPrice(price);
        room.setDescription(description);

        hotelService.addRoom(room);
        System.out.println("Room added successfully.");
    }

    private void updateRoom() {
        System.out.print("Enter room ID to update: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        Room existingRoom = hotelService.getRoomById(roomId);
        if (existingRoom == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.println("Enter updated room details:");
        System.out.print("Room number: ");
        existingRoom.setRoomNumber(scanner.nextLine());
        System.out.print("Capacity: ");
        existingRoom.setCapacity(Integer.parseInt(scanner.nextLine()));
        System.out.print("Price: ");
        existingRoom.setPrice(Double.parseDouble(scanner.nextLine()));
        System.out.print("Description: ");
        existingRoom.setDescription(scanner.nextLine());

        hotelService.updateRoom(existingRoom);
        System.out.println("Room updated successfully.");
    }

    private void deleteRoom() {
        System.out.print("Enter room ID to delete: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        hotelService.deleteRoom(roomId);
        System.out.println("Room deleted successfully.");
    }


    // methods related to customers
    private void displayAllCustomers() {
        List<Customer> customers = hotelService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("Customers:");
            displayCustomerData(customers);
        }
    }

    private void addCustomer() {
        System.out.println("Enter customer details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);

        hotelService.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        Customer existingCustomer = hotelService.getCustomerById(customerId);
        if (existingCustomer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Enter updated customer details:");
        System.out.print("Name: ");
        existingCustomer.setName(scanner.nextLine());
        System.out.print("Email: ");
        existingCustomer.setEmail(scanner.nextLine());
        System.out.print("Phone: ");
        existingCustomer.setPhone(scanner.nextLine());
        System.out.print("Address: ");
        existingCustomer.setAddress(scanner.nextLine());

        hotelService.updateCustomer(existingCustomer);
        System.out.println("Customer updated successfully.");
    }

    private void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        hotelService.deleteCustomer(customerId);
        System.out.println("Customer deleted successfully.");
    }

    // methods related to Bookings
    private void createBooking() {
        System.out.println("===== Create Booking =====");
        System.out.print("Enter Room ID: ");
        int roomId = scanner.nextInt();
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDateStr = scanner.next();
        LocalDate startDate = LocalDate.parse(startDateStr);
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDateStr = scanner.next();
        LocalDate endDate = LocalDate.parse(endDateStr);

        Booking booking = new Booking(roomId, customerId, startDate, endDate);
        hotelService.createBooking(booking);
        System.out.println("Booking created successfully.");
    }

    private void viewAllBookings() {
        System.out.println(" All Bookings : ");
        List<Booking> bookings = hotelService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No Bookings available.");
        } else {
            displayBookingData(bookings);
        }
    }

    private void viewBookingById() {
        System.out.println("View Booking by ID : ");
        System.out.print("Enter Booking ID: ");
        int id = scanner.nextInt();
        Booking booking = hotelService.getBookingById(id);
        if (booking != null) {
            System.out.println(booking);
        } else {
            System.out.println("Booking not found for ID: " + id);
        }
    }

    private void updateBooking() {
        System.out.println("===== Update Booking =====");
        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        // Check if the booking exists
        Booking existingBooking = hotelService.getBookingById(bookingId);
        if (existingBooking == null) {
            System.out.println("Booking not found for ID: " + bookingId);
            return;
        }



        // Prompt for updated details
        System.out.print("Enter Room ID: ");
        int roomId = scanner.nextInt();
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDateStr = scanner.next();
        LocalDate startDate = LocalDate.parse(startDateStr);
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDateStr = scanner.next();
        LocalDate endDate = LocalDate.parse(endDateStr);

        // Update the booking
        Booking updatedBooking = new Booking(bookingId, roomId, customerId, startDate, endDate);
        hotelService.updateBooking(updatedBooking);
        System.out.println("Booking updated successfully.");
    }

    private void deleteBooking() {
        System.out.println("===== Delete Booking =====");
        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        // Check if the booking exists
        Booking existingBooking = hotelService.getBookingById(bookingId);
        if (existingBooking == null) {
            System.out.println("Booking not found for ID: " + bookingId);
            return;
        }

        // Delete the booking
        hotelService.deleteBooking(bookingId);
        System.out.println("Booking deleted successfully.");
    }


    // methods related to show data on console
    private void displayCustomerData(List<Customer> customers) {
        // Print header
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("    %-5s %-10s %-20s %-15s %-20s    \n", "ID", "Name", "Email", "Phone", "Address");
        System.out.println("----------------------------------------------------------------------------------");
        // Print each row
        for (Customer customer : customers) {
            System.out.printf("    %-5d %-10s %-20s %-15s %-20s    \n",
                    customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getAddress());
        }
        System.out.println("----------------------------------------------------------------------------------");

    }

    private void displayRoomsData(List<Room> rooms) {
        // Print header
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("    %-5s %-10s %-20s %-15s %-20s    \n", "ID", "RoomNumber", "Capacity", "Price", "Description");
        System.out.println("----------------------------------------------------------------------------------");
        // Print each row
        for (Room room : rooms) {
            System.out.printf("    %-5d %-10s %-20s %-15s %-20s    \n",
                    room.getId(), room.getRoomNumber(), room.getCapacity(), room.getPrice(), room.getDescription());
        }
        System.out.println("----------------------------------------------------------------------------------");

    }

    private void displayBookingData(List<Booking> bookings) {

        // Print header
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("    %-5s %-10s %-20s %-15s %-20s    \n", "ID", "RoomId", "CustomerId", "StartDate", "EndDate");
        System.out.println("----------------------------------------------------------------------------------");
        // Print each row
        for (Booking booking : bookings) {
            System.out.printf("    %-5d %-10s %-20s %-15s %-20s    \n",
                    booking.getId(),hotelService.getRoomById(booking.getRoomId()).getRoomNumber(), hotelService.getCustomerById(booking.getCustomerId()).getName(), booking.getStartDate(), booking.getEndDate());
        }
        System.out.println("----------------------------------------------------------------------------------");

    }
}