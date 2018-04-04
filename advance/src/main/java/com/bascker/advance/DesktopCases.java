package com.bascker.advance;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * DeskTop Cases
 *
 * 1.DeskTop
 *  1.1 JDK6 的特性
 *  1.2 允许 Java 程序启动本地默认应用去处理 URI 或 File
 *  1.3 主要方法:
 *      1) browser(uri): 使用本地默认浏览器打开网址
 *      2) edit(File): 使用本地默认程序编辑 File
 *
 * @author bascker
 */
public class DesktopCases {

    private static final Logger LOG = LoggerFactory.getLogger(DesktopCases.class);
    private Desktop mDesktop;

    @Before
    public void before () throws Exception {
        if (Desktop.isDesktopSupported()) {
            mDesktop = Desktop.getDesktop();
        } else {
            mDesktop = null;
            LOG.warn("不支持 DeskTop!");
        }
    }

    @Test
    public void testBrowser () throws IOException {
        final URI uri = URI.create("https://github.com/");
        mDesktop.browse(uri);
    }

}
