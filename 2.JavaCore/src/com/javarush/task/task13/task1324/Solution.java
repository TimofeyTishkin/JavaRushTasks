package com.javarush.task.task13.task1324;

/* 
Один метод в классе
*/

public class Solution {
    public static void main(String[] args) {
    }

    public interface Animal {
        String getColor();

        Integer getAge();
    }

    public static abstract class Fox implements Animal {
        public String getName() {
            return "Fox";
        }

    }
}
