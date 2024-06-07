package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String FileName = "";
        FileInputStream fos = null;
        try {
            while(true){
                FileName = reader.readLine();
               try(FileInputStream FileOut =  new FileInputStream(FileName);){
                   fos = FileOut;
                }
            }
        } catch (FileNotFoundException ew){
            System.out.println(FileName);
            if(fos != null) {
                try {
                    fos.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
