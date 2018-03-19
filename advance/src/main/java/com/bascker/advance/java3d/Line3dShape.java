package com.bascker.advance.java3d;

import com.bascker.advance.java3d.util.J3DUtils;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Shape3D;

/**
 * Create 3D line and show it
 *
 * @author bascker
 */
public class Line3dShape extends LineShape {

    public Line3dShape(final float lineWidth, final float[] verts, final float[] colors) {
        super(lineWidth, verts, colors);
    }

    @Override
    public void create () {
        // 1.Create a universe
        final SimpleUniverse simpleUniverse = new SimpleUniverse();

        // 2.Create a BranchGroup to store node
        final BranchGroup branchGroup = new BranchGroup();

        // 2.1 Create line
        final Shape3D lineShape = new LineShape(getLineWidth(), getVerts(), getColors());
        branchGroup.addChild(lineShape);

        // 2.2 Create light
        final DirectionalLight light= J3DUtils.createDefaultLight();
        branchGroup.addChild(light);

        // 3.Set Observe point
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();

        // 4.Add BranchGroup tp universe
        simpleUniverse.addBranchGraph(branchGroup);
    }

}
