package com.revature.realtr.models;

public class History {

    private int hist_id;
    private int fp_id;
    private int user_id;
    private int loc_id;
    private String date;
    private float price;

    public History() {
    }

    public History(int hist_id, int fp_id, int user_id, int loc_id, String date, float price) {
        this.hist_id = hist_id;
        this.fp_id = fp_id;
        this.user_id = user_id;
        this.loc_id = loc_id;
        this.date = date;
        this.price = price;
    }

    public int getHist_id() {
        return hist_id;
    }

    public void setHist_id(int hist_id) {
        this.hist_id = hist_id;
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

    @Override
    public String toString() {
        return "History{" +
                "hist_id=" + hist_id +
                ", fp_id=" + fp_id +
                ", user_id=" + user_id +
                ", loc_id=" + loc_id +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}


