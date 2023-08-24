package com.zhangkie.tinyspring.reader;

/**
 * 定义了对bean definition resource进行读取的方法
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
