package com.zz.tst.juc;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTst {

    public static void main(String[] args) {
        PriorityBlockingQueue pbq = new PriorityBlockingQueue(3);

        for (int i = 0; i < 100; i++) {
            pbq.put(new Random().nextInt(100));
        }
        System.out.println(pbq);

        for (int i = 0; i < 100; i++) {
            System.out.println(pbq.poll());
        }
        System.out.println(pbq);
    }

}
