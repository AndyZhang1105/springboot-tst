package com.zz.tst.juc;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTst {

    public static void main(String[] args) {
        ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();
        for (int i = 0; i < 100; i++) {
            clq.add(i);
        }
        System.out.println(clq);

        for (int i = 0; i < 100; i++) {
            System.out.println(clq.poll());
        }
    }
}
