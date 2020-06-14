package com.zz.tst.objecttst;

import lombok.SneakyThrows;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTst3 {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final LockTst3 test = new LockTst3();

        new Thread(){
            @SneakyThrows
            public void run() {
                Thread.currentThread().sleep(1);
                test.get(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();

        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }

    public void get2(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }

}
