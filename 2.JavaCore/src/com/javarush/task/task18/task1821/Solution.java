package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception{
        FileInputStream FileIn = new FileInputStream(args[0]);
        ArrayList<Integer> arrayOfData = new ArrayList<>();
        Map<Integer, Integer> mapOfData= new TreeMap<>();
        int FileRead;
        while((FileRead = FileIn.read()) > -1){
            arrayOfData.add(FileRead);
            mapOfData.put(FileRead, 0);
        }
        FileIn.close();
        for(Map.Entry<Integer, Integer> pair: mapOfData.entrySet()){
            for(int i :arrayOfData){
                if(pair.getKey()==i){
                    mapOfData.put(pair.getKey(), (pair.getValue()+1));
                }
            }
            System.out.println((char)((int)pair.getKey()) + " " + pair.getValue());
        }

    }
}
