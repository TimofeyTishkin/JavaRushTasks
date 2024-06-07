package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(;;) {
            try {
                Thread.sleep(450);
            } catch (InterruptedException e) {
                return;
            }
            for (; ; ) {
                try {
                    System.out.format("Processing %s", queue.take().toString());
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
