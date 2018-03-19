package com.bascker.advance.java3d;

import com.bascker.advance.java3d.util.J3DUtils;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import java.applet.Applet;

/**
 * Create a 3D Conus(圆锥) and show it
 *
 * @author bascker
 */
public class Conus3d extends Applet {

    private float mRadius;
    private float mConusHeight;

    public Conus3d (final float radius, final float conusHeight) {
        mRadius = radius;
        mConusHeight = conusHeight;
    }

    public void create () {
        // 1.Create a universe
        final SimpleUniverse simpleUniverse = new SimpleUniverse();

        // 2.Create a BranchGroup to store data
        final BranchGroup branchGroup = new BranchGroup();

        // 2.1 Create a Conus and store it
        final Cone cone = new Cone(mRadius, mConusHeight);
        branchGroup.addChild(cone);

        // 2.2 Create light and store it
        final DirectionalLight lightDirection = J3DUtils.createDefaultLight();
        branchGroup.addChild(lightDirection);

        // 3.Set Observe point
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();

        // 4.Add group to universe
        simpleUniverse.addBranchGraph(branchGroup);
    }

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(final float radius) {
        mRadius = radius;
    }

    public float getConusHeight() {
        return mConusHeight;
    }

    public void setConusHeight(final float conusHeight) {
        mConusHeight = conusHeight;
    }
}
