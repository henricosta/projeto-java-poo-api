package com.api.plataformavagas.models;

public enum UserRole {
    CANDIDATE("candidate"),
    COMPANY("company");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
