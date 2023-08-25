package com.zhangkie.tinyspring.factory;

import com.zhangkie.tinyspring.BeanDefinition;
import com.zhangkie.tinyspring.BeanReference;
import com.zhangkie.tinyspring.PropertyValue;
import com.zhangkie.tinyspring.converter.StringToPrimitiveConverter;

import java.lang.reflect.Field;

public class AutoWireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 在为bean添加属性时,若类型为beanRef,则创建引用类型
     * 其他类型则正常实例化
     * @param bean
     * @param definition
     * @throws Exception
     */
    protected void applyPropertyValues(Object bean, BeanDefinition definition) throws Exception {
        for(PropertyValue propertyValue:definition.getPropertyValues().getPropertyValues()){
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);

            Object convertObject;
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                convertObject=getBean(beanReference.getName());
            }else{
                convertObject=StringToPrimitiveConverter.convert((String) value, declaredField.getType());
            }
            declaredField.set(bean,convertObject);
        }
    }

}
