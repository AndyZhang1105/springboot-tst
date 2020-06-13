package com.zz.tst.thread;

public class TwoMethods {

    public static void main(String[] args) {

        Thread t1 = new Thread(new MyThread1());
        t1.start();    //启动子线程

        Thread t2 = new MyThread2();
        t2.start();    //启动子线程

        //主线程继续同时向下执行
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
            try {
                Thread.sleep(1000);    //当前main线程暂停1000ms, 如果线程持有锁，sleep方法结束前并不会释放该锁
            } catch (InterruptedException e) {
            }
        }
    }

}

class MyThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
    }
}

class MyThread2 extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
    }
}