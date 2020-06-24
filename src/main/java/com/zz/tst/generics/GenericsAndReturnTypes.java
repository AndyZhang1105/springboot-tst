package com.zz.tst.generics;

interface GenericsGetter<T extends GenericsGetter<T>> {
    T get();
}

interface Getter extends GenericsGetter<Getter> {}

class GetterImpl implements Getter {

    @Override
    public Getter get() {
        System.out.println("Getter print.");
        return null;
    }
}

public class GenericsAndReturnTypes {
    void test(Getter g) {
        Getter result = g.get();
        GenericsGetter genericsGetter = g.get();
    }

    public static void main(String[] args) {
        GenericsAndReturnTypes ggg = new GenericsAndReturnTypes();
        ggg.test(new GetterImpl());
    }
}
