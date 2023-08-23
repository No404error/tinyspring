package com.zhangkie.tinyspring.beans;

import java.util.ArrayList;
import java.util.List;
/**
 * 用于描述bean具有全部属性的类
 */
public class PropertyValues {

    private List<PropertyValue> propertyValues;

    public PropertyValues() {
        this.propertyValues = new ArrayList<>();
    }

    public void addProperty(PropertyValue propertyValue){
        this.propertyValues.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues(){
        return this.propertyValues;
    }

}
