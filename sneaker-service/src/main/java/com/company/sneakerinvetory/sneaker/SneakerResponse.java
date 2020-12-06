package com.company.sneakerinvetory.sneaker;

import java.util.ArrayList;
import java.util.List;

public class SneakerResponse {
    private List<Sneaker> sneakerList;

    public SneakerResponse(){
        this.sneakerList = new ArrayList<>();
    }

    public void add(Sneaker sneaker){
        sneakerList.add(sneaker);
    }

    public List<Sneaker> getSneakerList(){
        return sneakerList;
    }
}
