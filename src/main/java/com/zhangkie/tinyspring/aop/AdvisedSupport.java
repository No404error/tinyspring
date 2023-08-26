package com.zhangkie.tinyspring.aop;

import com.zhangkie.tinyspring.TargetSource;
import com.zhangkie.tinyspring.aop.weaver.MethodFilter;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 用于协助代理完成invoke调用
 * 作用1,提供代理必备参数:target,targetClass,interfaces,methodInterceptor
 * 作用2,能够配合aspectJ 来确定某个方法是否需要被代理(当filter为null时所有方法默认代理)
 */
public class AdvisedSupport {

    protected TargetSource targetSource;

    protected MethodInterceptor methodInterceptor;

    protected MethodFilter methodFilter;

    public AdvisedSupport() {
    }

    /**
     * 默认所有方法都代理的filter
     * @param targetSource
     * @param methodInterceptor
     */
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
