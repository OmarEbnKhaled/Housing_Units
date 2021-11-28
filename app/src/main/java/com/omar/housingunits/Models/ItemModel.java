package com.omar.housingunits.Models;

public class ItemModel {

    String title, location, price, no_bed, no_room, no_bath;

    public ItemModel(String title, String location, String price, String no_bed, String no_room, String no_bath) {
        this.title = title;
        this.location = location;
        this.price = price;
        this.no_bed = no_bed;
        this.no_room = no_room;
        this.no_bath = no_bath;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getNo_bed() {
        return no_bed;
    }

    public String getNo_room() {
        return no_room;
    }

    public String getNo_bath() {
        return no_bath;
    }
}
