package com.bascker.general.io.nio;

import com.bascker.bsutil.CharsetUtils;
import com.bascker.bsutil.CollectionUtils;
import com.bascker.bsutil.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Path Sample
 *
 * 1.Path
 *  1.1 NIO.2 中最常用的类, 是 NIO.2 的里程碑
 *  1.2 一般搭配 {@link Paths}, {@link Files} 使用
 *  1.3 常用方法:
 *      1) resolve():       合并 Path 对象
 *      2) relativize():    获取 a 切换到 b 的相对路径
 *
 * 2.创建 Path 对象
 *  2.1 Paths.get(URI)
 *  2.2 Paths.get(filename)
 *  2.3 Paths.get(first, more)
 *      1) Paths.get("F:\\test", "a.txt"); 等价于 Path.get("F:\\test\a.txt)
 *      2) Paths.get("F:\\", "test", "a.txt");
 *
 * 3.文件视图: 6 种
 *  3.1 BasicFileAttributes            基础视图
 *  3.2 DosFileAttributes              支持 DOS 文件系统
 *  3.3 FileOwnerAttributeView         支持设置和读取文件所有者
 *  3.4 PosixFileAttributes            扩展 Basic 视图并支持 Unix 及相关系统
 *  3.5 AclFileAttributeView           精确控制访问文件的权限列表
 *  3.6 UserDefinedFileAttributeView   用户自定义视图
 *
 * 4.DirectoryStream
 *  4.1 用于目录操作, 如遍历
 *  4.2 可以进行文件过滤: 区块匹配，利用正则式匹配内容
 *  4.3 自定义文件过滤器: 需通过实现  DirectoryStream.Filter<T> 接口，并实现它的 accept() 方法
 *
 * @author bascker
 */
public class PathSample {

    private static final Logger LOG = LoggerFactory.getLogger(PathSample.class);
    private Path data;
    private Path img;

    @Before
    public void before () throws URISyntaxException {
        final URI uri = FileUtils.getFileInResources("data").toURI();
        data = Paths.get(uri);
        img = Paths.get(FileUtils.getFileInResources("img").toURI());
    }

    @Test
    public void testCreate () throws IOException, URISyntaxException {
        // 获取 resources 目录下文件
        LOG.info(CollectionUtils.toString(Files.readAllLines(data, CharsetUtils.UTF8)));

        // 获取 Home 目录
        LOG.info(Paths.get(System.getProperty("user.home")).toString());
    }

    @Test
    public void testGetInfo () throws IOException {
        Assert.assertTrue(Files.exists(data));
        Assert.assertEquals("data", data.getFileName().toString());
        LOG.info("uri: " + data.toUri());
        // LinkOption.NOFOLLOW_LINKS: 忽略符号链接
        LOG.info("realPath: " + data.toRealPath(LinkOption.NOFOLLOW_LINKS));
    }

    @Test
    public void testFileAttributeView () throws IOException {
        final BasicFileAttributes view = Files.readAttributes(data, BasicFileAttributes.class);
        Assert.assertFalse(view.isDirectory());
        LOG.info("CreateTime: " + view.creationTime());
    }

    /**
     * 目录遍历
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void testDirctoryStream () throws URISyntaxException, IOException {
        final DirectoryStream<Path> directoryStream = Files.newDirectoryStream(img);
        directoryStream.forEach(path -> LOG.info(path.getFileName().toString()));

        // 注意写正则式时不要随意补空格, 如显示主流图片 "*{gif,png,jpg}" 不要写成 "*{gif, png, jpg}"
        final DirectoryStream<Path> gifDirStream = Files.newDirectoryStream(img, "*{gif}");
        final List<String> gifNames = new ArrayList<>();
        gifDirStream.forEach(path -> gifNames.add(path.getFileName().toString()));
        LOG.info("Gif: " + CollectionUtils.toString(gifNames));

        // 自定义过滤器
        final DirectoryStream.Filter<Path> filter = path -> "1.jpg".equals(path.getFileName().toString());
        final DirectoryStream<Path> dirStream = Files.newDirectoryStream(img, filter);
        dirStream.forEach(path -> LOG.info(path.getFileName().toString()));
    }

    @Test
    public void testResolve () throws URISyntaxException {
        final Path jpg = img.resolve("1.jpg");
        LOG.info("jpg: " + jpg.getFileName().toString());
    }

    @Test
    public void testRelativize () {
        final Path jpg = img.resolve("1.jpg");
        LOG.info("1.jpg 切换到 data 的相对路径: " + jpg.relativize(data).toString());
    }

}
