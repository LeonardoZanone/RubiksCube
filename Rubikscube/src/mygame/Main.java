package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

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

    private Node[][] planes = new Node[6][9];

    @Override
    public void simpleInitApp() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    Box plane;

                    switch (i % 3) {
                        case 0:
                            plane = new Box(0.45f, 0.45f, 0f);
                            break;
                        case 1:
                            plane = new Box(0.45f, 0f, 0.45f);
                            break;
                        default:
                            plane = new Box(0f, 0.45f, 0.45f);
                            break;
                    }

                    Geometry planeGeometry = new Geometry(String.format("Plane %s%d", i, 3*j + k), plane);
                    
                    Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

                    switch (i) {
                        case 0:
                            mat.setColor("Color", ColorRGBA.Blue);
                            break;
                        case 1:
                            mat.setColor("Color", ColorRGBA.White);
                            break;
                        case 2:
                            mat.setColor("Color", ColorRGBA.Red);
                            break;
                        case 3:
                            mat.setColor("Color", ColorRGBA.Green);
                            break;
                        case 4:
                            mat.setColor("Color", ColorRGBA.Yellow);
                            break;
                        case 5:
                            mat.setColor("Color", ColorRGBA.Orange);
                            break;
                        default:
                            break;

                    }

                    planeGeometry.setMaterial(mat);
                    Node planeNode = new Node();

                    switch (i) {
                        case 0:
                            planeNode.move((float) j, (float) k, 2.5f);
                            break;
                        case 1:
                            planeNode.move((float) j, 2.5f, (float) k);
                            break;
                        case 2:
                            planeNode.move(-0.5f, (float) j, (float) k);
                            break;
                        case 3:
                            planeNode.move((float) j, (float) k, -0.5f);
                            break;
                        case 4:
                            planeNode.move((float) j, -0.5f, (float) k);
                            break;
                        case 5:
                            planeNode.move(2.5f, (float) j, (float) k);
                            break;
                        default:
                            break;
                    }

                    planeNode.attachChild(planeGeometry);
                    planes[i][3*j + k] = planeNode;
                    rootNode.attachChild(planeNode);

                }

            }
        }
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
