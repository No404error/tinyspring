package com.zhangkie.tinyspring.factory;

import com.zhangkie.tinyspring.beans.Aware;

/**
 * 能够感知beanFactory
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
