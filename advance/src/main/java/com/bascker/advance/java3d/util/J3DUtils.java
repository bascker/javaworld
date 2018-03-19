package com.bascker.advance.java3d.util;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 * Java 3D 工具类
 *
 * @author bascker
 */
public class J3DUtils {

    private static final Color3f BLACK = new Color3f();
    private static final Color3f RED = new Color3f(1.8f, 0.1f, 0.1f);
    private static final Color3f GRAY = new Color3f(1.0f,1.0f,0.9f);
    private static final Color3f BLUE = new Color3f(0.05f,0.05f,0.2f);

    private J3DUtils () {}

    /**
     * Create a Directional Light which color is Red
     * @return
     */
    public static DirectionalLight createDefaultLight () {
        return createLight(redColor());
    }

    /**
     * Create a specified color Directional Light
     * @param lightColor
     * @return
     */
    public static DirectionalLight createLight (Color3f lightColor) {
        // DirectionalLight(定向光源)
        DirectionalLight light = new DirectionalLight();
        light.setColor(lightColor);

        // Create a vector from the specified coordinate(坐标): 控制灯光的照射方向(可改变坐标值，看看 3D 图形显示效果)
        Vector3f lightDirection = new Vector3f(-4.0f, -7.0f, -12.0f);
        light.setDirection(lightDirection);

        // Define default spherical bounding region(球型边界区域) which center point is (0, 0, 0) and radius is 1.0
        BoundingSphere boundingSphere = new BoundingSphere();
        // Set the Light's influencing region: 设置灯光可以照射到的区域，若不指定，则无法看到 3D 图形，一片黑暗
        light.setInfluencingBounds(boundingSphere);

        return light;
    }

    public static Color3f redColor () {
        return RED;
    }

    public static Color3f grayColor () {
        return GRAY;
    }

    public static Color3f blueColor () {
        return BLUE;
    }

    public static Color3f blackColor () {
        return BLACK;
    }

}
