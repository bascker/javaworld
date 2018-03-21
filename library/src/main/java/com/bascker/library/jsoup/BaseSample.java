package com.bascker.library.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Jsoup 基础使用
 *
 * 1.Jsoup
 *  1.1 parse(): 将输入的 HTML 解析为一个新的文档 Document, 还可以从文件中加载一个 Document
 *  1.2 parseBodyFragment(): 方法创建一个空壳的文档，并插入解析过的 HTML 到 body 元素中
 *  1.3 connect(): 创建一个新的 Connection, 与 get/post() 搭配, 取得该 url 对应的 HTML
 *  1.4 clean(): 消除不受信任的 HTML (防止 XSS 攻击), 需要指定一个可配置的 Whitelist
 *
 * 2.Document
 *  2.1 代表一个 HTML 文档
 *  2.2 body(): 取得文档 body 元素的所有子元素
 *  2.3 getElementsByClass(): 相当于 jQuery 中的 $(class)
 *  2.4 select(): 相当于 css 选择器，select(".bs-link") 等价于 $('.bs-link')
 *  2.5 "abs:" 属性前缀: 取得包含 base URI 的绝对路径，或者 Node.absUrl(String key)
 *
 * 3.Element
 *  3.1 代表 HTML DOM 节点
 *  3.2 text(): 可以用于获取元素文本内容, 也可以写入新内容
 *  3.3 attr(): 获取属性值, 也可写入新属性值
 *
 * @author bascker
 */
public class BaseSample {

    private static final Logger LOG = LoggerFactory.getLogger(BaseSample.class);
    private static final String GITHUB = "https://github.com/bascker/javaworld";
    private static String HTML;

    @BeforeClass
    public static void beforeClass () {
        final StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Base Sample</title></head>")
            .append("<body>Hello Jsoup!")
            .append("<a class='bs-link' name='github'>https://github.com/bascker/javaworld</a>")
            .append("<img src='header.png'/>")
            .append("</body></html>");

        HTML = sb.toString();
    }

    @Test
    public void testParse () {
        final Document document = Jsoup.parse(HTML);
        LOG.info(document.toString());

        final String title = document.title();
        Assert.assertEquals("Base Sample", title);

        final Element body = document.body();
        LOG.info(body.text());

        final Elements links = document.getElementsByClass("bs-link");
        links.stream().forEach(link ->
                LOG.info("{ text: " + link.text() + ", name: " + link.attr("name") + "}"));
    }

    @Test
    public void testParseBodyFragment () {
        final String html = "<div><p>jsoup</p>";
        final Document document = Jsoup.parseBodyFragment(html);
        LOG.info(document.toString());
    }

    @Test
    public void testConnect () throws IOException {
        // 以 get 方式请求 url
        final Document document = Jsoup.connect(GITHUB).get();
        LOG.info(document.title());

        // post 请求
        final Document rs = Jsoup.connect("http://www.baidu.com").data("query", "java")
                .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
                .post();
        LOG.info(rs.title());
    }

    @Test
    public void testSelect () {
        final Document document = Jsoup.parse(HTML);
        final String queryLink = ".bs-link";
        final Elements links = document.select(queryLink);
        Assert.assertEquals("github", links.attr("name"));

        // 扩展名为.png的图片
        final String queryPng = "img[src$=.png]";
        final Elements pngs = document.select(queryPng);
        Assert.assertEquals(1, pngs.size());
    }

    @Test
    public void testAbsUrl () throws IOException {
        final Document document = Jsoup.connect(GITHUB).get();
        final Element link = document.select("a").first();
        // 相对路径
        final String relativeUrl = link.attr("href");
        LOG.info(relativeUrl);
        // 获取绝对路径
        final String absoluteUrl = link.attr("abs:href");
        LOG.info(absoluteUrl);
        Assert.assertEquals(absoluteUrl, link.absUrl("href"));
    }

    @Test
    public void testClean () {
        final String unsafe = "<p><a href='http://example.com/' onclick='stealCookies()'>XSS</a></p>";
        final String safe = Jsoup.clean(unsafe, Whitelist.basic());
        LOG.info("safe: " + safe);
    }

}
