package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void printEveryOne(String d){
        try{
            char[] hh = d.toCharArray();
            boolean isDouble = false;
            for (char c :hh) {
                if(c == '.') isDouble = true;
            }
            if (isDouble) print(Double.parseDouble(d));
            else{
                int n = Integer.parseInt(d);
                if(n >= 128 || n <= 0){
                    print(n);
                }
                if (n > 0 && n < 128) {
                    print(Short.parseShort(d));
                }
                //else print(d);
            }

        } catch (NumberFormatException e){
            print(d);
        }
        catch (Exception e){
            System.out.println();
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String d = reader.readLine();
        while(!(d.equals("exit"))){
            printEveryOne(d);
            d = reader.readLine();
        }

        //напиште тут ваш код
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
