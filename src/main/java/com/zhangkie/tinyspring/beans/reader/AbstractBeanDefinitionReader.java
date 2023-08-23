package com.zhangkie.tinyspring.beans.reader;

import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanReader能够保证每个reader都确实需要一个registry来存储读完资源后将definition放入其中，以及一个ResourceLoader
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.registry=new HashMap<>();
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    protected ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
