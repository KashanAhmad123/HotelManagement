package com.example.dao;

import com.example.datamodel.Room;

import java.util.List;

public interface RoomDao {
    List<Room> getAllRooms();
    Room getRoomById(int id);
    void addRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(int id);
}
