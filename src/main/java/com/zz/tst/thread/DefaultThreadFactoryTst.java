package com.zz.tst.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class DefaultThreadFactoryTst {

    /**
     * 默认5条线程（默认数量，即最少数量），
     * 最大20线程（指定了线程池中的最大线程数量），
     * 空闲时间0秒（当线程池梳理超过核心数量时，多余的空闲时间的存活时间，即超过核心线程数量的空闲线程，在多长时间内，会被销毁），
     * 等待队列长度1024，
     * 线程名称[MXR-Task-%d],方便回溯，
     * 拒绝策略：当任务队列已满，抛出RejectedExecutionException
     * 异常。
     */
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 20, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024)
            , new ThreadFactoryBuilder().setNameFormat("My-Task-%d").build()
            , new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws InterruptedException {

        DefaultThreadFactory threadFactory = new DefaultThreadFactory();
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask("TASK-BBB-" + i);
            threadFactory.newThread(new MyTask("TASK-AAA-" + i)).start();
            sleep(10);
        }

        sleep(10000);

        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask("TASK-BBB-" + i);
            threadPool.execute(myTask);
            sleep(10);
        }

        /**
         * 如何扩展线程池，重写 beforeExecute, afterExecute, terminated 方法，这三个方法默认是空的。
         * 可以监控每个线程任务执行的开始和结束时间，或者自定义一些增强。
         * 在 Worker 的 runWork 方法中，会调用这些方法
         */
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成： " + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        sleep(10000);

        for (int i = 0; i < 5; i++) {
            MyTask myTask = new MyTask("TASK-GEYM-" + i);
            es.execute(myTask);
            sleep(10);
        }

        es.shutdown();
    }

    static class MyTask implements Runnable {

        String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行：Thread ID:" + Thread.currentThread().getId() + ", Task Name = " + name);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
