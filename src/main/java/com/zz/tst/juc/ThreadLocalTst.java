package com.zz.tst.juc;

import static java.lang.Thread.sleep;

public class ThreadLocalTst {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>() {
        // 实现initialValue()
        public Integer initialValue() {
            return 0;
        }
    };

    public int nextSeq(){
        seqCount.set(seqCount.get() + 1);

        return seqCount.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTst seqCount = new ThreadLocalTst();

        SeqThread thread1 = new SeqThread(seqCount);
        SeqThread thread2 = new SeqThread(seqCount);
        SeqThread thread3 = new SeqThread(seqCount);
        SeqThread thread4 = new SeqThread(seqCount);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        sleep(100);
        System.out.println("seqCount :" + seqCount.nextSeq());
    }

    private static class SeqThread extends Thread {
        private ThreadLocalTst seqCount;

        SeqThread(ThreadLocalTst seqCount){
            this.seqCount = seqCount;
        }

        public void run() {
            for(int i = 0 ; i < 3 ; i++){
                System.out.println(Thread.currentThread().getName() + " seqCount :" + seqCount.nextSeq());
            }
        }
    }
}
