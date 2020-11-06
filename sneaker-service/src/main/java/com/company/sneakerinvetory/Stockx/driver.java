package com.company.sneakerinvetory.Stockx;

import java.io.IOException;

public class driver {
    public static void main(String agrs[]) throws IOException, InterruptedException {
        Sneaker sneaker = new Sneaker("fu9007", "yeezy black", "11");
        StockXReader reader = new StockXReader(sneaker);
        String url = reader.getAveragePrice();
        System.out.println(url);
    }
}
