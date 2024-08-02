package com.example.dao;

import com.example.datamodel.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookingDaoImpl implements BookingDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookingDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (id, room_id, customer_id, start_date, end_date) " +
                "VALUES (:id, :roomId, :customerId, :startDate, :endDate)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", booking.getId());
        params.addValue("roomId", booking.getRoomId());
        params.addValue("customerId", booking.getCustomerId());
        params.addValue("startDate", booking.getStartDate());
        params.addValue("endDate", booking.getEndDate());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Booking getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Booking.class));
    }

    @Override
    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class));
    }

    @Override
    public void updateBooking(Booking booking) {
        String sql = "UPDATE bookings SET room_id = :roomId, customer_id = :customerId, " +
                "start_date = :startDate, end_date = :endDate WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", booking.getId());
        params.addValue("roomId", booking.getRoomId());
        params.addValue("customerId", booking.getCustomerId());
        params.addValue("startDate", booking.getStartDate());
        params.addValue("endDate", booking.getEndDate());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
