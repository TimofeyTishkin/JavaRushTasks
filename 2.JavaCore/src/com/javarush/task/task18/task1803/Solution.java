package com.javarush.task.task18.task1803;

/* 
Самые частые байты
*/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedInputStream door = new BufferedInputStream(new FileInputStream(reader.readLine()));
        reader.close();
        ArrayList<Integer> arrayList = new ArrayList<>(100);
        HashMap<Integer, Integer> mapper = new HashMap<>();
        int doorRead;
        while ((doorRead = door.read())!= -1){
            arrayList.add(doorRead);
            mapper.put(doorRead, 1);
        }
        door.close();

        for (Map.Entry<Integer, Integer> pair : mapper.entrySet()){
            for(Integer i : arrayList){
                if(pair.getKey().equals(i)){
                    mapper.put(pair.getKey(), (pair.getValue()+1));
                }
            }
        }
        for(Map.Entry<Integer, Integer> pair : mapper.entrySet()){
            if(pair.getValue() > 3){
                System.out.print(pair.getKey()+" ");
            }
        }

    }
}
