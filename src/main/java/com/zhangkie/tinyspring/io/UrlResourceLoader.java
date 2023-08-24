package com.zhangkie.tinyspring.io;

import java.net.URL;

public class UrlResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String resourceLocation) {
        URL resourceUrl = this.getClass().getClassLoader().getResource(resourceLocation);
        return new UrlResource(resourceUrl);
    }
}
