package com.zhangkie.tinyspring.aop.jdk;

import com.zhangkie.tinyspring.User;
import com.zhangkie.tinyspring.aop.SendService;
import com.zhangkie.tinyspring.aop.TimeMethodInterceptor;
import com.zhangkie.tinyspring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class JDKAopProxyTest {
    @Test
    public void testProxy() throws Exception {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("tinyioc.xml");
        SendService sendService = (SendService) context.getBean("sendService");


        TargetSource targetSource = new TargetSource(sendService, SendService.class);
        AdvisedSupport advisedSupport = new AdvisedSupport(targetSource,new TimeMethodInterceptor());

        JDKAopProxy jdkAopProxy = new JDKAopProxy(advisedSupport);
        SendService sendServiceProxy = (SendService) jdkAopProxy.getProxy();

        sendServiceProxy.send();
    }
}
