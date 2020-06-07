package com.zz.tst.designmode.composite;

/*
 * 组合（Composite）模式的定义：有时又叫作部分-整体模式，它是一种将对象组合成树状的层次结构的模式，
 * 用来表示“部分-整体”的关系，使用户对单个对象和组合对象具有一致的访问性。
 */
public class CompositePattern {

    public static void main(String[] args) {
        Component c0 = new Composite();
        Component c1 = new Composite();

        Component leaf1 = new Leaf("1");
        Component leaf2 = new Leaf("2");
        Component leaf3 = new Leaf("3");

        c0.add(leaf1);
        c0.add(c1);

        c1.add(leaf2);
        c1.add(leaf3);

        c0.operation();
    }

}
