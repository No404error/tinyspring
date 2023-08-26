package com.zhangkie.tinyspring.aop.weaver;

import com.zhangkie.tinyspring.aop.AdvisedSupport;
import com.zhangkie.tinyspring.aop.ProxyFactory;
import com.zhangkie.tinyspring.aop.jdk.JDKAopProxy;
import com.zhangkie.tinyspring.TargetSource;
import com.zhangkie.tinyspring.beans.BeanPostProcessor;
import com.zhangkie.tinyspring.factory.AbstractBeanFactory;
import com.zhangkie.tinyspring.factory.BeanFactory;
import com.zhangkie.tinyspring.factory.BeanFactoryAware;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * 是一个自动服务
 * 用于将所有在beanFactory中被aspectJExpression通知绑定的类自动完成代理
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanFactoryAware, BeanPostProcessor {
    private AbstractBeanFactory beanFactory;

    private List<AspectJExpressionPointcutAdvisor> advisors;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    /**
     * 将所有符合bean类型的advisor全部代理
     * 采用jdk方式进行代理
     * 函数调用代理
     * 故通知本身以及其中的advice操作都不需要被处理
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if(bean instanceof AspectJExpressionPointcutAdvisor)
            return bean;
        if(bean instanceof MethodInterceptor)
            return bean;

        for(AspectJExpressionPointcutAdvisor advisor:advisors){
            if(advisor.getPointcut().getClassFilter().match(bean.getClass())){
                TargetSource targetSource = new TargetSource(bean,bean.getClass(),bean.getClass().getInterfaces());
                ProxyFactory proxyFactory=new ProxyFactory();
                proxyFactory.setTargetSource(targetSource);
                proxyFactory.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                proxyFactory.setMethodFilter(advisor.getPointcut().getMethodFilter());
                bean=proxyFactory.getProxy();
            }
        }
        return bean;
    }

    /**
     * 在设置beanFactory后
     * 一并初始化所有advisors
     * @param beanFactory
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory=(AbstractBeanFactory) beanFactory;
        try {
            this.advisors = (List<AspectJExpressionPointcutAdvisor>) this.beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
