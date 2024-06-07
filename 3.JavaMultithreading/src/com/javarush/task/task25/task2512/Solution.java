package com.javarush.task.task25.task2512;

import java.util.*;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        t.interrupt();

        System.out.println(e.getCause().getCause());
        System.out.println(e.getCause());
        System.out.println(e);

    }


    public static void main(String[] args) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler(new Solution());
        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
    }
}