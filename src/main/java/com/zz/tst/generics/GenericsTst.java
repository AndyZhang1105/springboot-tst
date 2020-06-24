package com.zz.tst.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class GenericsTst {

    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();
        fruit[1] = new Jonathan();
        try {
            fruit[0] = new Fruit();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            fruit[0] = new Orange();
        } catch (Exception e) {
            System.out.println(e);
        }

        // List<Fruit> flist = new ArrayList<Apple>(); // 编译错误

        ArrayList<Apple> appleList = new ArrayList<Apple>();
        appleList.add(new Apple());
        // List<Fruit> fruitList = appleList; // 编译错误

        List<? extends Apple> extendList = appleList;
        System.out.println(extendList.get(0));
        // extendList.add(new Apple());    // 编译错误

        List<? extends Apple> flist = new ArrayList<Apple>();
        // flist.add(new Apple());  // 编译错误
        // flist.add(new Fruit());  // 编译错误
        // flist.add(new Object());  // 编译错误


        List<? super Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple());
        apples.add(new Jonathan());
        // apples.add(new Fruit());  // 编译错误
        System.out.println(apples);

    }

    // Collections.java
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");

        if (srcSize < 10 || (src instanceof RandomAccess && dest instanceof RandomAccess)) {
            for (int i=0; i<srcSize; i++)
                dest.set(i, src.get(i));
        } else {
            ListIterator<? super T> di=dest.listIterator();
            ListIterator<? extends T> si=src.listIterator();
            for (int i=0; i<srcSize; i++) {
                di.next();
                di.set(si.next());
            }
        }
    }

}

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

