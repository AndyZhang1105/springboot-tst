package com.zz.tst.designmode.abstractfactory;

public class SRfarm implements Farm {

    public Animal newAnimal() {
        System.out.println("新马出生！");
        return new Horse();
    }

    public Plant newPlant() {
        System.out.println("水果长成！");
        return new Fruitage();
    }
}
