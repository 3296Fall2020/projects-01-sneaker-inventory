package com.company.sneakerinvetory.inventory;

public class InventoryResponse {
    String inventoryResponse;

    public InventoryResponse(String json){
        this.inventoryResponse = json;
    }

    public String getInventoryResponse() {
        return inventoryResponse;
    }

    public void setInventoryResponse(String inventoryResponse) {
        this.inventoryResponse = inventoryResponse;
    }
}
