/*
 * ObjectFileLoader.java
 * Copyright 2017 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.bascker.advance.java3d;

import com.bascker.advance.java3d.util.J3DUtils;
import com.bascker.advance.java3d.util.ObjUtils;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

/**
 * ObjFileLoader: 加载 Obj 模型，并在 Applet 中显示
 * 1.不依赖 Applet 实现 Java3D 加载外部模型: http://www.cnblogs.com/dennisit/archive/2013/05/07/3065479.html
 * 2.基于 swing 实现 Java3D 加载外部模型: http://www.cnblogs.com/dennisit/archive/2013/05/07/3065479.html
 *
 * @author bascker
 */
public class ObjFileLoader extends Applet {

    private String objFile;

    private static final BoundingSphere DEFAULT_BOUND = new BoundingSphere();

    public ObjFileLoader (final String objFile) throws HeadlessException {
        this.objFile = objFile;
    }

    public void create () {
        setLayout(new BorderLayout());

        // 1.Create 3D Canvas(画布)
        final Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center",canvas3D);

        // 2.Create an universe, and add canvas
        final SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);

        // 3.Set observe point
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();

        // 3.Create BranchGroup to store node：构建内容
        final BranchGroup branchGroup = createSceneGraph();
        branchGroup.compile();                                          // 编译所有子图

        // 4.Add BranchGroup to universe：实际是将内容视图添加到 Locale 对象中
        simpleUniverse.addBranchGraph(branchGroup);

        // 5.在 Frame 中显示
        new MainFrame(this, 360, 360);
    }

    /**
     * 创建场景图分支
     * @return
     */
    private BranchGroup createSceneGraph () {
        // Create a BranchGroup to store node
        final BranchGroup root = new BranchGroup();

        // 1 Set BackGround Color
        final Background background = createBackground();
        root.addChild(background);

        // 2 Set light
        final DirectionalLight light = createLight();
        root.addChild(light);

        // 3 Create TransformGroup to store 3d transform data
        final TransformGroup transformGroup = new TransformGroup();

        // 3.1 store scale data
        final Transform3D transform3D = new Transform3D();
        transform3D.setScale(0.8);                                      // 设置缩放比例
        transformGroup.setTransform(transform3D);

        // 3.2. Load Object Model
        final TransformGroup objTrans = new TransformGroup();
        objTrans.addChild(ObjUtils.loadObjFile(objFile));               // store object model

        // 3.3 [可选] 设置几何变化,绕Y轴中心旋转
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        final Transform3D yAxis = new Transform3D();
        final Alpha rotationAlpha = new Alpha(-1,Alpha.INCREASING_ENABLE,
                0,0,
                4000,0,0,
                0,0,0);
        final RotationInterpolator rotationInterpolator = new RotationInterpolator(rotationAlpha, objTrans, yAxis, 0.0f,(float)Math.PI*2.0f);
        rotationInterpolator.setSchedulingBounds(DEFAULT_BOUND);
        objTrans.addChild(rotationInterpolator);

        // 3.4 [可选] 设置模型行为,鼠标触控 360 度空间旋转
//        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
//        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        // 鼠标旋转功能
//        MouseRotate behavior = new MouseRotate();
//        behavior.setTransformGroup(transformGroup);
//        behavior.setSchedulingBounds(DEFAULT_BOUND);
//        objTrans.addChild(behavior);
//
//        // 鼠标拖拽功能
//        MouseTranslate mouseTranslate = new MouseTranslate();
//        mouseTranslate.setTransformGroup(transformGroup);
//        mouseTranslate.setSchedulingBounds(DEFAULT_BOUND);
//        objTrans.addChild(mouseTranslate);
//
//        // 鼠标缩放功能
//        MouseWheelZoom mouseWheelZoom = new MouseWheelZoom();
//        mouseWheelZoom.setTransformGroup(transformGroup);
//        mouseWheelZoom.setSchedulingBounds(DEFAULT_BOUND);
//        objTrans.addChild(mouseWheelZoom);

        transformGroup.addChild(objTrans);
        root.addChild(transformGroup);

        return root;
    }

    public DirectionalLight createLight () {
        final Color3f lightColor = J3DUtils.grayColor();
        final Vector3f lightDirection = new Vector3f(4.0f,-7.0f,-12.0f);
        final DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(DEFAULT_BOUND);

        return light;
    }

    public Background createBackground () {
        final Color3f bgColor = J3DUtils.blueColor();
        final Background background = new Background(bgColor);
        background.setApplicationBounds(DEFAULT_BOUND);

        return background;
    }

    // -----------------------------------
    // Getter/Setter
    // -----------------------------------

    public String getObjFile() {
        return objFile;
    }

    public void setObjFile(final String objFile) {
        this.objFile = objFile;
    }
}
