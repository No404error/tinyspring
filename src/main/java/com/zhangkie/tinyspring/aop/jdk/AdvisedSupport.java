package com.zhangkie.tinyspring.aop.jdk;

import com.zhangkie.tinyspring.aop.weaver.MethodFilter;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 用于协助代理完成invoke调用
 * 作用1,提供代理必备参数:target,targetClass,methodInterceptor
 * 作用2,能够配合aspectJ expression来确定某个方法是否需要被代理(当filter为null时所有方法不代理)
 */
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodFilter methodFilter;

    public AdvisedSupport(TargetSource targetSource, MethodInterceptor methodInterceptor) {
        this(targetSource,methodInterceptor,null);
    }

    public AdvisedSupport(TargetSource targetSource, MethodInterceptor methodInterceptor, MethodFilter methodFilter) {
        this.targetSource = targetSource;
        this.methodInterceptor = methodInterceptor;
        this.methodFilter = methodFilter;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodFilter getMethodFilter() {
        return methodFilter;
    }

    public void setMethodFilter(MethodFilter methodFilter) {
        this.methodFilter = methodFilter;
    }
}
