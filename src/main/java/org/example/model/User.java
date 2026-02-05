package org.example.model;

import java.util.UUID;

public class User {
    private final String id; //final: the value cannot be reassigned.
    private String name;
    private String email;
    private int age;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String name, String email, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
