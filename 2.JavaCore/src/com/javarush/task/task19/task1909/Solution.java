package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader readName = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(readName.readLine()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(readName.readLine()));
        readName.close();

        StringBuilder builder = new StringBuilder();

        while(reader.ready()){
            builder.append((char) reader.read());
        }
        String result = builder.toString();

        for(char character :result.toCharArray()){
            if(character == '.'){
                writer.write("!");
            } else writer.write(character);
        }

        reader.close();
        writer.close();
    }
}
