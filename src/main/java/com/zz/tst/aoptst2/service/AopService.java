package com.zz.tst.aoptst2.service;

import org.springframework.stereotype.Component;

@Component
public class AopService implements Service {

    public void query() {
        System.out.println("spring ---- query --- ");
    }

}
