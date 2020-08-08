package com.zz.tst.juc;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class SemaphoreTst {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 100; i++) {
            new Thread() {
                @SneakyThrows
                public void run() {
                    semaphore.acquire(1);
                    System.out.println("Thread " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    semaphore.release(1);
                }
            }.start();
        }
    }

}
