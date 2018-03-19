package com.bascker.advance.java3d;

import com.bascker.advance.java3d.util.J3DUtils;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import java.applet.Applet;
import java.awt.*;

/**
 * Create a 3D Cylinder(圆柱体) and show it
 *
 * @author bascker
 */
public class Cylinder3d extends Applet {

    private float mRadius;
    private float mCylinderHeight;

    public Cylinder3d(final float radius, final float cylinderHeight) throws HeadlessException {
        mRadius = radius;
        mCylinderHeight = cylinderHeight;
    }

    public void create () {
        // 1.Create a universe
        final SimpleUniverse universe = new SimpleUniverse();

        // 2.Create a BranchGroup to store node
        final BranchGroup branchGroup = new BranchGroup();

        // 2.1 Create Cylinder
        final Cylinder cylinder = new Cylinder(mRadius, mCylinderHeight);
        branchGroup.addChild(cylinder);

        // 2.2 Create light
        final DirectionalLight light = J3DUtils.createDefaultLight();
        branchGroup.addChild(light);

        // 3.Set observer point
        universe.getViewingPlatform().setNominalViewingTransform();

        // 4.Add BranchGroup to universe
        universe.addBranchGraph(branchGroup);
    }

    // --------------------------------------
    // Getter/Setter
    // --------------------------------------

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(final float radius) {
        mRadius = radius;
    }

    public float getCylinderHeight() {
        return mCylinderHeight;
    }

    public void setCylinderHeight(final float cylinderHeight) {
        mCylinderHeight = cylinderHeight;
    }
}
