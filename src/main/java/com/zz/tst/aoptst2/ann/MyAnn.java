package com.zz.tst.aoptst2.ann;

import com.zz.tst.aoptst2.config.MyBeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  自定义注解
 */
@Import(MyBeanPostProcessor.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnn {
}

