package com.zz.tst;

import java.lang.reflect.Field;

class Car extends Object {
    private String name = "car";
    public String getName() {
        return name;
    }
}

class SUV extends Car {
    private String name = "suv";

    @Override
    public String getName() {
        return name;
    }
}

class Tiguan extends SUV {
    private String name = "tiguan";

    @Override
    public String getName() {
        return name;
    }
}

public class ClassExtend {

    public static void main(String[] args) {
        try {
            // 使用forName方法与newInstance方法生成实例
            Car tiguan = new Tiguan();
            Car tiguanCar = (Car) Class.forName("com.zz.tst.Tiguan").newInstance();

            // 输出"tiguan"
            System.out.println(tiguanCar.getName());

            // 从上面的输出可以看出，tiguanCar是一个Tiguan实例，将其转换为SUV的类
            // 注意，此处只能窄化tiguanCar的范围，本质上仍然是Tiguan.class
            Class<? extends SUV> tiguanClass = tiguanCar.getClass().asSubclass(SUV.class);
            System.out.println(tiguanClass); // 输出"class Tiguan"

            // 将tiguanCar转换为SUV实例,tiguanCar是一个Tiguan类的实例，作用与(SUV) tiguanCar一样。
            SUV suv = SUV.class.cast(tiguanCar);
            // 输出"tiguan"
            System.out.println(suv.getName());

            // 获取Tiguan类声明的字段
            Field[] tiguanFields = tiguanClass.getDeclaredFields();
            for (Field field : tiguanFields) {            // 输出"name"
                System.out.println(field.getName());
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}