package com.zhangkie.tinyspring.aop.jdk;

import com.zhangkie.tinyspring.aop.AopProxy;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advisedSupport;

    public JDKAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{advisedSupport.getTargetSource().getTargetClass()},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        return methodInterceptor.invoke(new ReflectiveMethodInterceptor(advisedSupport.getTargetSource().getTarget(),method,args));
    }
}
