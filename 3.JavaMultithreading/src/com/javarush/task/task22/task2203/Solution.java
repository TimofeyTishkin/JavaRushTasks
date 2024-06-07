package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        try{
            if(string == null) throw new IndexOutOfBoundsException();
            if(!string.contains("\t")) throw new IndexOutOfBoundsException();
            return string.substring(string.indexOf('\t') + 1, string.substring(string.indexOf('\t') + 1).indexOf('\t') + string.indexOf('\t') +1);

        } catch (IndexOutOfBoundsException e){
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        System.out.println(getPartOfString("SSSSSSSS\t SssssZZZZZZZZZZz \t"));
    }
}
