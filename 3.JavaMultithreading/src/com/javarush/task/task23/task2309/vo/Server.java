package com.javarush.task.task23.task2309.vo;

public class Server extends NamedItem {
    private int id;
    private String name;
    private final String description = "Received from executing 'SELECT * FROM SERVER'";
    public static String query = "SELECT * FROM SERVER";
}
