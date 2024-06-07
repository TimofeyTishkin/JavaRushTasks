package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<>();
    static {
        labels.put(123d, "12");
        labels.put(12d, "12");
        labels.put(23d, "12");
        labels.put(13d, "12");
        labels.put(2d, "12");

    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
