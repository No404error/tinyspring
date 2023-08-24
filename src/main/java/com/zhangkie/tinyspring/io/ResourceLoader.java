package com.zhangkie.tinyspring.io;

/**
 * 定义了一个通过resource信息加载resource的方法
 */
public interface ResourceLoader {

    public Resource getResource(String resourceLocation);

}
