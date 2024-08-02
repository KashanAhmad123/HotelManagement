package com.example.services;

import com.example.dao.BookingDao;
import com.example.dao.CustomerDao;
import com.example.dao.RoomDao;
import com.example.datamodel.Booking;
import com.example.datamodel.Customer;
import com.example.datamodel.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private final RoomDao roomDao;
    private final CustomerDao customerDao;
    private final BookingDao bookingDao;

    @Autowired
    public HotelServiceImpl(RoomDao roomDao, CustomerDao customerDao, BookingDao bookingDao) {
        this.roomDao = roomDao;
        this.customerDao = customerDao;
        this.bookingDao = bookingDao;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.getAllRooms();
    }

    @Override
    public Room getRoomById(int id) {
        return roomDao.getRoomById(id);
    }

    @Override
    public void addRoom(Room room) {
        roomDao.addRoom(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomDao.updateRoom(room);
    }

    @Override
    public void deleteRoom(int id) {
        roomDao.deleteRoom(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }


    // Booking
    @Override
    public void createBooking(Booking booking) {
        bookingDao.createBooking(booking);
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingDao.getBookingById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingDao.updateBooking(booking);
    }

    @Override
    public void deleteBooking(int id) {
        bookingDao.deleteBooking(id);
    }
}