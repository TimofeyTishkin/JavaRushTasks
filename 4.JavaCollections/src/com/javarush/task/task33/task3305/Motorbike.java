package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class Motorbike extends Vehicle {
    private String owner;

    public Motorbike(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}