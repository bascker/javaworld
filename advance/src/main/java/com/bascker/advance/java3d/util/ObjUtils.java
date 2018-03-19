package com.bascker.advance.java3d.util;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import org.apache.commons.lang3.StringUtils;

import javax.media.j3d.BranchGroup;
import java.io.FileNotFoundException;

/**
 * Obj 模型工具类
 *
 * @author bascker
 */
public class ObjUtils {

    private static final double CREASEANGLE = 60.0;                 // 折角

    private ObjUtils() {}

    /**
     * load an obj file, and return a BranchGroup of contains the obj model
     * @param filename  obj file, not null
     * @return
     */
    public static BranchGroup loadObjFile (String filename) {
        if (StringUtils.isBlank(filename)) {
            throw new IllegalArgumentException("Argument is not be allowed, filename: " + filename);
        }

        final int flags = ObjectFile.RESIZE;
        final ObjectFile objectFile = new ObjectFile(flags, (float) (CREASEANGLE * Math.PI) / 180);
        Scene scene = null;
        try {
            scene = objectFile.load(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Call getSceneGroup() for attach obj model to scene, and return a BranchGroup of contains the obj model
        return scene.getSceneGroup();
    }

}
