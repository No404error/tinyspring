package com.zhangkie.tinyspring.context;

import com.zhangkie.tinyspring.User;
import com.zhangkie.tinyspring.WorkSpace;
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
}
