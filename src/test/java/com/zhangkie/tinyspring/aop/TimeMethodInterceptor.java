package com.zhangkie.tinyspring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class TimeMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("Method "+method.getName()+" start!");
        long time = System.nanoTime();
        Object result = invocation.proceed();
        long period=System.nanoTime()-time;
        System.out.println("Method "+method.getName()+" end! time is "+period + " nano second!");
        return result;
    }
}
