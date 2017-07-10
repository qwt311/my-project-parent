package com.project.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by qiaowentao on 2017/5/8.
 */
public class TestDynamic implements InvocationHandler{

    private Object target;

    public TestDynamic(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理对象");
        Object result = method.invoke(target,args);

        return result;
    }

}
