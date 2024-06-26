package com.javarush.task.task20.task2025;

import java.util.*;

/* 
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        List<Long> list = generate(19);
        long[] result = null;
        for (int i = 0; i < list.size(); i++)   if (list.get(i) < N)    result = new long[i + 1];
        if (result != null){
            for (int i = 0; i < result.length; i++) {
                if (list.get(i) < N) {
                    result[i] = list.get(i);
                }
            }
        }
        return result;
    }

    public static List<Long> generate(int maxN) {
        if (maxN >= 20) throw new IllegalArgumentException();
        genPows(maxN);
        results = new ArrayList<>();
        digitsMultiSet = new int[10];
        testMultiSet = new int[10];
        for (N = 1; N <= maxN; N++) {
            minPow = (long) Math.pow(10, N - 1);
            maxPow = (long) Math.pow(10, N);
            search(9, N, 0);
        }
        Collections.sort(results);
        return results;
    }
    private static void genPows(int N) {
        if (N > 20) throw new IllegalArgumentException();
        pows = new long[10][N + 1];
        for (int i = 0; i < pows.length; i++) {
            long p = 1;
            for (int j = 0; j < pows[i].length; j++) {
                pows[i][j] = p;
                p *= i;
            }
        }
    }
    private static int N;
    private static int[] digitsMultiSet;
    private static int[] testMultiSet;
    private static List<Long> results;
    private static long maxPow;
    private static long minPow;
    private static long[][] pows;

    private static boolean check(long pow) {
        if (pow >= maxPow) return false;
        if (pow < minPow) return false;
        for (int i = 0; i < 10; i++) {
            testMultiSet[i] = 0;
        }
        while (pow > 0) {
            int i = (int) (pow % 10);
            testMultiSet[i]++;
            if (testMultiSet[i] > digitsMultiSet[i]) return false;
            pow = pow / 10;
        }
        for (int i = 0; i < 10; i++) {
            if (testMultiSet[i] != digitsMultiSet[i]) return false;
        }
        return true;
    }
    private static void search(int digit, int unused, long pow) {
        if (pow >= maxPow) return;
        if (digit == -1) {
            if (check(pow)) results.add(pow);
            return;
        }
        if (digit == 0) {
            digitsMultiSet[digit] = unused;
            search(digit - 1, 0, pow + unused * pows[digit][N]);
        } else {
            // Check if we can generate more than minimum
            if (pow + unused * pows[digit][N] < minPow) return;
            long p = pow;
            for (int i = 0; i <= unused; i++) {
                digitsMultiSet[digit] = i;
                search(digit - 1, unused - i, p);
                if (i != unused) {
                    p += pows[digit][N];
                    // Check maximum and break the loop - doesn't help
                }
            }
        }
    }
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
