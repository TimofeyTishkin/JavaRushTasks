package com.javarush.task.task13.task1309;

/* 
Всё, что движется
*/

public class Solution {
    public static void main(String[] args){
    }
    interface CanMove{
        Double speed();
    }
    interface CanFly extends CanMove{

        Double speed(CanFly d);
    }
}