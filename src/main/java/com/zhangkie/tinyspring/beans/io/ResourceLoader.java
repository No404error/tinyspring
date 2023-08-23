package com.zhangkie.tinyspring.beans.io;

import java.net.URL;

/**
 * 用于通过location信息生成为一个可以生成inputStream的资源类
 */
public class ResourceLoader {

    public Resource getResource(String location){
        URL resourceUrl = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resourceUrl);
    }

}
