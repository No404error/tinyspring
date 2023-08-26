package com.zhangkie.tinyspring.aop.weaver;

/**
 * 用于识别该类是否符合条件
 */
public interface ClassFilter {
    boolean match(Class targetClass);
}
