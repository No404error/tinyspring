package com.zhangkie.tinyspring.context;

import com.zhangkie.tinyspring.User;
import com.zhangkie.tinyspring.WorkSpace;
import com.zhangkie.tinyspring.aop.AliSendService;
import com.zhangkie.tinyspring.aop.SendService;
import org.junit.Test;

public class ClassPathXmlApplicationContextTest {
    @Test
    public void testContext() throws Exception {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("tinyioc.xml");
        User user = null;
        user = (User) context.getBean("user");
        System.out.println(user);
        WorkSpace workSpace = null;
        workSpace = (WorkSpace) context.getBean("workSpace");
        System.out.println(workSpace);
    }

    @Test
    public void testContextWithBeanPostProcessor() throws Exception {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("tinyioc_process.xml");
        User user = null;
        user = (User) context.getBean("user");
        System.out.println(user);
        WorkSpace workSpace = null;
        workSpace = (WorkSpace) context.getBean("workSpace");
        System.out.println(workSpace);
    }

    @Test
    public void testContextWithAutoProxy() throws Exception {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("tinyioc_autoproxy.xml");
        SendService sendService = (SendService) context.getBean("sendService");
        sendService.send();
    }

    @Test
    public void testContextWithAutoProxyFactory() throws Exception {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("tinyioc_autoproxy_proxyfactory.xml");
        SendService sendService = (SendService) context.getBean("sendService");
        sendService.send();
        AliSendService aliSendService = (AliSendService) context.getBean("aliSendService");
        aliSendService.send();
    }
}
