package com.bascker.library.dom4j;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Element 使用案例
 *
 * 1.Element
 *  1.1 接口, 一个 Element 实例对应 XML 中的一个元素标签
 *
 *
 * @author bascker
 */
public class ElementCase {

    private static final Logger LOG = LoggerFactory.getLogger(ElementCase.class);
    private static final String CONF_NAME = "dom4j.xml";
    private static BufferedReader mBuffReader;
    private static SAXReader mSAXReader;
    private static Document mDocument;
    private static Element mRoot;

    /**
     * elementText(String name): 获取当前元素指定名称的第一个元素的内容
     */
    @Test
    public void testElementText() {
        Assert.assertEquals("java", mRoot.elementText("jdk.name"));
        Assert.assertEquals("1.8", mRoot.elementText("jdk.version"));

        // 当前 root 元素下是找不到 <name> 标签的
        Assert.assertNull(mRoot.elementText("name"));
    }

    /**
     * elements(String name): 返回当前元素下所有匹配给定名称的元素
     */
    @Test
    public void testElements() {
        final Element libs = mRoot.element("libs");
        final List<Element> elements = libs.elements("lib");
        Assert.assertEquals(2, elements.size());

        elements.forEach(element -> LOG.info("lib{name: {}, version: {}}",
                element.elementText("name"),
                element.elementText("version")));
    }

    @BeforeClass
    public static void init() {
        LOG.info("beforeClass start");
        try {
            mBuffReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(ElementCase.class.getResource("/").getPath() + CONF_NAME)));
            mSAXReader = new SAXReader();
            mDocument = mSAXReader.read(mBuffReader);
            mRoot = mDocument.getRootElement();
        } catch (FileNotFoundException | DocumentException e) {
            LOG.error("read {} failed", CONF_NAME, e.getMessage());
        }
        LOG.info("beforeClass end");
    }

    @After
    public void destroy() {
        LOG.info("destroy start");
        IOUtils.closeQuietly(mBuffReader);
        LOG.info("destroy end");
    }

}
