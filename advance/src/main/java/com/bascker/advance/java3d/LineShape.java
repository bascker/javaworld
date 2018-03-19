package com.bascker.advance.java3d;


import javax.media.j3d.Appearance;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Shape3D;

/**
 * 创建 3D 直线
 *
 * @author bascker
 */
public class LineShape extends Shape3D {

    private float mLineWidth;                               // 线宽
    private float[] mVerts;                                 // 直线的顶点坐标
    private float[] mColors;                                // 顶点的颜色

    public LineShape(final float lineWidth, final float[] verts, final float[] colors) {
        mLineWidth = lineWidth;
        mVerts = verts;
        mColors = colors;
    }

    public void create () {
        final LineArray lineArray = createLineArray();
        final LineAttributes lineAttributes = createLineAttributes();

        final Appearance appearance = new Appearance();
        appearance.setLineAttributes(lineAttributes);
        this.setGeometry(lineArray);
        this.setAppearance(appearance);
    }

    /**
     * 创建直线数组对象
     * @return
     */
    private LineArray createLineArray () {
        final LineArray lineArray = new LineArray(6, LineArray.COORDINATES | LineArray.COLOR_3);
        lineArray.setCoordinate(0, mVerts);             // 设置直线对象的坐标数组
        lineArray.setColor(0, mColors);                 // 设置直线对象的颜色数组

        return lineArray;
    }

    /**
     * 创建直线属性对象
     * @return
     */
    private LineAttributes createLineAttributes () {
        final LineAttributes lineAttributes = new LineAttributes();
        lineAttributes.setLineWidth(mLineWidth);            // 设置线宽
        lineAttributes.setLineAntialiasingEnable(true);     // 设置直线的渲染效果

        return lineAttributes;
    }

    // ----------------------------------------
    // Getter/Setter
    // ----------------------------------------

    public float getLineWidth() {
        return mLineWidth;
    }

    public void setLineWidth(final float lineWidth) {
        mLineWidth = lineWidth;
    }

    public float[] getVerts() {
        return mVerts;
    }

    public void setVerts(final float[] verts) {
        mVerts = verts;
    }

    public float[] getColors() {
        return mColors;
    }

    public void setColors(final float[] colors) {
        mColors = colors;
    }
}
