package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();
        reader.close();
        FileInputStream in1 = new FileInputStream(file2);

        FileOutputStream oi2 = new FileOutputStream(file1, true);
        int inRead;
        while ((inRead = in1.read()) != -1){
            oi2.write(inRead);
        }oi2.close(); in1.close();
        FileInputStream in3 = new FileInputStream(file3);

        FileOutputStream oi1 = new FileOutputStream(file1);
        while((inRead = in3.read()) != -1){
            oi1.write(inRead);
        } in3.close(); oi1.close();
    }
}
