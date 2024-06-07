package com.javarush.task.task18.task1801;

/* 
Максимальный байт
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedInputStream door = new BufferedInputStream(new FileInputStream(reader.readLine()));
        reader.close();
        int b;
        int d = door.read();
        while((b = door.read()) != -1){
            if(b > d){
                int timeD = d;
                d = b;
                b = timeD;
            }
        }
        door.close();
        System.out.println(d);
    }
}
