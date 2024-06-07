package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        if (n == 1) return true;
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            } else {
                n /= 3;
                if(n == 1) return true;
            }
        }
        return false;
    }
}
