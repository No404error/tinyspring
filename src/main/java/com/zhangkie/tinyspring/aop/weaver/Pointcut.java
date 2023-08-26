package com.zhangkie.tinyspring.aop.weaver;

/**
 * 切入点
 * 用于判断哪些类和方法能被匹配
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodFilter getMethodFilter();
}
