package com.zz.tst.aoptst2.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {

    private Object object;

    public MyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("反射执行了, invoke from: " + proxy.getClass().getName());
        return method.invoke(object,args);
    }
}
