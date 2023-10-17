package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Person {
    @Id
    private UUID id;
    private String name;
    private String email;
    private int age;


    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }
}
