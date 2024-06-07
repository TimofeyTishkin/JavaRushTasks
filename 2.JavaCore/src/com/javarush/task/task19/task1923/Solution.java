package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        String line;
        while ((line=reader.readLine())!=null){
            String [] mass =line.split("\\s");
            for(String s:mass){
                if(s.matches(".*[0-9]+.*")){
                    writer.write(s+" ");
                }
            }
        }
        reader.close();
        writer.close();
    }
}
