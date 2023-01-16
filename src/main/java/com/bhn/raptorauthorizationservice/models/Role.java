package com.bhn.raptorauthorizationservice.models;

public enum Role {

    ROLE_ADMIN("admin");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
