package com.zhangkie.tinyspring.aop;

import com.zhangkie.tinyspring.aop.cglib.CglibAopProxy;
import com.zhangkie.tinyspring.aop.jdk.JDKAopProxy;


public class ProxyFactory extends AdvisedSupport implements AopProxy{
    /**
     * 在被代理的对象没有要实现的interfaces时,采用cglib
     * 其余情况默认为jdk
     * @return AopProxy
     */
    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    /**
     * 在被代理的对象没有要实现的interfaces时,采用cglib
     * 其余情况默认为jdk
     * @return AopProxy
     */
    protected AopProxy createAopProxy(){
        Class<?>[] interfaces = targetSource.getInterfaces();
        if(interfaces==null||interfaces.length==0)
            return new CglibAopProxy(this);
        else
            return new JDKAopProxy(this);
    }
}
