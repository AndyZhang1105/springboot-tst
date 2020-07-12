package com.zz.tst.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTst2 {

        Lock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();

        private int signal;

        public static void main(String[] args) {
            ConditionTst2 d = new ConditionTst2();
            A a = new A(d);
            B b = new B(d);
            C c = new C(d);

            new Thread(a).start();
            new Thread(b).start();
            new Thread(c).start();
        }

        public void a() {
            lock.lock();
            while(signal != 0) {
                try {
                    a.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("a");
            signal ++;
            b.signal();
            lock.unlock();
        }

        public void b() {
            lock.lock();
            while(signal != 1) {
                try {
                    b.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("b");
            signal ++;
            c.signal();
            lock.unlock();
        }

        public  void c () {
            lock.lock();
            while(signal != 2) {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("c");
            signal = 0;
            a.signal();
            lock.unlock();
        }

}

class A implements Runnable {

    private ConditionTst2 demo;

    public A(ConditionTst2 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {
            demo.a();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class B implements Runnable {

    private ConditionTst2 demo;

    public B(ConditionTst2 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {
            demo.b();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class C implements Runnable {

    private ConditionTst2 demo;

    public C(ConditionTst2 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {
            demo.c();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}