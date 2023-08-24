package com.zhangkie.tinyspring;


import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.PropertyValue;
import com.zhangkie.tinyspring.beans.PropertyValues;
import com.zhangkie.tinyspring.beans.factory.AbstractBeanFactory;
import com.zhangkie.tinyspring.beans.factory.AutoWireCapableBeanFactory;
import com.zhangkie.tinyspring.beans.factory.BeanFactory;
import com.zhangkie.tinyspring.beans.io.UrlResourceLoader;
import com.zhangkie.tinyspring.beans.reader.AbstractBeanDefinitionReader;
import com.zhangkie.tinyspring.beans.reader.XmlBeanDefinitionReader;
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
}