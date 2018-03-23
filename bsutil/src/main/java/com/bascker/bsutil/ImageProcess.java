package com.bascker.bsutil;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Image Processor
 *
 * @author bascker
 */
public class ImageProcess {

    private static final Logger LOG = LoggerFactory.getLogger(ImageProcess.class);
    private static final String JPG = ".jpg";
    private static final int WHITE = 0xFFFFFFFF;
    private static final int CONN_TIMEOUT = 5 * 1000;

    public static File download (final String imgUrl) {
        try {
            final File file = File.createTempFile("previewImg", JPG);
            final URL url = new URL(imgUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(CONN_TIMEOUT);
            final InputStream inputStream = conn.getInputStream();
            FileUtils.copyInputStreamToFile(inputStream, file);

            return file;
        } catch (IOException e) {
            LOG.error("download", e);
        }

        return null;
    }

    public static List<File> dowloads (final Set<String> urls) {
        if (Objects.isNull(urls) || urls.isEmpty()) {
            return Collections.emptyList();
        }

        final List<File> files = new CopyOnWriteArrayList<>();
        final ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            urls.stream().forEach(url -> threadPool.submit(() -> files.add(download(url))));
        } finally {
            threadPool.shutdown();
        }

        return files;
    }

    public static boolean isEmpty (final File imageFile) throws IOException {
        return isEmpty(ImageIO.read(imageFile));
    }

    public static boolean isEmpty (final String imageUrl) throws IOException {
        final URL url = new URL(imageUrl);

        return isEmpty(ImageIO.read(url));
    }

    /**
     * 白图检测
     * @param image
     * @return
     */
    public static boolean isEmpty (final BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int i = 0; i < width; i ++) {
            for (int j = 0; j < height; j ++) {
                if (!Objects.equals(WHITE, image.getRGB(i, j))) {
                    return false;
                }
            }
        }

        return true;
    }

    private ImageProcess () {}

}
