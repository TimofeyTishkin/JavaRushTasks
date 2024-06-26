package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable{
    public static void main(String[] args) throws Exception{
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(new File("C:\\Users\\aatis\\OneDrive\\Рабочий стол\\txt.txt.txt")));
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File("C:\\Users\\aatis\\OneDrive\\Рабочий стол\\txt.txt.txt")));
        Solution savedObject = new Solution(12);

        writer.writeObject(savedObject);
        Solution loadedObject = (Solution) reader.readObject();
        System.out.println(loadedObject.string.equals(savedObject.string));
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
