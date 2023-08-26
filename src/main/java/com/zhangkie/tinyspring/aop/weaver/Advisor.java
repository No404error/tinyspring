package com.zhangkie.tinyspring.aop.weaver;

import org.aopalliance.aop.Advice;

/**
 * 通知的管理者
 */
public interface Advisor {
    Advice getAdvice();
}
