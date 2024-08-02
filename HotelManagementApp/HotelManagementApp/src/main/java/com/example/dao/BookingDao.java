package com.example.dao;

import com.example.datamodel.Booking;

import java.util.List;

public interface BookingDao {
    void createBooking(Booking booking);
    Booking getBookingById(int id);
    List<Booking> getAllBookings();
    void updateBooking(Booking booking);
    void deleteBooking(int id);
}
