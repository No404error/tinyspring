package com.zhangkie.tinyspring.aop.weaver;

import java.lang.reflect.Method;

/**
 * 用于识别某个方法是否符合条件
 */
public interface MethodFilter {
    boolean match(Class targetClass, Method method);
}
