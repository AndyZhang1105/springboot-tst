package com.zz.tst.juc;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueTst {

    static class LinkedTransferQueueProducer implements Runnable {

        protected BlockingQueue<String> blockingQueue;
        final Random random = new Random();

        public LinkedTransferQueueProducer(BlockingQueue<String> queue) {
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

    static class LinkedTransferQueueConsumer implements Runnable {

        protected BlockingQueue<String> blockingQueue;

        public LinkedTransferQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " take(): " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        final LinkedTransferQueue<String> blockingQueue = new LinkedTransferQueue<String>();

        LinkedTransferQueueProducer queueProducer1 = new LinkedTransferQueueProducer(blockingQueue);
        new Thread(queueProducer1).start();

        LinkedTransferQueueProducer queueProducer2 = new LinkedTransferQueueProducer(blockingQueue);
        new Thread(queueProducer2).start();

        LinkedTransferQueueConsumer queueConsumer1 = new LinkedTransferQueueConsumer(blockingQueue);
        new Thread(queueConsumer1).start();

        LinkedTransferQueueConsumer queueConsumer2 = new LinkedTransferQueueConsumer(blockingQueue);
        new Thread(queueConsumer2).start();
    }
}
