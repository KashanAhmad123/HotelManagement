package com.example.services;

import com.example.datamodel.Booking;
import com.example.datamodel.Customer;
import com.example.datamodel.Room;

import java.util.List;

public interface HotelService {
    // Rooms related methods
    List<Room> getAllRooms();
    Room getRoomById(int id);
    void addRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(int id);

    // Customer related methods
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);

    // Booking
    void createBooking(Booking booking);

    Booking getBookingById(int id);

    List<Booking> getAllBookings();

    void updateBooking(Booking booking);

    void deleteBooking(int id);







}
