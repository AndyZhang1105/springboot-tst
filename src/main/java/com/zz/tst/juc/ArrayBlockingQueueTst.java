package com.zz.tst.juc;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTst {

    public static void main(String[] args) {
        ArrayBlockingQueue abq = new ArrayBlockingQueue(300, true);

        for (int i = 0; i < 100; i++) {
            abq.add(i);
        }
        System.out.println(abq);

        for (int i = 0; i < 100; i++) {
            System.out.println(abq.poll());
        }
        System.out.println(abq);
    }
}
