# 代理模式的三种实现方式

***

###### 代理即替代+管理，也就是说在处理一个类的时候，我们可以控制访问（前置处理）和 善后操作（后置操作），换而言之，便是在原有的功能基础上处理一些其他想做的事情，比如纪录日志。

###### 下面分别使用三种方式对以下代码进行代理


**代理类和被代理类实现的接口 ProxyInterface.java**

```markdown
    
    package com.dao.proxy.common;
    
    /**
     * 代理的接口
     *
     * @author 阿导
     * @version 1.0
     * @fileName com.dao.proxy.common.ProxyInterface.java
     * @CopyRright (c) 2018-万物皆导
     * @created 2018-04-12 15:55:00
     */
    public interface ProxyInterface {
        /**
         * 总想说些什么
         *
         * @author 阿导
         * @time 2018/4/12
         * @CopyRight 万物皆导
         * @param
         * @return void
         */
        void say();
    }


```

**被代理的类 UserDao.java**

```markdown
    
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



```



> 静态代理

- 特点：代理类和被代理类需要实现同一个接口，当代理对象增多时，其代理类随之增加，而且一旦接口更改，其代理类和被代理类都需要同时做出调整，管理及其不便。

- 示例代码

**代理类 ProxyDao.java**

```markdown

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
            System.out.println("======静态代理后置处理=======");
        }
    }




```

**测试类 StaticProxyTest.java**

```markdown

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


```

**测试结果**

```markdown
    
    ======静态代理前置处理=======
    ======我是被代理的类=======
    ======静态代理后置处理=======
    
    
```

> 动态代理

- 特点：代理类不需要实现被代理类的接口，通过 java 的 api 动态生成代理类 Proxy.newProxyInstance（。。。），其中有三个参数：

> - ClassLoader loader：目标对象的类加载器

> - Class<?>[] interfaces：目标对象的接口类型

> - InvocationHandler h：事件处理函数，实现对目标对象的操作

- 示例代码

**动态代理工厂类 DynamicProxyFactory.java**

```markdown
    
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



```

**测试类 DynamicProxyTest.java**

```markdown
    
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



```

**测试结果**

```markdown
    
    ======动态代理前置处理=======
    ======我是被代理的类=======
    ======动态代理后置处理=======
    原生对象：class com.dao.proxy.common.UserDao
    代理对象：class com.sun.proxy.$Proxy2
    
    
```

> CGLIB 代理，也就是子类代理，在内存中构建一个子类对象从而实现对被代理类的功能的扩展

> - jdk 的动态代理有一个限制,就是使用动态代理的对象必须实现一个或多个接口,如果想代理没有实现接口的类,就可以使用 cglib 实现.
  
> - cglib 是一个强大的高性能的代码生成包,它可以在运行期扩展 java 类与实现 java 接口.它广泛的被许多 AOP 的框架使用,例如 spring AOP 和 synaop,为他们提供方法的 interception (拦截)

> - cglib 包的底层是通过使用一个小而块的字节码处理框架 ASM 来转换字节码并生成新的类.不鼓励直接使用 ASM,因为它要求你必须对 JVM 内部结构包括 class 文件的格式和指令集都很熟悉.

- 示例代码


**CGLIB 代理类 CglibProxy.java**

```markdown
    
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


```

**测试类**

```markdown
    
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


```

**测试结果**

```markdown

    ======CGLIB 代理前置处理=======
    ======我是被代理的类=======
    ======CGLIB 代理后置处理=======
    原生对象：class com.dao.proxy.common.UserDao
    代理对象：class com.dao.proxy.common.UserDao$$EnhancerByCGLIB$$623ca353
    
    
```
