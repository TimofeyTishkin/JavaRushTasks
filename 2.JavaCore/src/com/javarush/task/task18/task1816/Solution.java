package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;
import java.util.ArrayList;


public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inp = new FileInputStream(args[0]);
        ArrayList<String> list = new ArrayList();
        int i = 0;
        while(inp.available()>0){
            list.add(String.valueOf((char)inp.read()));
        }
        inp.close();
        for(String s:list){
            if(s.matches("[a-z]")||s.matches("[A-Z]")){
                i++;
            }
        }
        System.out.print(i);
    }
}
