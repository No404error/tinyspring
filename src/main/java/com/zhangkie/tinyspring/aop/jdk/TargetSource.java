package com.zhangkie.tinyspring.aop.jdk;

public class TargetSource {
    private Object target;

    private Class[] targetClasses;

    public TargetSource(Object target, Class targetClass) {
        this.target = target;
        this.targetClasses = new Class[]{targetClass};
    }

    public TargetSource(Object target, Class[] targetClasses) {
        this.target = target;
        this.targetClasses = targetClasses;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class[] getTargetClasses() {
        return targetClasses;
    }

    public void setTargetClasses(Class[] targetClasses) {
        this.targetClasses = targetClasses;
    }
}
