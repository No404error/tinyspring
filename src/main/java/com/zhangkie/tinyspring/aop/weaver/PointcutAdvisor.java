package com.zhangkie.tinyspring.aop.weaver;

/**
 * 管理切点的通知管理者
 */
public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
