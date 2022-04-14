package com.revature.realtr.models;

public class Cart {

    private int cart_id;
    private int fp_id;
    private int user_id;
    private int loc_id;
    private String date;
    private float price;

    public Cart() {
    }

    public Cart(int cart_id, int fp_id, int user_id, int loc_id, String date, float price) {
        this.cart_id = cart_id;
        this.fp_id = fp_id;
        this.user_id = user_id;
        this.loc_id = loc_id;
        this.date = date;
        this.price = price;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getFp_id() {
        return fp_id;
    }

    public void setFp_id(int fp_id) {
        this.fp_id = fp_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(int loc_id) {
        this.loc_id = loc_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
