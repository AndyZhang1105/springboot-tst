package com.zz.tst.hash;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashTst {

    private HashMap forTestLockHashMap = new HashMap();

    public static void main(String[] args) {
        testHashMap();
        sortByHashMap();
        testHashTable();
        testConcurentHashMap();
        testLinkedHashMap();

        (new HashTst()).testLockWithHashMap();

        System.out.println("hashCode: " + (new HashTst()).hashCode());
        System.out.println("hashCode: " + (new HashTst()).hashCode());
        System.out.println("hashCode: " + "This is value for hash1".hashCode());
        System.out.println("hashCode: " + "This is value for hash2".hashCode());
    }

    public static void testHashMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("A", "This is A value");

        System.out.println("hashMap.size: " + hashMap.size());
        System.out.println("hashMap: " + hashMap);


        /**
         * 打印在数组中出现n/2以上的元素
         * 利用一个HashMap来存放数组元素及出现的次数
         */
        int [] a = {2,3,2,2,1,4,2,2,2,7,9,6,2,2,3,1,0};

        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i<a.length; i++) {
            if(map.containsKey(a[i])) {
                int tmp = map.get(a[i]);
                tmp += 1;
                map.put(a[i], tmp);
            } else {
                map.put(a[i], 1);
            }
        }
        Set<Integer> set = map.keySet(); //------------1------------
        for (Integer s : set) {
            if (map.get(s) >= a.length / 2) {
                System.out.println("数组中出现n/2以上的元素: " + s);
            }
        }

        /*
        HashMap中Value可以相同，但是键不可以相同
         */
        HashMap<String,Integer> mHashMap = new HashMap<String,Integer>();

        //出入两个Value相同的值，没有问题
        mHashMap.put("egg", 1);
        mHashMap.put("niu", 1);

        //插入key相同的值，看返回结果
        int egg = (Integer) mHashMap.put("egg", 3);

        System.out.println(egg);   //输出1
        System.out.println(mHashMap.get("egg"));   //输出3，将原值1覆盖
        System.out.println(mHashMap.get("niu"));   //输出1
    }

    public static void sortByHashMap() {
        int data[] = {2, 5, 2, 3, 5, 2, 3, 5, 2, 3, 5, 2, 3, 5, 2, 7, 8, 8, 7, 8, 7, 9, 0};
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : data) {
            if (map.containsKey(i)) {//判断HashMap里是否存在
                map.put(i, map.get(i) + 1);//已存在，值+1
            } else {
                map.put(i, 1);//不存在，新增
            }
        }
        //map按值排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        for (Map.Entry<Integer, Integer> m : list) {
            System.out.println(m.getKey() + "-" + m.getValue());
        }
    }

    public static void testHashTable() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("B", "This is B value");

        System.out.println("hashtable.size: " + hashtable.size());
        System.out.println("hashtable: " + hashtable);
    }

    public static void testConcurentHashMap() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("C", "This is C value");

        System.out.println("concurrentHashMap.size: " + concurrentHashMap.size());
        System.out.println("concurrentHashMap: " + concurrentHashMap);
    }

    public static void testLinkedHashMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("first", 1);
        linkedHashMap.put("second", 2);
        linkedHashMap.put("third", 3);
        System.out.println("linkedHashMap: " + linkedHashMap);
    }

    public void testLockWithHashMap() {

        Thread t1 = new Thread() {
            public void run() {
            for (int i = 0; i < 50000; i++) {
                forTestLockHashMap.put(new Integer(i), i);
            }
            System.out.println("t1 over");
            }
        };

        Thread t2 = new Thread() {
            public void run() {
            for (int i = 0; i < 50000; i++) {
                forTestLockHashMap.put(new Integer(i), i);
            }
            System.out.println("t2 over");
            }
        };

        t1.start();
        t2.start();
    }

}
