package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        try {
            String str = args[0].toUpperCase();

            // Если в строке есть что-то кроме чисел и букв из диапазонов a-z A-Z, выводим ошибку
            if (str.matches(".*[^0-9a-zA-Z].*")) System.out.println("incorrect");
            else {

                char[] symbols = str.toCharArray();
                Arrays.sort(symbols);

                char largestChar = symbols[symbols.length - 1]; // Самой большой символ в числе

                if (largestChar < 50) System.out.println(2); // Бинарная система
                else if (largestChar < 58) System.out.println(largestChar - 47); // Если цифра
                else if (largestChar < 91) System.out.println(largestChar - 64 + 10); // Если большая буква
            }
        }
        catch (Exception e) {
            System.out.println("incorrect");
        }
    }
}