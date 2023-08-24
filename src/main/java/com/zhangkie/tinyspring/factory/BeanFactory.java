package com.zhangkie.tinyspring.factory;

public interface BeanFactory {
    Object getBean(String beanName) throws Exception;

}
