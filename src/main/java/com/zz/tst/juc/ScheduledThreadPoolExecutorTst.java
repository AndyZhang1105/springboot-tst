package com.zz.tst.juc;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTst {

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor1 = new ScheduledThreadPoolExecutor(3);
        for (int i = 0; i < 10; i++) {
            executor1.schedule(new ThreadPoolExecutorTst.Task(), new Random().nextInt(10), TimeUnit.SECONDS);
        }

        ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor2.scheduleWithFixedDelay(new ThreadPoolExecutorTst.Task(), 10, new Random().nextInt(10), TimeUnit.SECONDS);
        }
    }
}
