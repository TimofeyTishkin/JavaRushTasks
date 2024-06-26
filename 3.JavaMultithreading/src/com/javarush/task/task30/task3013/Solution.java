package com.javarush.task.task30.task3013;

/* 
Набираем код Ӏ Java Multithreading: 10 уровень, 6 лекция
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = Integer.MAX_VALUE - 133;
        System.out.println(Integer.toString(number, 2));

        String result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);
    }

    public int resetLowerBits(int number) {
        number |= (number >>  1);
        number |= (number >>  2);
        number |= (number >>  4);
        number |= (number >>  8);
        return number &= ~(number >> 1);
    }
}