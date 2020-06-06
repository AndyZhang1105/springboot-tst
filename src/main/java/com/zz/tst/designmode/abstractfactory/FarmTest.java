package com.zz.tst.designmode.abstractfactory;

public class FarmTest {

    public static void main(String[] args) {
        try {
            Animal a;
            Plant p;
            Farm af = (Farm) ReadXML2.getObject();
            a = af.newAnimal();
            p = af.newPlant();
            a.show();
            p.show();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
