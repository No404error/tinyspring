package com.zhangkie.tinyspring.beans.factory;

import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.PropertyValue;
import com.zhangkie.tinyspring.beans.converter.StringToPrimitiveConverter;

import java.lang.reflect.Field;

public class AutoWireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object doCreateBean(BeanDefinition definition) throws Exception {
        Object bean=createBeanInstance(definition);
        applyPropertyValues(bean,definition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition definition) throws InstantiationException, IllegalAccessException {
        return definition.getBeanClass().newInstance();
    }

    protected void applyPropertyValues(Object bean, BeanDefinition definition) throws NoSuchFieldException, IllegalAccessException {
        for(PropertyValue propertyValue:definition.getPropertyValues().getPropertyValues()){
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object convertObject = StringToPrimitiveConverter.convert((String)propertyValue.getValue(), declaredField.getType());
            declaredField.set(bean,convertObject);
        }
    }

}
