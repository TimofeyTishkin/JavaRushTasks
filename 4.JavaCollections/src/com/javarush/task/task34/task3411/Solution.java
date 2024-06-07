package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {
    public static int countOfTurns = 0;
    public static void main(String[] args) {
        int numRings = 10;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) {
        if (numRings == 0) return;
        moveRing(a,c,b, numRings-1);
        System.out.println("from " +a+ " to "+b+ " , step: "+ ++countOfTurns);
        moveRing(c,b,a, numRings-1);
    }
}