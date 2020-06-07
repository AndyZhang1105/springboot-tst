package com.zz.tst.classtst;

import com.zz.tst.objecttst.AnalyzeObjectP;
import com.zz.tst.objecttst.AnalyzeObjectS;
import com.zz.util.ClassUtil;
import com.zz.util.ObjectAnalyzer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassTst {

    private String name;

    private ClassTst() {
        System.out.println("ClassTst's private constructor is called.");
    }

    private ClassTst(int a, int b) {
        System.out.println("ClassTst's private constructor is called. a: " + a + ", b: " + b);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }

    static boolean isClassPresent(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static LogFactory createLog4j() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (LogFactory) Class.forName("org.apache.logging.log4j.Logger").newInstance();
    }

    static LogFactory createJdkLog() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return null;    // need to write in details
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class cls = null;

        try {
            cls = Class.forName("com.zz.tst.classtst.ClassTst");
            System.out.println("cls: " + cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Object obj = cls.newInstance();
            System.out.println("obj.getClass(): " + obj.getClass());
            for (Field field : obj.getClass().getDeclaredFields()) {
                System.out.println(field.getName());
            }
            System.out.println("obj.toString()" + obj.toString());

            Field f2 = cls.getDeclaredField("name");
            f2.setAccessible(true);  // 启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
            System.out.println("Before update: " + obj);
            f2.set(obj, "BB");  // 使用反射机制可以打破封装性，导致了java对象的属性不安全。建议不要过多的使用反射，反射是很脆弱的，编译器很难帮助人们发现程序中的错误，只有在运行时才会发现错误并且导致异常
            System.out.println("After update: " + obj);

            if(obj instanceof ClassTst) {
                ClassTst ct = (ClassTst) obj;
                ct.name = "ZZ";
                System.out.println("obj.toString()" + obj.toString());
            }

            System.out.println("cls.getSigners(): " + cls.getSigners());

            // 获取构造方法
            Constructor [] constructors = cls.getDeclaredConstructors();
            for(Constructor constructor : constructors){
                System.out.println(constructor.toString());
            }

            AnalyzeObjectS s = new AnalyzeObjectS("ZZ", 100, "M");
            Field fd = ClassUtil.getDeclaredField(s, "name");
            System.out.println(fd.getName());
            System.out.println(fd.get(s));
            fd.set(s, "ZZ2");
            System.out.println(fd.get(s));


            Method md = ClassUtil.getDeclaredMethod(s, "toString");
            System.out.println("md.invoke(s): " + md.invoke(s));
            // 调用静态方法时，由于无需指定实例对象，所以invoke方法传入的第一个参数永远为null
            Method m = Integer.class.getMethod("parseInt", String.class);
            System.out.println((Integer) m.invoke(null, "12345"));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        long l = 111l;
        Long L = 222l;
        System.out.println("l: " + l);
        System.out.println("l.getClass(): " + L.getClass());
        System.out.println("long.class.isPrimitive(): " + long.class.isPrimitive());
        System.out.println("Long.class.isPrimitive(): " + Long.class.isPrimitive());
        System.out.println("void.class.isPrimitive(): " + void.class.isPrimitive());
        System.out.println("void.class: " + void.class.getSuperclass());


        int[] arr = new int[5];
        System.out.println("arr.getClass(): " + arr.getClass());
        System.out.println("arr.getClass().getSuperclass(): " + arr.getClass().getSuperclass());

        Integer iii = 123;
        System.out.println("iii.getClass(): " + iii.getClass());
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.toHexString(Integer.MIN_VALUE): " + Integer.toHexString(Integer.MIN_VALUE));
        System.out.println("Integer.toHexString(Integer.MAX_VALUE): " + Integer.toHexString(Integer.MAX_VALUE));

        Class cls1 = String.class;
        Class cls2 = int.class;
        Class cls3 = Double[].class;
        System.out.println("cls1: " + cls1);
        System.out.println("cls2: " + cls2);
        System.out.println("cls3: " + cls3);

        Class cls4 = Class.forName("java.lang.String");
        Class cls5 = "aaa".getClass();
        System.out.println("cls4 == cls5: " + (cls4 == cls5));

        Integer n = new Integer(123);
        System.out.println("n instanceof Integer: " + (n instanceof Integer));
        System.out.println("n instanceof Number: " + (n instanceof Number));
        System.out.println("n.getClass() == Integer.class: " + (n.getClass() == Integer.class));
        System.out.println("Number.class: " + Number.class);    // n.getClass() == Number.class; // false

        String s1 = String.class.newInstance();
        System.out.println("s1: " + s1);

        try {
            Object tst3 = Class.forName("java.lang.Integer").newInstance();
        } catch (Exception e) {
            System.out.println("通过Class.NewInstance()调用私有构造函数【失败】");
        }

        /*以下调用无参的、私有构造函数*/
        Constructor c0 = Class.forName("com.zz.tst.classtst.ClassTst").getDeclaredConstructor();
        c0.setAccessible(true); //启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
        ClassTst tst1 = (ClassTst) c0.newInstance();
        /*以下调用带参的、私有构造函数*/
        Constructor c1 = Class.forName("com.zz.tst.classtst.ClassTst").getDeclaredConstructor(new Class[]{int.class, int.class});
        c1.setAccessible(true);
        ClassTst tst2 = (ClassTst) c1.newInstance(new Object[]{5,6});

        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);

        // 利用JVM动态加载特性
        LogFactory factory = null;
        if (isClassPresent("org.apache.logging.log4j.Logger")) {
            factory = createLog4j();
        } else {
            factory = createJdkLog();
        }
        System.out.println("factory: " + factory);

        // 如果是两个Class实例，要判断一个向上转型是否成立，可以调用isAssignableFrom()
        System.out.println(Integer.class.isAssignableFrom(Integer.class)); // true，因为Integer可以赋值给Integer
        System.out.println(Number.class.isAssignableFrom(Integer.class));  // true，因为Integer可以赋值给Number
        System.out.println(Object.class.isAssignableFrom(Integer.class));  // true，因为Integer可以赋值给Object
        System.out.println(Integer.class.isAssignableFrom(Number.class));  // false，因为Number不能赋值给Integer
        System.out.println(AnalyzeObjectP.class.isAssignableFrom(AnalyzeObjectS.class));  // true
        System.out.println(AnalyzeObjectS.class.isAssignableFrom(AnalyzeObjectP.class));  // false
        AnalyzeObjectS s = new AnalyzeObjectS("ZZZ", 101, "M");
        AnalyzeObjectP p = s;
        System.out.println("p: " + p);

        System.out.println((new ObjectAnalyzer()).toString(s));
        System.out.println((new ObjectAnalyzer()).toString(arr));
    }
}
