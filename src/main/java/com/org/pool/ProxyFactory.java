package com.org.pool;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactory<T> implements InvocationHandler, MethodInterceptor {
    //被代理类的对象
    private T target;

    public ProxyFactory(T target) {
        this.target = target;
    }

    /**
     * proxy:代表动态代理对象
     * method：代表正在执行的方法
     * args：代表调用目标方法时传入的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //代理过程中插入其他操作
        System.out.println("代");
        Object result = method.invoke(target, args);
        return result;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //代理过程中插入其他操作
        System.out.println("代2");
        Object result = method.invoke(target, objects);
        return result;
    }

    // 创建代理对象

    public Object getProxyInstance() {
        // 1.cglib工具类
        Enhancer en = new Enhancer();
        // 2.设置父类
        en.setSuperclass(this.target.getClass());
        // 3.设置回调函数
        en.setCallback(this);
        return en.create();
    }

}
