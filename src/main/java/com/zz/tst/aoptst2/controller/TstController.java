package com.zz.tst.aoptst2.controller;

import com.zz.tst.aoptst2.config.AopConfiguration;
import com.zz.tst.aoptst2.service.AopService;
import com.zz.tst.aoptst2.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TstController {

    @Autowired
    private Service service;

    @GetMapping("/aop/tst2")
    public void aopTst2() {
        System.out.println("****************** spring aop ******************");
        service.query();
    }

    public static void main(String[] args) {
        // 初始化 bean
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfiguration.class);
        // context.getBean()的底层就是 map.get()方法 DefaultSingletonBeanRegistry 类下  getSingleton() 方法中
        // 代理对象不是在get的时候代理的,而是在init的时候完成的
        context.getBean(Service.class).query();

        Service service = new AopService();
        service.query();
    }

}
