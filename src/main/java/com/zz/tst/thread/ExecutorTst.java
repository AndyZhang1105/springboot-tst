package com.zz.tst.thread;

import java.util.concurrent.*;

public class ExecutorTst {

    private static Executor executor = Executors.newFixedThreadPool(10);
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            executor.execute(new Task());
        }

        Future<String> future = executorService.submit(new Task2());
        System.out.println("do other things");
        try {
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class Task2 implements Callable {
        @Override
        public Object call() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "This is future case from thread: " + Thread.currentThread().getName();
        }
    }

}
