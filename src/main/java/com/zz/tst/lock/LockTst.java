package com.zz.tst.lock;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTst {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();    //注意这个地方

    public static void main(String[] args)  {
        final LockTst lockTst = new LockTst();

        new Thread() {
            @SneakyThrows
            public void run() {
                Thread.currentThread().sleep(1000);
                lockTst.insert(Thread.currentThread());
            }
        }.start();

        new Thread() {
            public void run() {
                lockTst.insert(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁" + lock.toString());
            for(int i=0; i<5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName() + "释放了锁" + lock.toString());
            lock.unlock();
        }
    }

}
