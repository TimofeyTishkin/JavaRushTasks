package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {

    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        if (x==0)
            return "";
        int a = x % 2;
        int b = x / 2;
        BinaryRepresentationTask task = new BinaryRepresentationTask(b);
        task.fork();
        if(b > 0)return task.join() + a;
        else return String.valueOf(a);
    }
}
