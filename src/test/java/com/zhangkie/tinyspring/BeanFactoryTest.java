package com.zhangkie.tinyspring;


import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.PropertyValue;
import com.zhangkie.tinyspring.beans.PropertyValues;
import com.zhangkie.tinyspring.beans.factory.AutoWireCapableBeanFactory;
import com.zhangkie.tinyspring.beans.factory.BeanFactory;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void testGetBean() throws Exception {
        BeanFactory beanFactory=new AutoWireCapableBeanFactory();

        BeanDefinition userDefinition = new BeanDefinition();

        userDefinition.setBeanClassName("com.zhangkie.tinyspring.User");

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addProperty(new PropertyValue("name","cxk"));

        userDefinition.setPropertyValues(propertyValues);

        beanFactory.registerBeanDefinition("user", userDefinition);

        User user = (User)beanFactory.getBean("user");

        System.out.println(user);
    }
}
