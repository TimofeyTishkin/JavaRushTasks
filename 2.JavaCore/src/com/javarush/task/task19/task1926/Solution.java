package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        while (fileReader.ready()){
            String newLine = fileReader.readLine();
            StringBuilder reverseLine = new StringBuilder();

            ArrayList<Character> charArray = new ArrayList<>();
            for(char h : newLine.toCharArray()){
                charArray.add(0, h);
            }

            for(Character h :charArray){
                reverseLine.append(h);
            }
            System.out.println(reverseLine);
        }

        fileReader.close();
    }
}
