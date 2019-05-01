package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
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
    
    Node[] nodes = new Node[2];

    @Override
    public void simpleInitApp() {
        
        flyCam.setEnabled(false);
        
        Rubik cube = new Rubik(assetManager);
        Rubik miniCube = new Rubik(assetManager);
        cube.node.setLocalTransform(new Transform(new Vector3f(0f, 0f, 0f)));
        miniCube.node.setLocalTransform(new Transform(new Vector3f(-3f, 2f, 1.5f)));
        miniCube.node.setLocalScale(new Vector3f(0.35f, 0.35f, 0.35f));
        miniCube.node.rotate(0f, (float)Math.PI, 0f);
        nodes[0] = cube.node;
        nodes[1] = miniCube.node;
        rootNode.attachChild(cube.node);
        rootNode.attachChild(miniCube.node);
        
    }

 
    @Override
    public void simpleUpdate(float tpf) {
        for(Node node : nodes) {
            node.rotate(tpf, 0f, 0f);
        }
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
