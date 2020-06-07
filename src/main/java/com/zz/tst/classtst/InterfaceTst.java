package com.zz.tst.classtst;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Hello {
    void morning(String name);
}

public class InterfaceTst {

    /*
      在运行期动态创建一个interface实例的方法如下：
        定义一个InvocationHandler实例，它负责实现接口的方法调用；
        通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
            使用的ClassLoader，通常就是接口类的ClassLoader；
            需要实现的接口数组，至少需要传入一个接口进去；
            用来处理接口方法调用的InvocationHandler实例。
        将返回的Object强制转型为接口。
     */
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if(method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello) Proxy.newProxyInstance(
            Hello.class.getClassLoader(),
            new Class[] {Hello.class},
            handler
        );
        hello.morning("Zal");
    }
}

/*
 动态代理实际上是JDK在运行期动态创建class字节码并加载的过程，它并没有什么黑魔法，把上面的动态代理改写为静态实现类大概长这样
 */
class HelloDynamicProxy implements Hello {
    InvocationHandler handler;
    public HelloDynamicProxy(InvocationHandler handler) {
        this.handler = handler;
    }
    public void morning(String name) {
        try {
            handler.invoke(this, Hello.class.getMethod("morning"), new Object[]{name});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
