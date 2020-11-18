package com.company.sneakerinvetory.ebay;

public class EbayResponse {
    String priceResponse;

    public EbayResponse(String priceResponse){
        this.priceResponse = priceResponse;
    }

    public void setPriceResponse(String priceResponse) {
        this.priceResponse = priceResponse;
    }

    public String getPriceResponse(){
        return priceResponse;
    }
}
