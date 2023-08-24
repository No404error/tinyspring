package com.zhangkie.tinyspring.beans.context;

import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.factory.AbstractBeanFactory;
import com.zhangkie.tinyspring.beans.factory.AutoWireCapableBeanFactory;
import com.zhangkie.tinyspring.beans.io.UrlResourceLoader;
import com.zhangkie.tinyspring.beans.reader.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext{

    private AbstractBeanFactory beanFactory;

    private String resourceLocation;

    public ClassPathXmlApplicationContext(String resourceLocation) {
        this(resourceLocation,new AutoWireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String resourceLocation, AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.resourceLocation = resourceLocation;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(resourceLocation);
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        for(Map.Entry<String,BeanDefinition> entry:registry.entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }

}
