package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        while(fileReader.ready()){
            String line = fileReader.readLine();
            String[] words = line.split("\\s");
            int count = 0;
            for(String s : words){
                for(String word :Solution.words){
                    if(s.equals(word)) count++;
                }
            }
            if(count == 2) System.out.println(line);
        }
        fileReader.close();
    }
}
