package com.javarush.task.task17.task1711;

public enum Sex {
    MALE("м"),
    FEMALE("ж");
    protected String sex;
    Sex(String sex){
        this.sex = sex;
    }

    @Override
    public String toString() {
        return this.sex;
    }
}
