package com.company.sneakerinvetory.ebay;

public class EbayThread implements Runnable {

    SneakerForm sneaker;
    String price;
    public EbayThread(SneakerForm sneaker){
        this.sneaker = sneaker;
    }
    @Override
    public void run() {
        EbayReader ebayReader = new EbayReader(sneaker);
        price = ebayReader.getAveragePrice();
    }

    public String getPrice(){
        return price;
    }
}
