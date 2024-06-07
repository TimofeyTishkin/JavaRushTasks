package com.javarush.task.task13.task1319;

/* 
Писатель в файл с консоли
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String FileName = reader.readLine();
        OutputStream vov = new FileOutputStream(FileName);
        BufferedWriter suka = new BufferedWriter(new OutputStreamWriter(vov));

        for(;;){
            String saka = reader.readLine();
            suka.write(saka);
            suka.write("\n");
        if(saka.equals("exit")) break;
        }
        vov.close();
        reader.close();
        suka.close();
        // напишите тут ваш код
    }
}
