package com.dao.proxy.core.cglibproxy;

import com.dao.proxy.common.UserDao;
import org.junit.Test;

/**
 * CGLIB 代理测试类
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.proxy.core.cglibproxy.CglibProxyTest.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-04-12 16:43:00
 */
public class CglibProxyTest {
    /**
     * CGLIB 代理测试
     *
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    @Test
    public void test(){
        //cglib 代理对象
        UserDao target= new UserDao();
        UserDao cglibProxy=(UserDao)new  CglibProxy(target).getProxyInstance();
        //执行方法
        cglibProxy.say();

        System.out.println("原生对象："+target.getClass());
        System.out.println("代理对象："+cglibProxy.getClass());
    }
}
