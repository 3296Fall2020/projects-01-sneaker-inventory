package com.company.sneakerinvetory.login;

// json post entity
public class LoginResponse{
    private String sessionID;

    public LoginResponse(String sessionID){
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setText(String sessionID) {
        this.sessionID = sessionID;
    }
}