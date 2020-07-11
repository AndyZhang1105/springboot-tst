package com.zz.tst.aoptst2.aop;

import org.aspectj.lang.JoinPoint;
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

    @Around("pointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around start ---------------");
        pjp.proceed();
        System.out.println("around end ---------------");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after ---------------");
    }

    @AfterReturning(value = "pointcut())", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "结果返回" + result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void afterThrowing(Exception exception) {
        System.out.println("方法出现异常");
    }

}

