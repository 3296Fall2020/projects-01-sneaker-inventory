package com.company.sneakerinvetory.ebay;

public class EbaySneaker {
    private String sku;
    private String name;
    private String size;

    public EbaySneaker(String sku, String name, String size){
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

}
