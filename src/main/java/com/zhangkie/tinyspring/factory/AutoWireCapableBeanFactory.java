package com.zhangkie.tinyspring.factory;

import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.BeanReference;
import com.zhangkie.tinyspring.beans.PropertyValue;
import com.zhangkie.tinyspring.converter.StringToPrimitiveConverter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AutoWireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 在为bean添加属性时,若类型为beanRef,则创建引用类型
     * 若类型为beanFactoryAware时,为其添加beanFactory引用
     * 其他类型则正常实例化
     * 先尝试setter注入
     * 再进行属性赋值
     * @param bean
     * @param definition
     * @throws Exception
     */
    protected void applyPropertyValues(Object bean, BeanDefinition definition) throws Exception {
        if(bean instanceof BeanFactoryAware)
            ((BeanFactoryAware) bean).setBeanFactory(this);

        for(PropertyValue propertyValue:definition.getPropertyValues().getPropertyValues()){
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value=getBean(beanReference.getName());
            }

            try{
                setPropertyBySetter(propertyValue.getName(), bean,bean.getClass(),value,value.getClass());
            }catch (Exception e){
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean,value instanceof String?StringToPrimitiveConverter.convert((String) value,declaredField.getType()):value);
            }
        }
    }

    /**
     * 通过bean setter来进行属性的赋值
     * @param propertyName
     * @param bean
     * @param beanClass
     * @param value
     * @param valueClass
     * @throws Exception
     */
    private void setPropertyBySetter(String propertyName,Object bean,Class beanClass,Object value,Class valueClass) throws Exception  {
        StringBuffer sb=new StringBuffer("set");
        sb.append(propertyName.substring(0,1).toUpperCase());
        sb.append(propertyName.substring(1));
        Method declaredMethod = beanClass.getDeclaredMethod(sb.toString(), valueClass);
        declaredMethod.invoke(bean,value);
    }

}
