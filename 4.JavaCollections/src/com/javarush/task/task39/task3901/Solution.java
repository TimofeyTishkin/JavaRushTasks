package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if(s == null || s.isEmpty()) return 0;

        HashSet<Character> subString = new HashSet<>();

        int maxLength = 0;

        char[] chars = s.toLowerCase().toCharArray();


        for (int i = 0; i < chars.length; i++){

            if(subString.contains(chars[i])){

                if(chars.length-i-1  >  subString.size()){
                    subString = new HashSet<>();
                    i--;
                }
                else return Math.max(maxLength, subString.size());

            } else {
                subString.add(chars[i]);
                if(maxLength < subString.size()) maxLength = subString.size();
            }
        }

        return Math.max(maxLength, subString.size());
    }
}
