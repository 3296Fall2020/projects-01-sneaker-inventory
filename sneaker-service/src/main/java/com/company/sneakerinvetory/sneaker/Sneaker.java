package com.company.sneakerinvetory.sneaker;

public class Sneaker {
    String userID;
    String SneakerName;
    String Sku;
    String size;
    String date;
    String price;

    public Sneaker(String userID, String sneakerName, String Sku, String size, String price){
        this.userID = userID;
        this.SneakerName = sneakerName;
        this.Sku = Sku;
        this.size = size;
        this.price = price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSneakerName() {
        return SneakerName;
    }

    public void setSneakerName(String sneakerName) {
        SneakerName = sneakerName;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String sku) {
        Sku = sku;
    }

    public String getSize() {
        return size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
