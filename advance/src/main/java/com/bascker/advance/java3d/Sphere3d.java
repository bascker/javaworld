package com.bascker.advance.java3d;

import com.bascker.advance.java3d.util.J3DUtils;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;

/**
 * Create a 3d Sphere(球体) and show it
 *
 * @author bascker
 */
public class Sphere3d {

    public float mRadius;

    public Sphere3d(final float radius) {
        mRadius = radius;
    }

    public void create () {
        // 1.Create an universe
        final SimpleUniverse simpleUniverse = new SimpleUniverse();

        // 2.Create a BranchGroup to Store data
        final BranchGroup branchGroup = new BranchGroup();

        // 2.1 Create a sphere
        final Sphere sphere = new Sphere(mRadius);
        branchGroup.addChild(sphere);

        // 2.2 Create light
        final DirectionalLight lightDirection = J3DUtils.createDefaultLight();
        branchGroup.addChild(lightDirection);

        // 3.Set observe point
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();

        // 4.Add group to universe
        simpleUniverse.addBranchGraph(branchGroup);
    }

    // ------------------------------------------
    // Getter/Setter
    // ------------------------------------------

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(final float radius) {
        mRadius = radius;
    }
}
