package com.zhangkie.tinyspring.aop.weaver;

import org.aopalliance.aop.Advice;

/**
 * 保存了aspectJ expression pointcut的通知管理者
 * 能返回advice 及 aspectJ expression pointcut
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

	@Override
	public Advice getAdvice() {
		return advice;
	}

    @Override
	public Pointcut getPointcut() {
		return pointcut;
	}
}
