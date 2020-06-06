package com.zz.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 直接通过反射获取子类的对象是不能得到父类的属性值的，必须根据反射获得的子类 Class 对象在调用  getSuperclass() 方法获取父类对象，然后在通过父类对象去获取父类的属性值

 setAccessible(true)可能会失败。如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)。
 例如，某个SecurityManager可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全
 */

public class ClassUtil {

    public static Field getDeclaredField(Object obj, String fieldName) {
        Field field = null;
        Class c = obj.getClass();
        for(; c != Object.class ; c = c.getSuperclass()){
            try {
                field = c.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行c = c.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    public static Method getDeclaredMethod(Object obj, String methodName) {
        Method method = null;
        Class c = obj.getClass();
        for(; c != Object.class ; c = c.getSuperclass()){
            try {
                method = c.getDeclaredMethod(methodName);
                method.setAccessible(true);
                return method;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行c = c.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

}