package com.zz.tst;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class GalaryCollectionTst {

    public static void main(String[] args) {

        for(int i = 0; i < 10000; i++) {
            List<String> list = new ArrayList<String>();
            list.add("aaaaaaaaaaaaaa");
        }
        System.gc();


        // 强引用
        Object obj = new Object();

        // 软引用是用来描述一些还有用但并非必需的对象
        SoftReference<Object> sf = new SoftReference<Object>(obj);

        // 弱引用也是用来描述非必需对象的，但是它的强度比软引用更弱一些，被弱引用关联的对象，只能生存到下一次垃圾收集发生之前。
        WeakReference<Object> wf = new WeakReference<Object>(obj);

        // 虚引用也成为幽灵引用或者幻影引用, 为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个系统通知。
        PhantomReference<Object> pf = new PhantomReference<Object>(obj, null);

        System.out.println(obj.toString());
        System.out.println(sf.get().toString());
        System.out.println(wf.get().toString());
        System.out.println(pf.get().toString());    // java.lang.NullPointerException
    }

}
