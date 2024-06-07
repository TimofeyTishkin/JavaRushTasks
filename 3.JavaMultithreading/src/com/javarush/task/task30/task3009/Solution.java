package com.javarush.task.task30.task3009;

/* 
Палиндром?
*/

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String s) {
        HashSet<Integer> radixHashSet = new HashSet<>();
        int sa;
        try{sa = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return new HashSet<>();
        }
            for (int i = 2; i < 37; i++) {
                BigInteger integer = new BigInteger(String.valueOf(sa), 10);
                if(!integer.toString(i).isEmpty() && integer.toString(i).equals(new StringBuilder(integer.toString(i)).reverse().toString())){
                    radixHashSet.add(i);
                }
            }
            if(radixHashSet.isEmpty()) return new HashSet<>();
        return radixHashSet;
    }
}