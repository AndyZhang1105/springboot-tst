package com.zz.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopTstController {

    @Autowired
    private Speakable aopPerson;

    @GetMapping("/aop/tst")
    public void aopTst() {
        System.out.println("****************** spring aop ******************");
        aopPerson.sayHi();
        aopPerson.sayBye();

        System.out.println("****************** jdk dynamic proxy ******************");
        Speakable jdkProxy = PersonProxyFactory.newJdkProxy();
        jdkProxy.sayHi();
        jdkProxy.sayBye();

        System.out.println("****************** cglib proxy ****************** ");
        AopPerson cglibProxy = PersonProxyFactory.newCglibProxy();
        cglibProxy.sayHi();
        cglibProxy.sayBye();
    }
}
