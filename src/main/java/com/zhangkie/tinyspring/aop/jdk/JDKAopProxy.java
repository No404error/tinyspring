package com.zhangkie.tinyspring.aop.jdk;

import com.zhangkie.tinyspring.aop.AdvisedSupport;
import com.zhangkie.tinyspring.aop.AbstractAopProxy;
import com.zhangkie.tinyspring.aop.ReflectiveMethodInvocation;
import com.zhangkie.tinyspring.aop.weaver.MethodFilter;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JDKAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), advisedSupport.getTargetSource().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        MethodFilter methodFilter = advisedSupport.getMethodFilter();
        //代理的方法在expression范围内
        if(methodFilter==null||methodFilter.match(advisedSupport.getTargetSource().getTarget().getClass(),method))
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args));
        else{
            return method.invoke(advisedSupport.getTargetSource().getTarget(),args);
        }
    }
}
