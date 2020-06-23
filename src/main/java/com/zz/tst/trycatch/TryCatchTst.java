package com.zz.tst.trycatch;

public class TryCatchTst {

    public static void main(String[] args) {
        System.out.println("1. 我是输出结果a:" + testTry());
        System.out.println("2. 我是输出结果a:" + testCatch());
    }

    public static int testTry() {
        int a = 5;
        try {
            System.out.println(a / 1);
            a = 10;
            return a;
        } catch (ArithmeticException e) {
            System.out.println("我是catch中的a:" + a);
            return a = 20;// 程序执行到这时，a=20已经执行完，准备好返回结果了，发现有finally，则在去执行finally
        } finally {
            System.out.println("我是finally中未重定义的a:" + a);
            a = 30;
            System.out.println("我是finally中重定义过的a:" + a);
            return a;
        }
    }

    public static int testCatch() {
        int a = 5;
        try {
            System.out.println(a / 0);
            a = 10;
            return a;
        } catch (ArithmeticException e) {
            System.out.println("我是catch中的a:" + a);
            return a = 20;// 程序执行到这时，a=20已经执行完，准备好返回结果了，发现有finally，则在去执行finally
        } finally {
            System.out.println("我是finally中未重定义的a:" + a);
            a = 30;
            System.out.println("我是finally中重定义过的a:" + a);
            return a;
        }
    }

}
