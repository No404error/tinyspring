package com.zhangkie.tinyspring.context;

import com.zhangkie.tinyspring.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{
    private AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }
}
