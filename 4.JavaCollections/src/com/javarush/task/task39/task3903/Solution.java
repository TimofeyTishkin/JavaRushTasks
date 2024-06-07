package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.BitSet;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        if (i == j) return number;
        if (i < 0 || j < 0) return number;

        String s = Long.toBinaryString(number);
        StringBuffer buffer = new StringBuffer("00000000000000000000000000000000000000000000000000000000000000000");
        buffer.replace(buffer.length() - s.length() - 1, buffer.length(), s);
        s = buffer.toString();

        if (i > j) {
            int k = i;
            i = j;
            j = k;
        }
        String result = s.substring(0, s.length() - j - 1) +
                s.charAt(s.length() - i - 1) +
                s.substring(s.length() - j, s.length() - i - 1) +
                s.charAt(s.length() - j - 1) +
                s.substring(s.length() - i);
        System.out.println(result);
        number = Long.parseUnsignedLong(result, 2);
        return number;
    }
}
