package com.company.sneakerinvetory.inventory;

public class InventoryResponse {
    String jsonResponse;

    public InventoryResponse(String json){
        this.jsonResponse = json;
    }
    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
}
