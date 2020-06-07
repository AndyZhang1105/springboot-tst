package com.zz.tst.objecttst;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnalyzeObjectZ implements Cloneable {

    private AnalyzeObjectP p;
    private String type;

    public AnalyzeObjectZ(AnalyzeObjectP p, String type) {
        this.p = p;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(! (obj instanceof AnalyzeObjectZ) ) {
            return false;
        }

        AnalyzeObjectZ aObj = (AnalyzeObjectZ) obj;
        return this.p.equals(aObj.p) && aObj.type.equals(this.type);
    }

    @Override
    protected AnalyzeObjectZ clone() throws CloneNotSupportedException {
        // 浅克隆是在克隆时，基本数据类型直接拷贝，而引用类型只能拷贝引用地址
        AnalyzeObjectZ c = (AnalyzeObjectZ) super.clone();

        // 深克隆是重写clone方法，每次调用时， 先克隆一下引用类型，把克隆后的引用类型设置给克隆的对象
        c.p = p.clone();

        return c;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        AnalyzeObjectP p = new AnalyzeObjectP("ZZ", 100);
        AnalyzeObjectS s = new AnalyzeObjectS("ZZ", 100, "M");

        // 很显然，这并不符合对称性
        System.out.println("p.equals(s): " + p.equals(s));
        System.out.println("s.equals(p): " + s.equals(p));

        // 解决办法，将继承转为组合
        AnalyzeObjectZ z = new AnalyzeObjectZ(p, "M");
        System.out.println("p.equals(z): " + p.equals(z));
        System.out.println("z.equals(p): " + z.equals(p));

        // 覆盖了equals方法时，一定不能忘记覆盖hashCode方法
        AnalyzeObjectP p2 = new AnalyzeObjectP("ZZ", 100);
        System.out.println("p.equals(p2): " + p.equals(p2));
        System.out.println("p.hashCode() == p2.hashCode(): " + (p.hashCode() == p2.hashCode()));

        // clone方法, 对于对象中的所有引用类型，均需要实现Cloneable接口，并重写clone方法
        AnalyzeObjectP pp = p.clone();
        AnalyzeObjectS ss = s.clone();
        AnalyzeObjectZ zz = z.clone();
        System.out.println(pp);  // 调用toString方法
        System.out.println(ss);  // 调用toString方法
        System.out.println("pp.equals(p): " + pp.equals(p));
        System.out.println("ss.equals(s): " + ss.equals(s));
        System.out.println("zz.p.equals(z.p): " + zz.p.equals(z.p));
        System.out.println("(zz.p == z.p): " + (zz.p == z.p));

        System.out.println(s);   // 调用toString方法
        System.out.println(z);   // 调用toString方法
        System.out.println(pp);  // 调用toString方法
        System.out.println(ss);  // 调用toString方法

        p = null;
        System.gc();

        // 获得运行时类信息
        Class<?> objClass = s.getClass();
        System.out.println("The class of s is: " + objClass);

        // Integer.SIZE
        System.out.println("Integer.SIZE: " + Integer.SIZE);

        Object obj = new Object();
        System.out.println("obj.toString(): " + obj.toString());
    }

}
