package com.zz.tst.designmode.observer;

/*
 * 观察者（Observer）模式的定义：指多个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 这种模式有时又称作发布-订阅模式、模型-视图模式，它是对象行为型模式。
 */

public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs1 = new ConcreteObserver1();
        Observer obs2 = new ConcreteObserver2();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();
    }

}
