package com.javarush.task.task30.task3002;

import java.math.BigInteger;

/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        if (s.startsWith("0x")) s = Integer.toString(Integer.parseInt(s.substring(2), 16));
        else if (s.startsWith("0b")) s = Integer.toString(Integer.parseInt(s.substring(2), 2));
        else if (s.startsWith("0")) s = Integer.toString(Integer.parseInt(s.substring(1), 8));

        return s;
    }
}
