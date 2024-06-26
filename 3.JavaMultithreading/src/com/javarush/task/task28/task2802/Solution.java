package com.javarush.task.task28.task2802;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/*
Пишем свою ThreadFactory
*/
public class Solution {
    public static class AmigoThreadFactory implements ThreadFactory {
        private static AtomicInteger fabricNumber = new AtomicInteger(1);
        private AtomicInteger threadNumber = new AtomicInteger(1);
        private ThreadGroup current = Thread.currentThread().getThreadGroup();

        private int factoryNumber = fabricNumber.getAndIncrement();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(current, r);
            thread.setName(String.format("%s-pool-%d-thread-%d", current.getName(), factoryNumber, threadNumber.getAndIncrement()));
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setDaemon(false);
            return thread;
        }
    }
    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
