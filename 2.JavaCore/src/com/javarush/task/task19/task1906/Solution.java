package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileInputStream = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter fileOutputStream = new BufferedWriter(new FileWriter(reader.readLine()));
        reader.close();

        int count = 1;

        while (fileInputStream.ready()) {
            int date = fileInputStream.read();
            if ((count % 2) == 0) {
                fileOutputStream.write(date);
            }
            count++;
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
