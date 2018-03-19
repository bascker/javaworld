package com.bascker.bsutil;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * ImageProcess Unit Test
 *
 * @author bascker
 */
public class ImageProcessTest {

    @Test
    public void testIsEmpty () {
        try {
            Assert.assertFalse(ImageProcess.isEmpty("http://qhalbumdev.oss.aliyuncs.com/tiledtexture/2013/03/27/UVKyyHJCQUIJZQFBAAAA.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
