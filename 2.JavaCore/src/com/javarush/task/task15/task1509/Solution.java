package com.javarush.task.task15.task1509;

/* 
Мужчина или женщина?
*/

public class Solution {
    public static void main(String[] args) {
        Man man = new Man();
        Woman woman = new Woman();

        printName(man);
        printName(woman);
    }

    public static void printName(Human man) {
        if(man instanceof Man) System.out.println("");
        if(man instanceof Woman) System.out.println("");
    }

    public static class Human{

    }

    public static class Man extends Human {

    }

    public static class Woman extends Human{

    }
}
