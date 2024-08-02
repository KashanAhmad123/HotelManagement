package com.example.datamodel;

public class Room {
    private int id;
    private String roomNumber;
    private int capacity;
    private double price;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
// Getters and setters

    @Override
    public String toString() {
        return "        " +
                "Id : " + id +
                " RoomNumber : " + roomNumber + '\'' +
                " Capacity : " + capacity +
                " Price : " + price +
                " Description : " + description + '\'';
    }
}
