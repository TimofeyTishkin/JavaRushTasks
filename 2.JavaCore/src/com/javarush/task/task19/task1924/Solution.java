package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<>();
    static {
        map.put(0,"ноль");
        map.put(1,"один");
        map.put(2,"два");
        map.put(3,"три");
        map.put(4,"четыре");
        map.put(5,"пять");
        map.put(6,"шесть");
        map.put(7,"семь");
        map.put(8,"восемь");
        map.put(9,"девять");
        map.put(10,"десять");
        map.put(11,"одиннадцать");
        map.put(12,"двенадцать");
    }
    public static void main(String[] args) throws IOException{
        BufferedReader readerOfName = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(readerOfName.readLine()));
        readerOfName.close();
        while(reader.ready()){
            String line = reader.readLine();
            String[] dataLine = line.split("\\s");
            StringBuilder lineOut = new StringBuilder();
            boolean firstWord = true;
            for(int i = 0;i < dataLine.length;i++){
                int num = 13;
                try{
                    num = Integer.parseInt(dataLine[i]);
                } catch (Exception parse){}

                if(map.containsKey(num)){
                    dataLine[i] = map.get(num);
                }
                if(!firstWord){
                    lineOut.append(" ").append(dataLine[i]);
                }else {
                    lineOut.append(dataLine[i]);
                    firstWord = false;
                }
            }
            System.out.println(lineOut.toString());
        }
        reader.close();
    }
}
