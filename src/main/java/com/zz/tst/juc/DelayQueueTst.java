package com.zz.tst.juc;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTst {

    public static class DelayedImpl implements Delayed {

        int ind;

        long startMills;
        long delayMills = 0l;

        public DelayedImpl(int ind) {
            this.ind = ind;
            this.startMills = System.currentTimeMillis();
            this.delayMills = new Random().nextInt(1000);
            System.out.println(this.delayMills);
        }

        public String toString() {
            return "ind: " + this.ind + ", startMills:" + startMills + ", delayMills:" + delayMills;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return this.startMills + this.delayMills - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.delayMills == o.getDelay(TimeUnit.SECONDS)) return 0;
            return this.delayMills < o.getDelay(TimeUnit.SECONDS) ? -1 : 1;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        DelayQueue delayQueue = new DelayQueue();

        for (int i = 0; i < 100; i++) {
            delayQueue.offer(new DelayedImpl(i));
        }

        System.out.println("take...");

        for (int i = 0; i < 1000; i++) {
            DelayedImpl delayedImpl = (DelayedImpl) delayQueue.take();
            System.out.println(delayedImpl.toString());
        }

        System.out.println("println...");
        System.out.println(delayQueue);
    }
}
