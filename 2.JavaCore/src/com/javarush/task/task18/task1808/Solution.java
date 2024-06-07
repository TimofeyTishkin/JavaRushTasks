package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;


public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = "";
        String file2 = "";
        String file3 = "";
        try {
            file1 = reader.readLine();
            file2 = reader.readLine();
            file3 = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("fkn bullshit");
        }
        int aFull = 0;
        FileInputStream File1 = null;
        FileOutputStream File2;
        FileOutputStream File3;
        int aHalf = 0;
        try {
            File1 = new FileInputStream(file1);

            if(File1.available()>0){
                aFull = File1.available();
                if(aFull%2 == 0) aHalf = aFull/2;
                else aHalf = (aFull/2) + 1;
            }

            File2 = new FileOutputStream(file2);
            for(int i = 0; i < aHalf; i++) File2.write(File1.read());
            File2.close();


            File3 = new FileOutputStream(file3);
            for(int i = aHalf; i < aFull; i++) File3.write(File1.read());
            File3.close();

            File1.close();
        } catch (FileNotFoundException e){
            try {
                assert File1 != null;
                File1.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("kazzo");
        } catch (IOException e) {
            try {
                File1.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("again...");
        }
    }
}
