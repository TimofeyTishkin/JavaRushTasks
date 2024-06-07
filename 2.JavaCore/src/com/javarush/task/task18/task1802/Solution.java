package com.javarush.task.task18.task1802;

/* 
Минимальный байт
*/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedInputStream door = new BufferedInputStream(new FileInputStream(reader.readLine()));
        reader.close();
        int min = door.read();
        int just;
        while((just = door.read()) != -1){
            if(just < min){
                int minBuf = min;
                min = just;
                just = minBuf;
            }
        }
        door.close();
        System.out.println(min);
    }
}
