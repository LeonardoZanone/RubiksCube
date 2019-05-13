package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import mygame.models.Cube;
import mygame.models.Rubik;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.setShowSettings(false);
        app.start();
    }
    
    Rubik[] cubes = new Rubik[2];
    float rotation = 0;
    boolean test = true;

    @Override
    public void simpleInitApp() {
        
//        flyCam.setEnabled(false);
        
        cubes[0] = new Rubik(assetManager);
        cubes[1] = new Rubik(assetManager);
//        cubes[0].node.setLocalTransform(new Transform(new Vector3f(0f, 0f, 0f)));
//        
//        cubes[1].node.setLocalTransform(new Transform(new Vector3f(-3f, 2f, 1.5f)));
//        cubes[1].node.setLocalScale(new Vector3f(0.35f, 0.35f, 0.35f));
//        cubes[1].node.rotate(0f, (float)Math.PI, 0f);
//        rootNode.attachChild(cubes[0].node);
        rootNode.attachChild(cubes[0].node);
        
        cubes[0].node.scale(0.5f);
        cubes[0].rotateFace(0, true, 0);
        cubes[0].rotateFace(0, true, 0);
//        cubes[0].faces[0].rotate(0f, 0f, (float) Math.PI);

//          Cube test = new Cube(assetManager);
//          test.materials[0].setColor("Color", ColorRGBA.Blue);
//          rootNode.attachChild(test.node);
    }

 
    @Override
    public void simpleUpdate(float tpf) {
//        if(test) {
//            cubes[0].rotateFace(0, true, tpf);
//            test = false;
//        }
        rotation+=tpf;
//        cubes[0].node.rotate(tpf, 0f, 0f);
//        Matrix3f mat = new Matrix3f();
//        for(Rubik cube : cubes) {
//            cubes[0].node.setLocalRotation(new Matrix3f(0, -1, 0, 1, 0, 0, 0, 0, 1));
//            if(rotation < 1)
//                cube.faces[0].rotate(0f, 0f, tpf);
//
//        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
