package com.zhangkie.tinyspring.beans.factory;

public interface BeanFactory {
    Object getBean(String beanName) throws Exception;

}
