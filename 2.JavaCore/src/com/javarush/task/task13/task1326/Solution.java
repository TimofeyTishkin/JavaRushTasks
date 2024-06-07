package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        InputStream sos = new FileInputStream(fileName);
        //BufferedInputStream buffer = new BufferedInputStream(sos);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(sos));
        ArrayList<Integer> sosat = new ArrayList<>();
        while(true) {
            String sd = buffer.readLine();
            if (sd == null) break;
            int s = Integer.parseInt(sd);
            sosat.add(s);
        }
        buffer.close();
        sos.close();
        Collections.sort(sosat);
        for(Integer d : sosat){
            if(d%2 == 0) System.out.println(d);
        }
        // напишите тут ваш код
    }
}
