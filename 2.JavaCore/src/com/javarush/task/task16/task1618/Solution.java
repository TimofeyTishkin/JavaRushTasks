package com.javarush.task.task16.task1618;

/* 
Снова interrupt
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new TestThread();
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
        //Add your code here - добавь код тут
    }

    //Add your code below - добавь код ниже
    public static class TestThread extends Thread {
        public void run(){
            for(int i = 0; i < 1; i++){
                try{
                    while(!(isInterrupted())){
                        Thread.sleep(10);
                        System.out.println("Hel-lo!!");
                    }
                } catch (InterruptedException e){

                }
            }
        }
    }
}