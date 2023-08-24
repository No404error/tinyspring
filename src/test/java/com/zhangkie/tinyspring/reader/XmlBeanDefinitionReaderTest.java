package com.zhangkie.tinyspring.reader;

import com.zhangkie.tinyspring.io.UrlResourceLoader;
import org.junit.Assert;
import org.junit.Test;

public class XmlBeanDefinitionReaderTest {
    @Test
    public void testXmlBeanDefinitionReader() throws Exception {
        AbstractBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(new UrlResourceLoader());
        beanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        Assert.assertTrue(beanDefinitionReader.getRegistry().size()>0);
    }
}
