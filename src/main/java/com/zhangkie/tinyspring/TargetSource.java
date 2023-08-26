package com.zhangkie.tinyspring;

/**
 * 目标对象的属性资源
 * target对象本身
 * target的class类型
 * target需要实现的interfaces
 * 既满足了jdk代理(interfaces)又满足cglib代理(targetClass)
 */
public class TargetSource {
    private Object target;

    private Class<?> targetClass;

    private Class<?>[] interfaces;

    public TargetSource(Object target, Class<?> targetClass,Class<?>... interfaces) {
        this.target = target;
        this.targetClass=targetClass;
        this.interfaces = interfaces;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class<?>[] interfaces) {
        this.interfaces = interfaces;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }
}
