package com.company.sneakerinvetory.sneaker;

public class Sneaker {
    int index;
    String shoeName;
    String sku;
    String size;
    String price;

    public Sneaker(String shoeName, String sku, String size, String price){
        this.shoeName = shoeName;
        this.sku = sku;
        this.size = size;
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String sneakerName) {
        shoeName = sneakerName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        sku = sku;
    }

    public String getSize() {
        return size;
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

    public static boolean validateAddSneaker(Sneaker sneaker) {
        return  sneaker.getPrice() != null && sneaker.getSize() != null && sneaker.getSku() != null && sneaker.getShoeName() != null;

    }
    public static boolean validateEditSneaker(Sneaker sneaker){
        return sneaker.getIndex() != 0 && sneaker.getPrice() != null && sneaker.getSize() != null && sneaker.getSku() != null && sneaker.getShoeName() != null;
    }

    public static void printSneaker(Sneaker sneaker){
        System.out.println(sneaker.getIndex() + "\t" + sneaker.getShoeName() + "\t" + sneaker.getSku() + "\t" +
                sneaker.getSize() + "\t" + sneaker.getPrice());
    }
}
