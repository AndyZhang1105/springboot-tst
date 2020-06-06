package com.zz.tst;

import java.util.HashSet;
import java.util.Set;

public class EqualHashTst {

    private final String name;
    private final int age;
    private final  char sex;

    public EqualHashTst(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof EqualHashTst)) return false;
        EqualHashTst objPerson = (EqualHashTst) obj;
        return this.name.equals(objPerson.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name + ": Age(" + this.age + ") Sex(" + this.sex+ ")";
    }

    public static void main(String[] args) {
        EqualHashTst person1 = new EqualHashTst("Steven", 20, 'M');
        EqualHashTst person2 = new EqualHashTst("Steven", 30, 'F');
        EqualHashTst person3 = new EqualHashTst("Steveo", 20, 'M');

        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
        System.out.println("person1.equals(person2): " + person1.equals(person2));
        System.out.println("person1.equals(person3): " + person1.equals(person3));

        Set<EqualHashTst> set = new HashSet<EqualHashTst>();
        set.add(person1);
        System.out.println("set.contains(person1): " + set.contains(person1));
        System.out.println("set.contains(person2): " + set.contains(person2));
        System.out.println("set.contains(person3): " + set.contains(person3));

        System.out.println("Steven".hashCode());
        System.out.println("Steven".hashCode());
        System.out.println("Steveo".hashCode());
    }

}
