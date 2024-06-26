package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable , CustomThreadManipulator {

    private Thread thread;

    @Override
    public void start(String threadName) {
        thread = new Thread(this);
        thread.setName(threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void run() {
        System.out.println(thread.getName());
        while (!Thread.interrupted()){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                stop();
                break;
            }
            System.out.println(thread.getName());
        }
    }
}