package com.zhangkie.tinyspring;

/**
 * 扩展接口,方便在bean实例化前后添加自定义行为
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean,String beanName) throws Exception;
}
