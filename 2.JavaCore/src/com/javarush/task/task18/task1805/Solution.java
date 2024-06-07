package com.javarush.task.task18.task1805;

/* 
Сортировка байт
*/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedInputStream order = new BufferedInputStream(new FileInputStream(reader.readLine()));
        reader.close();
        HashSet<Integer> set = new HashSet<>();
        int orderRead;
        while((orderRead = order.read()) != -1){
            set.add(orderRead);
        }
        order.close();
        ArrayList<Integer> finallyty = new ArrayList<>(set);
        Collections.sort(finallyty);
        for(Integer i:finallyty){
            System.out.print(i + " ");
        }
    }
}
