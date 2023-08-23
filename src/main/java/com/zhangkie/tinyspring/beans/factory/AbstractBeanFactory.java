package com.zhangkie.tinyspring.beans.factory;

import com.zhangkie.tinyspring.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String, BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();


    @Override
    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition definition) throws Exception {
        Object bean = doCreateBean(definition);
        definition.setBean(bean);
        this.beanDefinitionMap.put(name,definition);
    }

    /**
     * AbstractBeanFactory无权创建bean,将创建bean的操作延后至子类
     * @param definition
     * @return bean
     * @throws Exception
     */
    protected abstract Object doCreateBean(BeanDefinition definition) throws Exception;
}
