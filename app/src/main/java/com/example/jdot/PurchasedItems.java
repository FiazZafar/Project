package com.example.jdot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PurchasedItems {
    private int imgResource;
    private String itemName;
    private long price;
    private Date date;

    public PurchasedItems(int imgResource, String itemName, long price, long dateInMillis) {
        this.imgResource = imgResource;
        this.itemName = itemName;
        this.price = price;
        this.date = new Date(dateInMillis); // Initialize Date using milliseconds
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(date);
    }
}
