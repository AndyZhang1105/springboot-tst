package com.zz.tst.designmode.bridge;

/*
 * 桥接（Bridge）模式的定义如下：将抽象与实现分离，使它们可以独立变化。
 * 是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度
 */

public class BridgeTest {
    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.Operation();
    }
}
