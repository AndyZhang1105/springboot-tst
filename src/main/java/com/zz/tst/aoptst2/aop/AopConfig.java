package com.zz.tst.aoptst2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *  定义的切面
 **/
@Aspect
@Component
public class AopConfig {

    /**
     * 设置切点
     */
    @Pointcut("execution (* com.zz.tst.aoptst2.service..*.*(..))")
    public void pointcut() {
    }

    /**
     * 在切点之前执行的代码
     */
    @Before("pointcut()")
    public void before() {
        System.out.println("before ---------------");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after ---------------");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around start ---------------");
        pjp.proceed();
        System.out.println("around end ---------------");
    }

}

