package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String,String> map;
    public Producer(ConcurrentHashMap<String,String> map){
        this.map = map;
    }
    public void run() {
        int counter = 1;
        Thread currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted()) {
            this.map.put(""+counter, "Some text for "+counter);
            counter++;
            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
                break;
            }
        }
        System.out.println("["+currentThread.getName()+"]"+" thread was terminated");
    }
}
