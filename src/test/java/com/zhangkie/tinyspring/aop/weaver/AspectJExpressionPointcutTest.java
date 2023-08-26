package com.zhangkie.tinyspring.aop.weaver;

import com.zhangkie.tinyspring.aop.SendServiceImpl;
import com.zhangkie.tinyspring.aop.TimeMethodInterceptor;
import org.junit.Assert;
import org.junit.Test;

public class AspectJExpressionPointcutTest {
    @Test
    public void testMatchClass(){
        String expression="execution(* com.zhangkie.tinyspring.aop.TimeMethodInterceptor.*(..))";

        AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        boolean match = pointcut.getClassFilter().match(TimeMethodInterceptor.class);
        Assert.assertTrue(match);
    }
}
