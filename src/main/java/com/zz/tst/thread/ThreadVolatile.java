package com.zz.tst.thread;

public class ThreadVolatile {

    volatile int result;

    public int getResult() {
        System.out.println("get result start...");
        return result;
    }

    public synchronized void setResult(int result) throws InterruptedException {
        System.out.println("set result start...");
        Thread.currentThread().sleep(100);
        this.result = result;
        System.out.println("set result end.");
    }

    public static void main(String[] args) {
        ThreadVolatile threadVolatile = new ThreadVolatile();
        for (int i =  0; i < 8; i++) {
            new Thread (() -> {
                int x =  0 ;
                while (threadVolatile.getResult() <  100) {
                    x++;
                }
                System.out.println(x);
            }).start();
        }
        try {
            Thread.sleep(10);
            threadVolatile.setResult(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
