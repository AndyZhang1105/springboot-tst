package com.zz.tst.juc;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeTst {

    static class LinkedBlockingDequeProducer implements Runnable {

        protected BlockingQueue<String> blockingQueue;
        final Random random = new Random();

        public LinkedBlockingDequeProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    System.out.println("Put: " + data);
                    blockingQueue.put(data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static class LinkedBlockingDequeConsumer implements Runnable {

        protected BlockingQueue<String> blockingQueue;

        public LinkedBlockingDequeConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = blockingQueue.poll();
                    System.out.println(Thread.currentThread().getName() + " take(): " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        final LinkedBlockingDeque<String> blockingQueue = new LinkedBlockingDeque<String>();

        LinkedBlockingDequeProducer queueProducer1 = new LinkedBlockingDequeProducer(blockingQueue);
        new Thread(queueProducer1).start();

        LinkedBlockingDequeProducer queueProducer2 = new LinkedBlockingDequeProducer(blockingQueue);
        new Thread(queueProducer2).start();

        LinkedBlockingDequeConsumer queueConsumer1 = new LinkedBlockingDequeConsumer(blockingQueue);
        new Thread(queueConsumer1).start();

        LinkedBlockingDequeConsumer queueConsumer2 = new LinkedBlockingDequeConsumer(blockingQueue);
        new Thread(queueConsumer2).start();
    }
}
