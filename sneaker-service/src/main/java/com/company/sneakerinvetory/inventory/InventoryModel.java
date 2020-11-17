package com.company.sneakerinvetory.inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryModel {
    private String id;

    @JsonCreator
    public InventoryModel(@JsonProperty("id") String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
