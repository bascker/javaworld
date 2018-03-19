package com.bascker.advance.java3d;

import org.junit.Test;

/**
 * ObjFileLoader Unit Test
 *
 * @author bascker
 */
public class ObjFileLoaderTest {

    @Test
    public void test () {
        final ObjFileLoader objFileLoader = new ObjFileLoader("D:\\文档\\Obj文件\\布迪加双座敞篷跑车3D图纸.obj");
        objFileLoader.create();
    }

}
