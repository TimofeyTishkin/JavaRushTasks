package com.javarush.task.task18.task1809;

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = "";
        String file2 = "";
        FileInputStream File1;
        FileOutputStream File2;
        byte[] bytes;
        try{
            file1 = reader.readLine();
            file2 = reader.readLine();
            reader.close();
        } catch (IOException e){
            System.out.println("smth");
        }

        try{
            File1 = new FileInputStream(file1);
            bytes = new byte[File1.available()];
            int count = File1.read(bytes);
            File1.close();

            File2 = new FileOutputStream(file2);
            for(int i = bytes.length - 1; i >= 0;i--){
                File2.write(bytes[i]);
            }
            File2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}