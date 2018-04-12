package com.dao.proxy.core.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理工厂类
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.proxy.core.dynamicproxy.DynamicProxyDao.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-04-12 16:14:00
 */
public class DynamicProxyFactory {
    /**
     * 需要被代理的类
     */
    private Object obj;

    /**
     * 构造方法必传被代理的对象
     *
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     * @param obj
     * @return
     */
    public DynamicProxyFactory(Object obj) {
        this.obj = obj;
    }

    /**
     * 获取代理
     *
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     * @param
     * @return java.lang.Object
     */
    public Object getInstance() {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                beforeProxy();
                Object invoke = method.invoke(obj, args);
                afterProxy();

                return invoke;
            }
        });
    }

    /**
     * 前置处理
     *
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    private void beforeProxy(){
        System.out.println("======动态代理前置处理=======");
    }

    /**
     * 后置处理
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    private void afterProxy(){
        System.out.println("======动态代理后置处理=======");
    }
}
