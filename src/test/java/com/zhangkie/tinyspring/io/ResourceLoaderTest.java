package com.zhangkie.tinyspring.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ResourceLoaderTest {
    @Test
    public void testResourceLoad() throws IOException {
        ResourceLoader resourceLoader = new UrlResourceLoader();
        Resource resource = resourceLoader.getResource("tinyioc.xml");
        Assert.assertNotNull(resource.getInputStream());
    }
}
