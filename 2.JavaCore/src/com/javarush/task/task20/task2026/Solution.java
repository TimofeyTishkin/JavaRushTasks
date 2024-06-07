package com.javarush.task.task20.task2026;

import java.util.ArrayList;
import java.util.Arrays;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        byte[][] b = new byte[a.length+1][a[0].length+1];
        for(int i = b.length-1; i>=0;i--){
            for(int j = b[0].length-1; j>=0;j--){
                try{
                    b[i][j] = a[i][j];
                } catch (ArrayIndexOutOfBoundsException e){
                    b[i][j] = 0;
                }
            }
        }

        int rectangleCount = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if(a[i][j] == 1) {
                    if(b[i][j+1] == 0){
                        if(b[i+1][j] == 0) {
                            rectangleCount++;
                        }
                    }
                }
            }
        }
        return rectangleCount;
    }
}
