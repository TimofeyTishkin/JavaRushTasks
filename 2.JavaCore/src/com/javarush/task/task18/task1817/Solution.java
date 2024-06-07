package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        double count = 0;
        double countSpace = 0;
        FileInputStream in = new FileInputStream(args[0]);
        while ((in.available() > 0)) {
            char c = (char) in.read();
            count++;

            if (c == ' ') countSpace++;
        }
        double d =  countSpace/ count *100;
        System.out.println(Math.round(d*100)/(double)100);
        in.close();
    }
}
