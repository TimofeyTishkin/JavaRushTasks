package com.javarush.task.task15.task1517;

/* 
Статики и исключения
*/

public class Solution {
    public static class MYNEWException extends Exception{
        public MYNEWException(String text){
            super(text);
        }
    }
    public static int A = 0;

    static{

            int d = 1/0;
        //throw an exception here - выбросьте эксепшн тут
    }

    public static int B = 5;

    public static void main(String[] args) {
        System.out.println(B);
    }
}
