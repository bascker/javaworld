package com.bascker.general.io.nio;

import com.bascker.bsutil.CollectionUtils;
import com.bascker.bsutil.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * FileChannel Case
 *
 * 1.FileChanel
 *
 * 2.ByteBuffer
 *  2.1 字节缓冲区
 *  2.2 常用方法
 *      1) allocate()
 *      2) flip(): 重置 position & limit, 让缓冲区可以将新读入的数据写入另一个通道
 *      3) wrap(): 将一个现有的数组转换为缓冲区。小心使用，一旦完成包装，底层数据就可以通过缓冲区或者直接访问
 *      4) slice(): 缓冲区分片，根据现有的缓冲区创建一种子缓冲区，子缓冲区与父缓冲区共享部分数据
 *
 * 3.MappedByteBuffer
 *
 * 4.FileLock
 *
 * @author bascker
 */
public class ChannelCases {

    private static final Logger LOG = LoggerFactory.getLogger(ChannelCases.class);
    private static final int MB = 1024;
    private Path data;
    private Path tmp;
    private FileInputStream fis;
    private FileOutputStream fos;
    private FileChannel fcIn;
    private FileChannel fcOut;

    @Before
    public void before () throws URISyntaxException, IOException {
        data = Paths.get(FileUtils.getFileInResources("data").toURI());
        fis = new FileInputStream(data.toFile());
        fcIn = fis.getChannel();
        tmp = Files.createTempFile("nio", "tmp");
        fos = new FileOutputStream(tmp.toFile());
        fcOut = fos.getChannel();
    }

    @After
    public void after () throws IOException {
        fcOut.close();
        fcIn.close();
        fis.close();
        fos.close();
        Files.deleteIfExists(tmp);
    }

    /**
     * 单向读(one-way read)
     * @throws IOException
     */
    @Test
    public void testRead () throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1 * MB);
        // read data to buffer
        int r = fcIn.read(buffer);
        if (r == -1) {
            return;
        }

        Assert.assertFalse(buffer.isReadOnly());
        // reset position and limit
        buffer.flip();
        final StringBuffer sb = new StringBuffer();
        while (buffer.hasRemaining()) {
            sb.append((char) buffer.get());
        }
        LOG.info("file content: " + sb.toString());
    }

    /**
     *单向写(one-way write)
     */
    @Test
    public void testWrite () throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1 * MB);
        final byte[] msg = "My name is bascker".getBytes();
        for (int i = 0; i < msg.length; i ++) {
            buffer.put(msg[i]);
        }
        buffer.flip();
        fcOut.write(buffer);

        Assert.assertEquals("My name is bascker", CollectionUtils.toString(Files.readAllLines(tmp)));
    }

    /**
     * 同时进行读写: 将一个文件的内容复制到另一个文件, 并追加内容
     * @throws IOException
     */
    @Test
    public void testReadWrite () throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1 * MB);
        while (true) {
            buffer.clear();

            int r = fcIn.read(buffer);
            if (r == -1) {
                break;
            }

            final byte[] msg = "<< Append by FileChannel".getBytes();
            for (int i = 0; i < msg.length; i ++) {
                buffer.put(msg[i]);
            }
            buffer.flip();
            fcOut.write(buffer);
        }

        LOG.info("TMP File Content: " + CollectionUtils.toString(Files.readAllLines(tmp)));
    }

    @Test
    public void testWrapArray () {
        final byte[] bytes = new byte[10];
        final ByteBuffer buffer = ByteBuffer.wrap(bytes);
        Assert.assertEquals(10, buffer.capacity());
    }

    @Test
    public void testSlice () {
        final ByteBuffer buffer = ByteBuffer.allocate(10);
        IntStream.range(0, buffer.capacity()).forEach(i -> buffer.put((byte) i));

        /*
         * 创建一个包含槽 3 到槽 6 的子缓冲区. 某种意义上，子缓冲区就像原来的缓冲区中的一个窗口
         * 窗口的起始和结束位置通过设置 position 和 limit 值来指定 [position, limit)
         */
        buffer.position(3);
        buffer.limit(7);
        final ByteBuffer sonBuffer = buffer.slice();

        // 数据共享测试: 更改子缓冲区内容
        IntStream.range(0, sonBuffer.capacity()).forEach(i -> {
            byte b = sonBuffer.get(i);
            b *= 11;
            sonBuffer.put(i, b);
        });

        // 查看父缓冲: 共享区间内容已被更改
        buffer.position(0);
        buffer.limit(buffer.capacity());
        final List<String> rs = new ArrayList<>();
        while (buffer.hasRemaining()) {
            rs.add(String.valueOf(buffer.get()));
        }
        Assert.assertEquals("0 1 2 33 44 55 66 7 8 9", CollectionUtils.toString(rs));
    }

}