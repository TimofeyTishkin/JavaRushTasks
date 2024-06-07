package com.javarush.task.task13.task1308;

/* 
Эй, ты там живой?
*/

public class Solution {
    public static void main(String[] args) {
    }
    interface Person{
        default boolean isAlive(){
            return true;
        }
    }
    interface Presentable extends Person{

    }
}