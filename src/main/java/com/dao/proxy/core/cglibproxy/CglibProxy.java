package com.dao.proxy.core.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib 代理
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.proxy.core.cglibproxy.CglibProxy.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-04-12 16:36:00
 */
public class CglibProxy implements MethodInterceptor {
    /**
     * 需要被代理的类
     */
    private Object obj;

    /**
     * 构造方法必须传入被代理的类
     *
     * @param obj
     * @return
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     */
    public CglibProxy(Object obj) {
        this.obj = obj;
    }

    public Object getProxyInstance() {
        /**
         * 1.工具类，允许为非接口类型创建一个 Java 代理。Enhancer 动态创建了给定类型的子类但是拦截了所有的方法。
         * 和 Proxy 不一样的是，不管是接口还是类他都能正常工作
         */
        Enhancer en = new Enhancer();
        /**
         * 2.设置父类
         */
        en.setSuperclass(obj.getClass());
        /**
         * 3.设置回掉函数(因为 MethodInterceptor 继承了 Callback 类,默认执行 intercept 方法)
         */
        en.setCallback(this);
        /**
         * 4.创建子类
         */
        return en.create();
    }

    /**
     * 实现 intercept 方法
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return java.lang.Object
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        beforeProxy();
        Object invoke = method.invoke(obj, objects);
        afterProxy();
        return invoke;
    }

    /**
     * 前置处理
     *
     * @param
     * @return void
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     */
    private void beforeProxy() {
        System.out.println("======CGLIB 代理前置处理=======");
    }

    /**
     * 后置处理
     *
     * @param
     * @return void
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     */
    private void afterProxy() {
        System.out.println("======CGLIB 代理后置处理=======");
    }
}
