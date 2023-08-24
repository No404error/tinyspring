package com.zhangkie.tinyspring.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * resource提供输入流及其数据
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
