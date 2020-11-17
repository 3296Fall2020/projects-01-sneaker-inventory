package com.company.sneakerinvetory.Stockx;

import java.io.IOException;

public class driver {
    public static void main(String agrs[]) throws IOException, InterruptedException {
        Sneaker sneaker = new Sneaker("fu9007", "yeezy black", "11");
        EbayReader reader = new EbayReader(sneaker);
        String url = reader.getGetAveragePrice();
        System.out.println(url);
    }
}
