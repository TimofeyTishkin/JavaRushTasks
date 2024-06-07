package com.javarush.task.task14.task1419;

import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }
    public static class MyException2 extends Exception{
        public MyException2(String text){
            super(text);
        }
    }
    public static class MyException3 extends Exception{
        public MyException3(String text){
            super(text);
        }
    }
    public static class MyException4 extends Exception{
        public MyException4(String text){
            super(text);
        }
    }
    public static class MyException5 extends Exception{
        public MyException5(String text){
            super(text);
        }
    }
    public static class MyException6 extends Exception{
        public MyException6(String text){
            super(text);
        }
    }
    public static class MyException7 extends Exception{
        public MyException7(String text){
            super(text);
        }
    }
    public static class MyException8 extends Exception{
        public MyException8(String text){
            super(text);
        }
    }
    public static class MyException9 extends Exception{
        public MyException9(String text){
            super(text);
        }
    }
    public static class MyException10 extends Exception{
        public MyException10(String text){
            super(text);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }
        try{ throw new MyException2("pas"); }
        catch (MyException2 e){ exceptions.add(e); }
        try{throw new MyException3("fuck"); }
        catch (MyException3 e){exceptions.add(e);}

        try{ throw new MyException4("pas"); }
        catch (MyException4 e){ exceptions.add(e); }
        try{throw new MyException5("fuck"); }
        catch (MyException5 e){exceptions.add(e);}

        try{ throw new MyException6("pas"); }
        catch (MyException6 e){ exceptions.add(e); }
        try{throw new MyException7("fuck"); }
        catch (MyException7 e){exceptions.add(e);}

        try{ throw new MyException8("pas"); }
        catch (MyException8 e){ exceptions.add(e); }
        try{throw new MyException9("fuck"); }
        catch (MyException9 e){exceptions.add(e);}

        try{ throw new MyException10("pas"); }
        catch (MyException10 e){ exceptions.add(e); }


        //напишите тут ваш код

    }
}
