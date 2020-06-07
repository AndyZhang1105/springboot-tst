package com.zz.tst.designmode.mediator;

/*
 * 中介者（Mediator）模式的定义：定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。
 * 中介者模式又叫调停模式，它是迪米特法则的典型应用。
 */

public class MediatorPattern {

    public static void main(String[] args) {
        Mediator md = new ConcreteMediator();
        Colleague c1,c2;
        c1 = new ConcreteColleague1();
        c2 = new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-------------");
        c2.send();
    }

}
