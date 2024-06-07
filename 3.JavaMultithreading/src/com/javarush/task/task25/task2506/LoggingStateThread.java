package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    private Thread thread;
    public LoggingStateThread(Thread thread){
        this.thread = thread;
        this.setDaemon(true);
    }

    @Override
    public void run() {

        while(!thread.isInterrupted()){
            String state = thread.getState().toString();
            System.out.println(state);
            while(thread.getState().toString().equals(state)){
                if(state.equals("TERMINATED")) break;
            }
            if(state.equals("TERMINATED")) break;

        }

    }
}
