package com.dao.proxy.core.staticproxy;

import org.junit.Test;

/**
 * 静态代理测试
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.proxy.core.staticproxy.StaticProxyTest.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-04-12 16:03:00
 */
public class StaticProxyTest {

    /**
     * 测试静态代理
     *
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    @Test
    public void test(){
        //声明静态代理类
        StaticProxy staticProxy =new StaticProxy();
        //执行代理方法
        staticProxy.say();
    }
}
