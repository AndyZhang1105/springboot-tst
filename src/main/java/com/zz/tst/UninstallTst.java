package com.zz.tst;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UninstallTst {

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @Author Zal test at 2020-05-25 23:05:40
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MyClassLoader classLoader1 = new MyClassLoader();
        classLoader1.setBasePath("/Users/andy/Documents/c_code/java_code/java-tst/target/classes/com/zz/util/");
        Class<?> clazz = classLoader1.loadClass("ClassUtil");

        @SuppressWarnings("unused")
        Object obj = clazz.newInstance();
        System.out.println("1:" + clazz.hashCode());
        System.out.println("clazz.getClassLoader(): " + clazz.getClassLoader().toString());

        obj = null;
        System.out.println("2:" + clazz.hashCode());

        classLoader1 = null;
        System.out.println("3:" + clazz.hashCode());

        clazz = null;
        System.out.println("===========");

        classLoader1 = new MyClassLoader("classLoader1");
        classLoader1.setBasePath("/Users/andy/Documents/c_code/java_code/java-tst/target/classes/com/zz/util/");
        clazz = classLoader1.loadClass("ClassUtil");

        System.out.println("4:" + clazz.hashCode());
        System.out.println("clazz.getClassLoader(): " + clazz.getClassLoader().toString());

        System.out.println("clazz: " + ToStringBuilder.reflectionToString(clazz, ToStringStyle.JSON_STYLE));
        System.out.println("clazz: " + ToStringBuilder.reflectionToString(clazz.getDeclaredMethods(), ToStringStyle.JSON_STYLE));

        System.out.println("run on main method " + (ClassLoader.getSystemClassLoader().toString()));
        Class<?> clazz2 = ClassLoader.getSystemClassLoader().loadClass("com.zz.util.ClassUtil");
        System.out.println("5:" + clazz2.hashCode());
        System.out.println("clazz2: " + ToStringBuilder.reflectionToString(clazz2.getDeclaredMethods(), ToStringStyle.JSON_STYLE));

        System.out.println("clazz2.getClassLoader(): " + clazz2.getClassLoader().toString());
    }

}
