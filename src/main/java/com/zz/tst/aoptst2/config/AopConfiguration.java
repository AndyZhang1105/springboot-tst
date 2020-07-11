package com.zz.tst.aoptst2.config;

import com.zz.tst.aoptst2.ann.MyAnn;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.zz.tst.aoptst2")
//@EnableAspectJAutoProxy
@MyAnn
public class AopConfiguration {

}
