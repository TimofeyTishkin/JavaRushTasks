package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        StringBuilder fileData = new StringBuilder();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
        while(fileReader.ready()){
            fileData.append((char) fileReader.read());
        }
        fileReader.close();

        String[] dataArray = fileData.toString().split("\\s");
        Matcher matcher = Pattern.compile("\\b[0-9]+\\b").matcher(fileData);

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName2, true));
        while(matcher.find()){
            if(!fileData.substring(matcher.start(),matcher.end()).
                    equals(dataArray[0])) { fileWriter.write(" ");}
            fileWriter.write(fileData.substring(matcher.start(), matcher.end()));

        }
        fileWriter.close();
    }
}
