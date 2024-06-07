package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        Pattern pattern = Pattern.compile("\\s");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader FileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String FileReaderRead;
        while((FileReaderRead = FileReader.readLine()) != null) {
            if (FileReaderRead.contains(args[0])) {
                String[] ash = pattern.split(FileReaderRead);
                if(ash[0].equals(args[0])){
                    System.out.println(FileReaderRead);
                    break;
                }
            }
        } FileReader.close();
    }
}
