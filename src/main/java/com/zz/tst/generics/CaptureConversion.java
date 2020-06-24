package com.zz.tst.generics;

import javax.xml.ws.Holder;

public class CaptureConversion {

    static <T> void f1(Holder<T> holder) {
        T t = holder.value;
        System.out.println(t.getClass().getSimpleName());
    }

    static void f2(Holder<?> holder) {
        f1(holder);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        f2(raw);
        Holder rawBasic = new Holder(new Object());
        f2(rawBasic);
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }

}
