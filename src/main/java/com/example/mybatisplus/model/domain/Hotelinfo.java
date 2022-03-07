package com.example.mybatisplus.model.domain;

import java.util.List;

public class Hotelinfo {
    private Hotel hotel;
    private Address address;
    private List<Room> room;

    public Hotelinfo(Hotel hotel, Address address, List<Room> room) {
        this.hotel = hotel;
        this.address = address;
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }
}
