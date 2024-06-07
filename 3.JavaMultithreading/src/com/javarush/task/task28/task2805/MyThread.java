package com.javarush.task.task28.task2805;

import java.util.concurrent.ThreadLocalRandom;



public class MyThread extends Thread {
    static int count = Thread.MIN_PRIORITY;
    public MyThread(){
        this.setPriority(count);
        if(++count > MAX_PRIORITY) count = MIN_PRIORITY;
    }
    public MyThread(Runnable target) {
        super(target);
        this.setPriority(count);
        if(++count > MAX_PRIORITY) count = MIN_PRIORITY;
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if(count <= group.getMaxPriority())  this.setPriority(count);
        else this.setPriority(group.getMaxPriority());
        if(++count > MAX_PRIORITY)  count  = MIN_PRIORITY;
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(count);
        if(++count > MAX_PRIORITY) count = MIN_PRIORITY;
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if(count <= group.getMaxPriority())  this.setPriority(count);
        else this.setPriority(group.getMaxPriority());
        if(++count > MAX_PRIORITY)  count  = MIN_PRIORITY;
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(count);
        if(++count > MAX_PRIORITY) count = MIN_PRIORITY;
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if(count <= group.getMaxPriority())  this.setPriority(count);
        else this.setPriority(group.getMaxPriority());
        if(++count > MAX_PRIORITY)  count  = MIN_PRIORITY;
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if(count <= group.getMaxPriority())  this.setPriority(count);
        else this.setPriority(group.getMaxPriority());
        if(++count > MAX_PRIORITY)  count  = MIN_PRIORITY;
    }
}
