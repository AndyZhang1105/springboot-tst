package com.zz.tst.aop;

import org.springframework.stereotype.Service;

@Service
public class AopPerson implements Speakable {

    @Override
    public void sayHi() {
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hi!!");
    }

    @Override
    public void sayBye() {
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bye!!");
    }
}

