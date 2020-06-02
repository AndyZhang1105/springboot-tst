package com.zz.aop;

import com.zz.aop.cglib.CGLibProxy;
import com.zz.aop.jdk.DynamicProxy;

public class PersonProxyFactory {

    public static Speakable newJdkProxy() {
        DynamicProxy dynamicProxy = new DynamicProxy(new AopPerson());
        Speakable proxy = dynamicProxy.getProxy();
        return proxy;
    }

    public static AopPerson newCglibProxy() {
        CGLibProxy cgLibProxy = CGLibProxy.getInstance();
        AopPerson proxy = cgLibProxy.getProxy(AopPerson.class);
        return proxy;
    }
}
