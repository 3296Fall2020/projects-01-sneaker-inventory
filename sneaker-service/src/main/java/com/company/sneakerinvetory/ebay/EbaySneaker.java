package com.company.sneakerinvetory.ebay;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EbaySneaker {
    private String sku;
    private String name;
    private String size;

    @JsonCreator
    public EbaySneaker(@JsonProperty("sku") String sku,@JsonProperty("name") String name,@JsonProperty("size") String size){
        this.sku  = sku;
        //this.name = name.trim();
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

    public static boolean validateEbaySneaker(EbaySneaker sneaker){
        return sneaker.getName() != null && sneaker.getSize() != null && sneaker.getSku() != null;
    }

    public void printSneaker(){
        System.out.println("sku: " + sku + "\tsize: " + size + "\tname: " + name);
    }

}
