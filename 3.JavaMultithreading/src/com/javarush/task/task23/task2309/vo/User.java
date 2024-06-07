package com.javarush.task.task23.task2309.vo;

public class User extends NamedItem {
    private int id;
    private String name;
    private final String description = "Received from executing 'SELECT * FROM USER'";
    public static String query = "SELECT * FROM USER";
}
