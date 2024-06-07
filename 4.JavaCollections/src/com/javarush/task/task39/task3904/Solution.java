package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/
public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if(n == 0 || n==1)return 1;
        if(n < 0) return 0;

        long[] accents = new long[n + 1];
        accents[0] = 1;
        accents[1] = 1;
        accents[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            accents[i] = accents[i - 3] + accents[i - 2] + accents[i - 1];
        }

        return accents[n];
    }
}

