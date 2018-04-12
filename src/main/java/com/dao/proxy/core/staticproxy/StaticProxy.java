package com.dao.proxy.core.staticproxy;

import com.dao.proxy.common.ProxyInterface;
import com.dao.proxy.common.UserDao;

/**
 * 代理类
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.proxy.core.staticproxy.StaticProxy.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-04-12 15:57:00
 */
public class StaticProxy implements ProxyInterface {
    /**
     * 需要被代理的类
     */
    private ProxyInterface userDao;
    //分配空间
    {
        userDao=new UserDao();
    }

    /**
     * 总想说些什么
     *
     * @return void
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     */
    public void say() {
        beforeProxy();
        userDao.say();
        afterProxy();
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
        System.out.println("======静态代理前置处理=======");
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
        System.out.println("======静态态代理后置处理=======");
    }
}
