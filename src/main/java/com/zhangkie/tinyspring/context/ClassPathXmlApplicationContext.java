package com.zhangkie.tinyspring.context;

import com.zhangkie.tinyspring.BeanDefinition;
import com.zhangkie.tinyspring.factory.AbstractBeanFactory;
import com.zhangkie.tinyspring.factory.AutoWireCapableBeanFactory;
import com.zhangkie.tinyspring.io.UrlResourceLoader;
import com.zhangkie.tinyspring.reader.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    private String resourceLocation;

    public ClassPathXmlApplicationContext(String resourceLocation) throws Exception {
        this(resourceLocation,new AutoWireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String resourceLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.resourceLocation=resourceLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(resourceLocation);
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        for(Map.Entry<String,BeanDefinition> entry:registry.entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }
    }
}
