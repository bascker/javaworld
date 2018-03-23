package com.bascker.library.guava;

import com.bascker.bsutil.FileUtils;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Files Case
 *
 * 1.Files
 *  1.1 Guava 提供操作文件的工具类
 *  1.2 常用方法
 *      1) readLines(): 逐行读取文件
 *      2) readBytes(): 可对文件的字节做处理
 *      3) readFirstLine(): 返回第一行的文本
 *      4) write()
 *      5) copy()
 *      6) move()
 *      7) equal(): 比较文件内容是否完全一致
 *
 * @author bascker
 */
public class FilesCases {

    private static final Logger LOG = LoggerFactory.getLogger(FilesCases.class);

    @Test
    public void testRead () throws URISyntaxException, IOException {
        final Path ehcache = Paths.get(FileUtils.getFileInResources("ehcache.xml").toURI());
        final List<String> content = Files.readLines(ehcache.toFile(), Charsets.UTF_8);
        content.forEach(line -> LOG.info(line));
    }

}
