package com.company.sneakerinvetory.Stockx;

public class Sneaker {
    private String sku;
    private String name;
    private String size;
    private String average;

    public Sneaker(String sku, String name, String size){
        this.sku  = sku;
        this.name = name.trim();
        this.name = name;
        this.size = size;

    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.trim();
        this.name = name;

    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String retreiveAverage(){
        if (sku != null & name != null){
            Runnable thread = new Thread(){
                @Override
                public void run() {

                }
            };
        }
        return this.average;
    }

}
