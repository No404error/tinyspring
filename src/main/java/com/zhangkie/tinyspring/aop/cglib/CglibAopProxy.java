package com.zhangkie.tinyspring.aop.cglib;

import com.zhangkie.tinyspring.TargetSource;
import com.zhangkie.tinyspring.aop.AbstractAopProxy;
import com.zhangkie.tinyspring.aop.AdvisedSupport;
import com.zhangkie.tinyspring.aop.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibAopProxy extends AbstractAopProxy {

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        enhancer.setCallback(new CglibDelegateAdvisedInterceptor(advisedSupport));
        return enhancer.create();
    }

    private static class CglibDelegateAdvisedInterceptor implements MethodInterceptor{

        private AdvisedSupport advisedSupport;

        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

        public CglibDelegateAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
            this.delegateMethodInterceptor = advisedSupport.getMethodInterceptor();
        }

        /**
         * 若filter为空或者该方法符合匹配,就通过委托的methodInterceptor进行代理执行、
         * 若不符合则直接调用原方法
         * @param obj "this", the enhanced object
         * @param method intercepted Method
         * @param args argument array; primitive types are wrapped
         * @param proxy used to invoke super (non-intercepted method); may be called
         * as many times as needed
         * @return
         * @throws Throwable
         */
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            TargetSource targetSource = advisedSupport.getTargetSource();
            Class<?> targetClass = targetSource.getTargetClass();
            CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(targetSource.getTarget(), method, args, proxy);

            if(advisedSupport.getMethodFilter()==null||advisedSupport.getMethodFilter().match(targetClass,method)){
                return delegateMethodInterceptor.invoke(cglibMethodInvocation);
            }else
                return cglibMethodInvocation.proceed();
        }
    }

    /**
     * 用methodProxy进行proceed调用
     */
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private MethodProxy proxy;

        public CglibMethodInvocation(Object target, Method method, Object[] args,MethodProxy proxy) {
            super(target, method, args);
            this.proxy=proxy;
        }


        @Override
        public Object proceed() throws Throwable {
            return proxy.invoke(target,args);
        }
    }

}
