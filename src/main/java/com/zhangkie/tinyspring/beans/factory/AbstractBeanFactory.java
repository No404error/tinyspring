package com.zhangkie.tinyspring.beans.factory;

import com.zhangkie.tinyspring.beans.BeanDefinition;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String, BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();

    private Set<String> beanDefinitionNames=new HashSet<>();


    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition==null)
            throw new Exception("No such bean named "+beanName);
        Object bean = beanDefinition.getBean();
        if(bean==null){
            bean=doCreateBean(beanDefinition);
            //beanDefinition.setBean(bean);
        }
        return bean;
    }

    public void registerBeanDefinition(String name, BeanDefinition definition){
        this.beanDefinitionMap.put(name,definition);
        this.beanDefinitionNames.add(name);
    }

    /**
     * 由于当前的getBean为延迟加载模式,所以需要额外添加一个能够实现提前加载的方法
     */
    public final void preInstantiateSingletons() throws Exception {
        for (String beanName : beanDefinitionNames) {
            getBean(beanName);
        }
    }

    /**
     * AbstractBeanFactory无权创建bean,将创建bean的操作延后至子类
     * @param definition
     * @return bean
     * @throws Exception
     */
    protected abstract Object doCreateBean(BeanDefinition definition) throws Exception;
}
