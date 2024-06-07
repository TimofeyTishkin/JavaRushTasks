package com.javarush.task.task13.task1318;

/* 
Чтение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream fog = new FileInputStream(reader.readLine());
        BufferedInputStream buf = new BufferedInputStream(fog);
        while(buf.available()>0){
            char c = (char)buf.read();

            System.out.print(c);
        }
        fog.close();
        buf.close();
        reader.close();
        // напишите тут ваш код
    }
}