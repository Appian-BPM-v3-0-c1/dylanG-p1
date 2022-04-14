package com.revature.realtr.models;

public class Location {

    private int loc_id;
    private String address;
    private String city;
    private String state;
    private String zip;

    public Location() {
    }

    public Location(int loc_id, String address, String city, String state, String zip) {
        this.loc_id = loc_id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(int loc_id) {
        this.loc_id = loc_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Location: " + address + " " + city + ", " + state + " " + zip;
    }
}
