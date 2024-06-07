package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader(args[0]);
        FileWriter writer = new FileWriter(args[1]);
        StringBuilder dataOfFile1 = new StringBuilder();
        while(reader.ready()){
            dataOfFile1.append((char)reader.read());
        }
        reader.close();
        String data_of_File = dataOfFile1.toString().replaceAll(System.lineSeparator(), " ");
        String[] splittedArray = data_of_File.trim().split("\\s");

        for(String ass : splittedArray){
            if(ass.length() > 6){
                writer.write(ass);
                if(!(ass.equals(splittedArray[splittedArray.length - 1]))){
                    writer.write(",");
                }
            }
        }
        writer.close();
    }
}
