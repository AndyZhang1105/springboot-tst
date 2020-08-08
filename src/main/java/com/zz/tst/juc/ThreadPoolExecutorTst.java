package com.zz.tst.juc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTst {

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Task());
        }
    }

}
