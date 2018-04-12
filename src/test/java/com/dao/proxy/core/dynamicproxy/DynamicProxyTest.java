package com.dao.proxy.core.dynamicproxy;

import com.dao.proxy.common.ProxyInterface;
import com.dao.proxy.common.UserDao;
import org.junit.Test;

/**
 * 动态代理测试类
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.proxy.core.dynamicproxy.DynamicProxyTest.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-04-12 16:23:00
 */
public class DynamicProxyTest {

    /**
     * 测试动态代理类
     *
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    @Test
    public void test(){
        //动态代理工厂类生产动态代理对象
        ProxyInterface target= new UserDao();
        DynamicProxyFactory dynamicProxyFactory=new DynamicProxyFactory(target);
        //实例化代理类
        ProxyInterface dynamicProxy = (ProxyInterface)dynamicProxyFactory.getInstance();
        //动态生成的代理类执行代理方法
        dynamicProxy.say();

        System.out.println("原生对象："+target.getClass());
        System.out.println("代理对象："+dynamicProxy.getClass());
    }
}
