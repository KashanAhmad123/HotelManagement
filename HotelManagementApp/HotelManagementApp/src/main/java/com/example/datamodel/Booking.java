package com.example.datamodel;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int roomId;
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    // Other fields and getters/setters


    public  Booking(){

    }
    public Booking(int id, int roomId, int customerId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.roomId = roomId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking(int roomId, int customerId, LocalDate startDate, LocalDate endDate) {

        this.roomId = roomId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
