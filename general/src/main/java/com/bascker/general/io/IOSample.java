package com.bascker.general.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * IO Sample
 *
 * @author bascker
 */
public class IOSample {

    /**
     * 直接通过文件输入输出流单字节读写文件
     * @param from  源文件
     * @param to    目标文件
     */
    public void readWriteByByte(final File from, final File to){
        try (InputStream in = new FileInputStream(from);
             OutputStream out = new FileOutputStream(to)) {
            while(true){
                int byteData = in.read();
                if(byteData == -1){
                    break;
                }
                out.write(byteData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过系统缓冲输入输出流类读写文件(缓冲区块读取), 提高 IO 效率
     *  Java 中一般的输入输出流类都是采用"单字节"(每次读取只操作一个字节的数据)的读取方法,
     *  为加快 IO 效率，可以采用 IO 缓冲类(每次操作一个缓冲区大小的数据块)
     */
    public void readWriteBySysBuffer(final File from, final File to){
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(from));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(to))) {
            while(true){
                int byteData = in.read();
                if(byteData == -1){
                    break;
                }
                out.write(byteData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义缓冲区读写文件, 提高 IO 效率
     */
    public void readWriteByCustomBuff(final File from, final File to){
        try (InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to)) {
            // 不同点:使用数组充当缓冲区
            final int availableLen = in.available();
            byte[] buffer = new byte[availableLen];

            final int byteData = in.read(buffer);	// 读
            out.write(buffer);				// 写
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过压缩流读取与写入, 提高 IO 效率
     * 场景: 文件压缩, 网络数据传输
     * @param from
     * @param to
     */
    public void readWriterByZip(final File from, final File to){
        try (FileInputStream in = new FileInputStream(from);
             ZipOutputStream out = new ZipOutputStream(new FileOutputStream(to))) {
            out.setMethod(ZipOutputStream.DEFLATED);
            final ZipEntry zipEntry = new ZipEntry(to.getAbsolutePath());	// 创建一个 ZipEntry
            out.putNextEntry(zipEntry);						                // 添加 ZipEntry 与相关数据

            int len = 0;
            byte[] buff = new byte[1024];
            while((len = in.read(buff)) != -1){
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
