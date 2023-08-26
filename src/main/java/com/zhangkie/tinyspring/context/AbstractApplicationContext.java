package com.zhangkie.tinyspring.context;

import com.zhangkie.tinyspring.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * refresh 是为了刷新容器达到容器可用的目的
     * 子类在被调用前一定要调用该方法
     * 1.读取beanDefinition
     * 2.注册BeanPostProcessor
     * 3.自定义刷新容器
     * @throws Exception
     */
    public final void refresh() throws Exception {
        loadBeanDefinitions();
        beanFactory.registerBeanPostProcessors();
        onRefresh();
    }

    /**
     * 自定义刷新容器的接口
     */
    protected void onRefresh() throws Exception {
    }

    /**
     * 解析所有BeanDefinition
     * 交由子类进行实现
     */
    protected abstract void loadBeanDefinitions() throws Exception;



    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }
}
