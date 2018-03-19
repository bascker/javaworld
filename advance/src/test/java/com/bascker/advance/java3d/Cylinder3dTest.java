package com.bascker.advance.java3d;

import org.junit.Test;

/**
 * Cylinder3d Unit Test
 *
 * @author bascker
 */
public class Cylinder3dTest {

    @Test
    public void test () {
        final Cylinder3d cylinder3d = new Cylinder3d(.5f, 1.0f);
        cylinder3d.create();
    }

}
