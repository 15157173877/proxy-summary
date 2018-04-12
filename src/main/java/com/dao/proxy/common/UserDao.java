package com.dao.proxy.common;

/**
 * 被代理的类
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.proxy.common.UserDao.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-04-12 15:56:00
 */
public class UserDao implements ProxyInterface {

    /**
     * 总想说些什么
     *
     * @return void
     * @author 阿导
     * @time 2018/4/12
     * @CopyRight 万物皆导
     */
    public void say() {
        System.out.println("======我是被代理的类=======");
    }
}
