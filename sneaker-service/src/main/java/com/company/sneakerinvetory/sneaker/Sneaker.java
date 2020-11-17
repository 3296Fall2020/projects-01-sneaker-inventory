package com.company.sneakerinvetory.sneaker;

public class Sneaker {
    String index;
    String id;
    String SneakerName;
    String Sku;
    String size;
    String date;
    String price;

    public Sneaker(String id, String sneakerName, String Sku, String size, String price){
        this.id = id;
        this.SneakerName = sneakerName;
        this.Sku = Sku;
        this.size = size;
        this.price = price;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
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
