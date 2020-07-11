package com.zz.tst.aoptst2.service;

import org.springframework.stereotype.Component;

@Component
public class AopService implements Service {

    public String query() {
        System.out.println("spring ---- query --- ");
        return "Here is result.";
    }

}
