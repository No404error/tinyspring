package com.zhangkie.tinyspring.factory;

import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.BeanPostProcessor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String, BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();

    private Set<String> beanDefinitionNames=new HashSet<>();

    private List<BeanPostProcessor> beanPostProcessors=new ArrayList<>();


    @Override
    public final Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition==null)
            throw new Exception("No such bean named "+beanName);
        Object bean = beanDefinition.getBean();
        if(bean==null){
            bean=doCreateBean(beanDefinition);
            bean=initializeBean(bean,beanName);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    /**
     * 在factory中得到所有是该类型子类的对象
     * 兼有提前初始化一类对象的意味
     * @param type
     * @return
     */
    public final List getBeansForType(Class type) throws Exception {
        List<Object> beans=new ArrayList<>();
        for(String definitionName:beanDefinitionNames){
            if(type.isAssignableFrom(beanDefinitionMap.get(definitionName).getBeanClass()))
                beans.add(getBean(definitionName));
        }
        return beans;
    }

    /**
     * 将beanPostProcessor提前注册
     * @throws Exception
     */
    public final void registerBeanPostProcessors() throws Exception {
        List<Object> beanPostProcessors = getBeansForType(BeanPostProcessor.class);
        for (Object processor:beanPostProcessors){
            addBeanPostProcessor((BeanPostProcessor) processor);
        }
    }

    /**
     * 向beanFactory添加BeanPostProcessor
     * @param beanPostProcessor
     */
    public final void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 初始化一个bean,用到了BeanPostProcessor接口
     * @param bean
     * @param beanName
     */
    private Object initializeBean(Object bean, String beanName) throws Exception {
        for(BeanPostProcessor processor:beanPostProcessors){
            bean=processor.postProcessBeforeInitialization(bean,beanName);
        }

        //initialize

        for(BeanPostProcessor processor:beanPostProcessors){
            bean=processor.postProcessAfterInitialization(bean,beanName);
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
     * 在创建bean时先实例化存入缓存,再为其完善,可避免循环依赖问题
     * 即采用一级缓存的方式避免的循环依赖
     * @param definition
     * @return
     * @throws Exception
     */
    private Object doCreateBean(BeanDefinition definition) throws Exception {
        Object bean=createBeanInstance(definition);
        definition.setBean(bean);
        applyPropertyValues(bean,definition);
        return bean;
    }

    /**
     * bean的属性赋值交给子类完成
     * @param bean
     * @param definition
     * @throws Exception
     */
    protected void applyPropertyValues(Object bean, BeanDefinition definition) throws Exception{

    }

    /**
     * 创建bean实例
     * @param definition
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected Object createBeanInstance(BeanDefinition definition) throws InstantiationException, IllegalAccessException {
        return definition.getBeanClass().newInstance();
    }
}
