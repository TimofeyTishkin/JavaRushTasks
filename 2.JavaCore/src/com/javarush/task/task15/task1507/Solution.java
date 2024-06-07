package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }
    public static void printMatrix(short m, short n, Object value, Short d){
        System.out.println(n + m + (String)value + d);
    }
    public static void printMatrix(short m, short n, Object value, Short d, int f){
        System.out.println(n + m + (String)value + d + f);
    }
    public static void printMatrix(short m, short n, Object value, Short d, boolean dod){
        System.out.println(n + m + (String)value + d + dod);
    }
    public static void printMatrix(short m, short n, Object value, Short d, String ffofo){
        System.out.println(n + m + (String)value + d + ffofo);
    }
    public static void printMatrix(short m, short n, Object value, Short d, double f, float nnn){
        System.out.println(n + m + (String)value + d + f + nnn);
    }
    public static void printMatrix(short m, short n, Object value, Short d, String g, String gg){
        System.out.println(n + m + (String)value + d + g + gg);
    }
    public static void printMatrix(short m, short n, Object value, Short d, String mfg, String rrr, String rr){
        System.out.println(n + m + (String)value + d + mfg + rrr + rr);
    }
    public static void printMatrix(short m, short n, Object value, Short d, int v, int r, int t){
        System.out.println(n + m + (String)value + d + v + r + t);
    }
}
