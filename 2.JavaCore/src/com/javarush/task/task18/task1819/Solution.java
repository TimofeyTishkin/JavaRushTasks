package com.javarush.task.task18.task1819;

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        int FileRead;
        ArrayList<Integer> ListRead1 = new ArrayList<>();
        ArrayList<Integer> ListRead2 = new ArrayList<>();

        FileInputStream File1 = new FileInputStream(file1Name);
        while((FileRead = File1.read()) != -1){
            ListRead1.add(FileRead);
        } File1.close();

        FileInputStream File2 = new FileInputStream(file2Name);
        while((FileRead = File2.read()) != -1){
            ListRead2.add(FileRead);
        } File2.close();

        ArrayList<Integer> result = new ArrayList<>();
        result.addAll(ListRead2);
        result.addAll(ListRead1);

        FileOutputStream File1Out = new FileOutputStream(file1Name);
        for(Integer i :result){
            File1Out.write(i);
        }
        File1Out.close();
    }
}
