package com.zhangkie.tinyspring.context;

import com.zhangkie.tinyspring.User;
import com.zhangkie.tinyspring.beans.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ClassPathXmlApplicationContextTest {
    @Test
    public void testContext(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("tinyioc.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
