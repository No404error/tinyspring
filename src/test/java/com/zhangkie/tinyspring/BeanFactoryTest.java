package com.zhangkie.tinyspring;


import com.zhangkie.tinyspring.factory.AbstractBeanFactory;
import com.zhangkie.tinyspring.factory.AutoWireCapableBeanFactory;
import com.zhangkie.tinyspring.io.UrlResourceLoader;
import com.zhangkie.tinyspring.reader.AbstractBeanDefinitionReader;
import com.zhangkie.tinyspring.reader.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

public class BeanFactoryTest {

    @Test
    public void testGetBean() throws Exception {
        AbstractBeanFactory beanFactory=new AutoWireCapableBeanFactory();

        BeanDefinition userDefinition = new BeanDefinition();

        userDefinition.setBeanClassName("com.zhangkie.tinyspring.User");

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addProperty(new PropertyValue("name","cxk"));
        propertyValues.addProperty(new PropertyValue("id",666666L));

        userDefinition.setPropertyValues(propertyValues);

        beanFactory.registerBeanDefinition("user", userDefinition);

        User user = (User)beanFactory.getBean("user");

        System.out.println(user);
    }

    @Test
    public void testXmlBeanDefinitionReaderAndGetBean() throws Exception {
        AbstractBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(new UrlResourceLoader());
        beanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        Map<String, BeanDefinition> registry = beanDefinitionReader.getRegistry();

        AbstractBeanFactory beanFactory=new AutoWireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        User user = (User)beanFactory.getBean("user");
        System.out.println(user);
    }

    @Test
    public void testPreInitiateSingletons() throws Exception {
        AbstractBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(new UrlResourceLoader());
        beanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        Map<String, BeanDefinition> registry = beanDefinitionReader.getRegistry();

        AbstractBeanFactory beanFactory=new AutoWireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        beanFactory.preInstantiateSingletons();

        Object workSpace = beanFactory.getBean("workSpace");
        System.out.println(workSpace);
    }
}
