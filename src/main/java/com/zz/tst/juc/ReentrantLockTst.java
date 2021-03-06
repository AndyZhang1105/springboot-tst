package com.zz.tst.juc;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTst {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();    //注意这个地方

    public static void main(String[] args)  {
        final ReentrantLockTst test = new ReentrantLockTst();

        new Thread() {
            @SneakyThrows
            public void run() {
                Thread.currentThread().sleep(1000);
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread) {
        lock.tryLock();
        try {
            System.out.println(thread.getName()+"得到了锁" + lock.toString());
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName()+"释放了锁" + lock.toString());
            lock.unlock();
        }
    }

}
