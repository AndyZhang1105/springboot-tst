package com.zz.tst.juc;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTst {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();    //注意这个地方

    public static void main(String[] args)  {
        final TryLockTst tryLockTst = new TryLockTst();

        new Thread() {
            @SneakyThrows
            public void run() {
                tryLockTst.insert(Thread.currentThread());
            }
        }.start();

        new Thread() {
            public void run() {
                tryLockTst.insert(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁" + lock.toString());
                for(int i=0; i<5; i++) {
                    arrayList.add(i);
                }
                thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(thread.getName() + "释放了锁" + lock.toString());
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "没有获取到锁" + lock.toString());
        }
    }
}
