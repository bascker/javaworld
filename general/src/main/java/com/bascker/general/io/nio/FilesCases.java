package com.bascker.general.io.nio;

import com.bascker.bsutil.CharsetUtils;
import com.bascker.bsutil.CollectionHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Files Case
 *
 * 1.Files
 *  1.1 NIO 提供文件操作的工具
 *  1.2 常用操作
 *      1) createTempFile(): 在系统默认临时目录位置创建临时文件
 *      2) write(): 按行写，会在行末加上换行符
 *      3) move(): 文件移动/重命名
 *      4) copy(): 复制
 *
 * 2.FileVisitor
 *  2.1 用于目录的遍历
 *
 * 3.文件系统监控 API
 *  3.1 WatchService
 *  3.2 WatchKey
 *
 * @author bascker
 */
public class FilesCases {

    private static final Logger LOG = LoggerFactory.getLogger(FilesCases.class);
    private static final String TMP_FILE_PREFIX = "nio_";
    private static final String TMP_FILE_SUFIX = ".txt";
    private Path tmpPath;

    @Before
    public void before () throws IOException {
        tmpPath = Files.createTempFile(TMP_FILE_PREFIX, TMP_FILE_SUFIX);
    }

    /**
     * 扫尾操作
     * 1.使用 shutdown 钩子删除临时目录
     * 2.shutdownHook 主要用于系统停止运行时进行资源清理和保存
     */
    @After
    public void after () {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                LOG.info("临时文件内容: " + CollectionHelper.toString(Files.readAllLines(tmpPath, CharsetUtils.UTF8)));
                Files.deleteIfExists(tmpPath);
                LOG.info("清除临时文件: " + tmpPath.getFileName().toString());
            } catch (IOException e) {
                LOG.error("after", e);
            }
        }));
    }

    @Test
    public void testWrite () throws IOException {
        final List<String> content = Arrays.asList("111", "222", "333");
        Files.write(tmpPath, content, CharsetUtils.UTF8, StandardOpenOption.APPEND);

        // 使用缓冲流
        try (BufferedWriter writer = Files.newBufferedWriter(tmpPath, StandardOpenOption.APPEND)) {
            writer.write("From BufferStream");
        }
    }

    @Test
    public void testCopy () throws IOException {
        final Path p = Files.createTempFile(TMP_FILE_PREFIX, TMP_FILE_SUFIX);
        Files.copy(tmpPath, p, StandardCopyOption.REPLACE_EXISTING);
        LOG.info("Copy to: " + p.getFileName().toString());
    }

}
