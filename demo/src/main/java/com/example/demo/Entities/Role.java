package com.example.demo.Entities;

public enum Role {
    SUBSCRIBER(1,new String[]{"READ","WRITE","UPDATE","DELETE"}),
    USER(2,new String[]{"READ","WRITE","UPDATE"});

    private long id;
    private String[] authorities;
    Role(long id, String[] authorities) {
        this.id =id;
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
