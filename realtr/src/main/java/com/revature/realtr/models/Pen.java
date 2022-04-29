package com.revature.realtr.models;

import java.text.DecimalFormat;

public class Pen {

    private int fp_id;
    private String brand;
    private String model;
    private float price;
    private int qty;
    private String description;
    private String nib;
    private int loc_id;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Pen() {

    }

    public Pen(int fp_id, String brand, String model, float price, int qty, String description, String nib, int loc_id) {
        this.fp_id = fp_id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qty = qty;
        this.description = description;
        this.nib = nib;
        this.loc_id = loc_id;
    }

    public int getFp_id() {
        return fp_id;
    }

    public void setFp_id(int fp_id) {
        this.fp_id = fp_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public int getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(int loc_id) {
        this.loc_id = loc_id;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + " | Model: " + model + " | Price: US$ " + df.format(price) + " | Quantity in Stock: " + qty +
                "\nDescription: " + description + " | Nib Type & Material: " + nib + "\n";

    }
}
