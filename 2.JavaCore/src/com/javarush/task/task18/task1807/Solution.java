package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try { BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedInputStream order = new BufferedInputStream(new FileInputStream(reader.readLine()));
            reader.close();
        byte[] buffer = new byte[1000];
            while (order.available() > 0) {
                int count = order.read(buffer);
            }
            order.close();
            int CounT = 0;
            for(byte b : buffer){
                if(b == 44){
                    CounT++;
                }
            }
            System.out.println(CounT);
        } catch (IOException i){
            i.printStackTrace();
        }


    }
}
