package com.zz.tst.aoptst2.config;

import com.zz.tst.aoptst2.service.Service;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization . " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization . " + beanName);
        // 把 bean 通过反射放进去
        if (beanName.equals("aopService")) {
            return Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(),new Class[]{Service.class}, new MyHandler(bean));
        }
        return bean;
    }
}

