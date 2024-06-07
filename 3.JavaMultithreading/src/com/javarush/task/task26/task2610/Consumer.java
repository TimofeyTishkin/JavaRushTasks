package com.javarush.task.task26.task2610;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private BlockingQueue queue;
    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                for(Object o : queue){
                    System.out.println(o.toString());
                    queue.remove(o);
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
