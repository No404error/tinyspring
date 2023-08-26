package com.zhangkie.tinyspring.context;

import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.factory.AbstractBeanFactory;
import com.zhangkie.tinyspring.factory.AutoWireCapableBeanFactory;
import com.zhangkie.tinyspring.io.UrlResourceLoader;
import com.zhangkie.tinyspring.reader.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 定义了一个用xml初始容器的,且会提前初始化所有bean的context
 */
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

    /**
     * 容器预先初始化所有bean
     */
    @Override
    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 用xml读取bean
     * @throws Exception
     */
    @Override
    protected void loadBeanDefinitions() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(resourceLocation);
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        for(Map.Entry<String,BeanDefinition> entry:registry.entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }
    }
}
