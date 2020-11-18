package com.company.sneakerinvetory.ebay;

public class EbayThread implements Runnable {

    EbaySneaker sneaker;
    String price;
    public EbayThread(EbaySneaker sneaker){
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
