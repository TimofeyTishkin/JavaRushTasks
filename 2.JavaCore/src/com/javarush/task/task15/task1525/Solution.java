package com.javarush.task.task15.task1525;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<>();
    static {

        try{
            InputStream is = new FileInputStream(Statics.FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s;
            while((s = br.readLine()) != null) {
                lines.add(s);
            }
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}
