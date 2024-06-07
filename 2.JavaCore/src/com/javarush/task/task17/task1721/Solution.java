package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<>();
    public static List<String> forRemoveLines = new ArrayList<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1;
        String fileName2;
        try {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();
            reader.close();
            BufferedReader newReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName1)));
            while(newReader1.ready()){
                String str1 = newReader1.readLine();
                if(str1.equals(null)) break;
                allLines.add(str1);
            }
            BufferedReader newReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName2)));
            while(newReader2.ready()){
                String str2 = newReader2.readLine();
                if(str2.equals(null)) break;
                forRemoveLines.add(str2);
            }
            newReader1.close();
            newReader2.close();
        } catch (IOException e) {}
        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }
    }

    public void joinData() throws CorruptedDataException {
        if(allLines.containsAll(forRemoveLines)){
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
