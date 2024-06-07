package com.javarush.task.task18.task1804;

/* 
Самые редкие байты
*/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedInputStream order =  new BufferedInputStream(new FileInputStream(reader.readLine()));
        reader.close();
        ArrayList<Integer> allElements = new ArrayList<>();
        HashMap<Integer, Integer> originalElements = new HashMap<>();
        int orderRead;
        while((orderRead = order.read()) != -1){
            allElements.add(orderRead);
            originalElements.put(orderRead, 0);
        }
        order.close();
        for(Map.Entry<Integer, Integer> pair : originalElements.entrySet()){
            for(Integer i :allElements){
                if(pair.getKey().equals(i)){
                    originalElements.put(pair.getKey(), (pair.getValue()+1));
                }
            }
        }
        for(Map.Entry<Integer, Integer> pair : originalElements.entrySet()){
            if(pair.getValue() < 2){
                System.out.print(pair.getKey()+" ");
            }
        }
    }
}
