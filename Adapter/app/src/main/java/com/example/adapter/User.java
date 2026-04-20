package com.example.adapter;

public class User {
    private final String name;
    private final String hometown;

    public User(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }

    public String getName() {
        return name;
    }

    public String getHometown() {
        return hometown;
    }
}

