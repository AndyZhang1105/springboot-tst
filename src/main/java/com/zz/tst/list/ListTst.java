package com.zz.tst.list;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListTst {

    public static void main(String[] args) {

        ArrayList arrayList = new ArrayList();
        System.out.println(arrayList.size());

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        System.out.println(arrayList);
        System.out.println(arrayList.toArray()[0]);

        LinkedList<String> linkedList = new LinkedList<String>();
        System.out.println(linkedList.size());

        linkedList.push("A");
        linkedList.push("B");
        linkedList.push("C");
        System.out.println(linkedList);
        System.out.println(linkedList.getFirst());
    }

}
