package com.zz.tst.designmode.abstractfactory;

public class SGfarm implements Farm {

    public Animal newAnimal() {
        System.out.println("新牛出生！");
        return new Cattle();
    }

    public Plant newPlant() {
        System.out.println("蔬菜长成！");
        return new Vegetables();
    }
}
