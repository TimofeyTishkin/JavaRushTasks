package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        int countZaWarudo = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileReader reader1 = new FileReader(fileName);
        String slovo = " ";
        char [] a = new char[50];

        while (reader1.ready()) {
            reader1.read(a);

            for(char c : a) {
                slovo+=c;
                slovo = slovo.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
                //System.out.println(slovo);
                if (slovo.contains(" world ")) {
                    slovo = " ";
                    countZaWarudo++;
                }
            }
        }
        reader1.close();
        System.out.println(countZaWarudo);
    }
}
