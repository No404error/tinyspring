package com.zhangkie.tinyspring.reader;

import com.zhangkie.tinyspring.User;
import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.factory.AutoWireCapableBeanFactory;
import com.zhangkie.tinyspring.beans.factory.BeanFactory;
import com.zhangkie.tinyspring.beans.io.UrlResourceLoader;
import com.zhangkie.tinyspring.beans.reader.AbstractBeanDefinitionReader;
import com.zhangkie.tinyspring.beans.reader.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class XmlBeanDefinitionReaderTest {
    @Test
    public void testXmlBeanDefinitionReader() throws Exception {
        AbstractBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(new UrlResourceLoader());
        beanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        Assert.assertTrue(beanDefinitionReader.getRegistry().size()>0);
    }
}
