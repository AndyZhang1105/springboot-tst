package com.zz.tst.juc;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTst {

    private LinkedList<String> buffer;
    private int maxSize;
    private Lock lock;
    private Condition fullCondition;
    private Condition notFullCondition;

    public ConditionTst(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void set(String string) throws InterruptedException {
        lock.lock();
        try {
            while (maxSize == buffer.size()) {
                System.out.println("notFullCondition.await();");
                notFullCondition.await();
            }
            buffer.add(string);
            System.out.println("fullCondition.signal();");
            fullCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public String get() throws InterruptedException {
        String string;
        lock.lock();
        try {
            while (buffer.size() == 0) {
                System.out.println("fullCondition.await();");
                fullCondition.await();
            }
            string = buffer.poll();
            System.out.println("notFullCondition.signal();");
            notFullCondition.signal();
        } finally {
            lock.unlock();
        }
        return string;
    }

    public static void main(String[] args) {
        ConditionTst conditionTst = new ConditionTst(3);

        for (int i = 0; i < 100; i++) {
            final int iii = i;
            new Thread() {
                @SneakyThrows
                public void run() {
                    conditionTst.set(String.valueOf(iii));
                }
            }.start();
        }

        for (int i = 0; i < 100; i++) {
            final int iii = i;
            new Thread() {
                @SneakyThrows
                public void run() {
                    System.out.println(conditionTst.get());
                }
            }.start();
        }
    }

}
