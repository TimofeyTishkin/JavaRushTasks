package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        try{
            if(string == null) throw new IndexOutOfBoundsException();

            int allCountOfSpace = 0;
            char[] chars = string.toCharArray();
            for(char ch : chars){
                if(ch == ' ') allCountOfSpace++;
            }
            if(allCountOfSpace < 4){
                throw new IndexOutOfBoundsException();
            }
            int afterFirstSpaceIndex = string.indexOf(' ') + 1;
            int fifthSpaceIndex = afterFirstSpaceIndex;

            int countOfSpace = 1;
            char[] characters = string.substring(afterFirstSpaceIndex).toCharArray();
            for(char ch : characters){
                if(ch == ' ') countOfSpace++;
                if(countOfSpace == 5) break;
                fifthSpaceIndex++;
            }
            return string.substring(afterFirstSpaceIndex, fifthSpaceIndex);
        } catch (IndexOutOfBoundsException e){
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {
        public TooShortStringException(){}
        public TooShortStringException(String message){
            super(message);
        }
    }
}
