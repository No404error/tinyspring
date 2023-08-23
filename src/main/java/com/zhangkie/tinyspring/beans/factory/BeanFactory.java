package com.zhangkie.tinyspring.beans.factory;

import com.zhangkie.tinyspring.beans.BeanDefinition;

public interface BeanFactory {
    Object getBean(String beanName);

}
