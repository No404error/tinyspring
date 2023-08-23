package com.zhangkie.tinyspring.beans.io;

import java.net.URL;

/**
 * 定义了一个通过resource信息加载resource的方法
 */
public interface ResourceLoader {

    public Resource getResource(String resourceLocation);

}
