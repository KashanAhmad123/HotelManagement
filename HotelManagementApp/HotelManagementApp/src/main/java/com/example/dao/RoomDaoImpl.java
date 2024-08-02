package com.example.dao;

import com.example.datamodel.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class RoomDaoImpl implements RoomDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public RoomDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate =jdbcTemplate;
    }

    @Override
    public List<Room> getAllRooms() {
        String query = "SELECT * FROM room";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Room room = new Room();
            room.setId(rs.getInt("id"));
            room.setRoomNumber(rs.getString("room_number"));
            room.setCapacity(rs.getInt("capacity"));
            room.setPrice(rs.getDouble("price"));
            room.setDescription(rs.getString("description"));
            return room;
        });
    }

    @Override
    public Room getRoomById(int id) {
        String query = "SELECT * FROM room WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate.queryForObject(query, params, (rs, rowNum) -> {
            Room room = new Room();
            room.setId(rs.getInt("id"));
            room.setRoomNumber(rs.getString("room_number"));
            room.setCapacity(rs.getInt("capacity"));
            room.setPrice(rs.getDouble("price"));
            room.setDescription(rs.getString("description"));
            return room;
        });
    }

    @Override
    public void addRoom(Room room) {
        String query = "INSERT INTO room (room_number, capacity, price, description) " + "VALUES (:roomNumber, :capacity, :price, :description)";
        Map<String, Object> params = new HashMap<>();
        params.put("roomNumber", room.getRoomNumber());
        params.put("capacity", room.getCapacity());
        params.put("price", room.getPrice());
        params.put("description", room.getDescription());
        jdbcTemplate.update(query, params);
    }

    @Override
    public void updateRoom(Room room) {
        String query = "UPDATE room SET room_number = :roomNumber, " + "capacity = :capacity, price = :price, description = :description " + "WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", room.getId());
        params.put("roomNumber", room.getRoomNumber());
        params.put("capacity", room.getCapacity());
        params.put("price", room.getPrice());
        params.put("description", room.getDescription());
        jdbcTemplate.update(query, params);
    }

    @Override
    public void deleteRoom(int id) {
        String query = "DELETE FROM room WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update(query, params);
    }
}
