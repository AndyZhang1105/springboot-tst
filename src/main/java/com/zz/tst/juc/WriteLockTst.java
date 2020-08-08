package com.zz.tst.juc;

import lombok.SneakyThrows;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteLockTst {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final WriteLockTst readWriteLockTst = new WriteLockTst();

        new Thread(){
            @SneakyThrows
            public void run() {
                readWriteLockTst.get(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run() {
                readWriteLockTst.get(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run() {
                readWriteLockTst.write(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run() {
                readWriteLockTst.write(Thread.currentThread());
            }
        }.start();

    }

    @SneakyThrows
    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 100) {
                System.out.println(thread.getName()+"正在进行读操作");
                thread.sleep(10);
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }

    @SneakyThrows
    public void write(Thread thread) {
        rwl.writeLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 100) {
                System.out.println(thread.getName()+"正在进行写操作");
                thread.sleep(10);
            }
            System.out.println(thread.getName()+"写操作完毕");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
