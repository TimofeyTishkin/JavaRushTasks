package com.javarush.task.task25.task2510;

import javax.sound.midi.Soundbank;

/*
Поживем - увидим
*/
public class Solution extends Thread {

    public Solution() {
        class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if(e instanceof Error){
                    System.out.println("Нельзя дальше работать");
                    return;
                }
                if(e instanceof Exception){
                    System.out.println("Надо обработать");
                }
                else {
                    System.out.println("Поживем - увидим");
                }
            }
        }
        this.setUncaughtExceptionHandler(new UncaughtExceptionHandler());
    }

    public static void main(String[] args) {
    }
}
