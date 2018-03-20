package com.bascker.bsutil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

/**
 * FileUtils
 *
 * @author bascker
 */
public class FileUtils {

    private FileUtils () {}

    public static String getFileType (final Path path) {
        final String filename = path.getFileName().toString();
        final int index = filename.indexOf(".");

        return filename.substring(index);
    }

    /**
     * 获取 resources 目录下的文件
     * @param filename
     * @return
     */
    public static URL getFileInResources (final String filename) {
        return getClassLoader().getResource(filename);
    }

    /**
     * 删除单个文件
     * @param filepath
     * @throws IOException
     */
    public static void delete (final String filepath) throws IOException {
        final Path path = Paths.get(filepath);
        Files.delete(path);
    }

    /**
     * 遍历目录，不包含子目录
     * @param dir
     * @return
     */
    public static Set<Path> visite (final String dir) throws IOException {
        return visite(dir, false);
    }

    /**
     * 遍历目录
     * @param dir
     * @param deep  是否深度遍历, true: 遍历当前目录及其子目录文件, false: 仅仅遍历当前目录
     * @return
     */
    public static Set<Path> visite (final String dir, final boolean deep) throws IOException {
        final Set<Path> paths = new HashSet<>();
        final Path dirPath = Paths.get(dir);
        if (deep) {
            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    paths.add(file);
                    return super.visitFile(file, attrs);
                }
            });
        } else {
            final DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath);
            dirStream.forEach(p -> paths.add(p));
        }

        return paths;
    }

    /**
     * 创建多级目录
     * @param dir
     */
    public static void createDirs (final String dir) throws IOException {
        final Path path = Paths.get(dir);
        Files.createDirectories(path);
    }

    private static ClassLoader getClassLoader () {
        return FileUtils.class.getClassLoader();
    }
}
