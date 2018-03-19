package com.bascker.advance.java3d;

import org.junit.Test;

/**
 * LineShape & Line3dShape Unit Test
 *
 * @author bascker
 */
public class LineShapeTest {

    private static final float[] verts = {0.5f,0.5f,0.0f, -0.5f,0.5f,0.0f,
            0.3f,0.0f,0.0f, -0.3f,0.0f,0.0f,
            -0.5f,-0.5f,0.0f, 0.5f,-0.5f,0.0f};
    private static final float[] colors = {0.0f,0.5f,1.0f,  0.0f,0.5f,1.0f,
            0.0f,0.8f,2.0f,  1.0f,0.0f,0.3f,
            0.0f,1.0f,0.3f,  0.3f,0.8f,0.0f};

    @Test
    public void testLineShape () {
        final LineShape lineShape = new LineShape(10.0f, verts, colors);
        lineShape.create();
    }

    @Test
    public void testLine3dShape () {
        final Line3dShape line3dShape = new Line3dShape(10.0f, verts, colors);
        line3dShape.create();
    }

}
