package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader readerName = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(readerName.readLine()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(readerName.readLine()));
        readerName.close();
        StringBuilder stringer = new StringBuilder();
        while (reader.ready()){
            stringer.append((char) reader.read());
        }
        String toWrite = stringer.toString().replaceAll("[\\p{Punct}]","");
        writer.write(toWrite);

        reader.close();
        writer.close();
    }
}
