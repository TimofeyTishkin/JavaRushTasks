package com.javarush.task.task21.task2103;

public class Solution {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return (a && b && c && !d) || (!a && c) || (!b && c) || (c && d);
    }

    public static void main(String[] args) {

    }
}
