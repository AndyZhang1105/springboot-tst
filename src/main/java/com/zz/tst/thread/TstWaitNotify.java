package com.zz.tst.thread;

class MyThread implements Runnable {
    private Object flag;
    private String threadName;

    public MyThread(Object flag, String threadName) {
            this.flag = flag;
            this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10;i++) {
                if(i == 3) {
                    synchronized (this.flag){
                        System.out.println("3秒后线程调用wait睡眠");
                        this.flag.wait();
                    }
                }
                System.out.println(this.threadName + " " + i);
                Thread.sleep(1);
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}


public class TstWaitNotify {

    public static void main(String[] args) {
        Object object = new Object();
        MyThread myThread1 = new MyThread(object,"thread1");
        MyThread myThread2 = new MyThread(object,"thread2");
        Thread test1 = new Thread(myThread1);
        test1.start();
        Thread test2 = new Thread(myThread2);
        test2.start();

        try {
            Thread.sleep(6000);
            System.out.println("6秒后唤醒线程");
            synchronized (object){
                object.notifyAll();
            }

            Thread.sleep(6000);
            System.out.println(test1.getName() + test1.getState() + test1.getId());
            System.out.println(test2.getName() + test2.getState() + test2.getId());
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

}
