package com.javarush.task.task16.task1617;

/* 
Отсчет на гонках
*/

public class Solution {
    public static volatile int numSeconds = 4;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        Thread.sleep(3500);
        clock.interrupt();
        //add your code here - добавь код тут
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {
            for(;numSeconds > 0;numSeconds--){
                try {
                    System.out.print(numSeconds + " ");
                    Thread.sleep(1000);
                    if(numSeconds == 1) {
                        System.out.print("Марш!");
                        return;
                    }
                } catch (InterruptedException e) {
                    System.out.print("Прервано!");
                    return;
                }

            }
            //add your code here - добавь код тут
        }
    }
}
