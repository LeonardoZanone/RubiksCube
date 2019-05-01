/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Leonardo
 */
public class Rubik {

    private Node[][] planes = new Node[6][9];
    public Node node;
    
    public Rubik(AssetManager assetManager)
    {
        this.node = CreateCube(assetManager);
    }

    private Node CreateCube(AssetManager assetManager) {
        Node cube = new Node();
//        cube.setLocalTransform(new Transform(new Vector3f(1.5f, 1.5f, 1.5f)));
        for (int i = 0; i < 6; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {

                    Box plane;

                    switch (i % 3) {
                        case 0:
                            plane = new Box(0.49f, 0.49f, 0f);
                            break;
                        case 1:
                            plane = new Box(0.49f, 0f, 0.49f);
                            break;
                        default:
                            plane = new Box(0f, 0.49f, 0.49f);
                            break;
                    }

                    Geometry planeGeometry = new Geometry(String.format("Plane %s%d", i, 3 * (j+1) + k + 1), plane);

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
                            planeNode.setLocalTransform(new Transform(new Vector3f((float) j, (float) k, 1.5f)));
                            break;
                        case 1:
                            planeNode.setLocalTransform(new Transform(new Vector3f((float) j, 1.5f, (float) k)));
                            break;
                        case 2:
                            planeNode.setLocalTransform(new Transform(new Vector3f(-1.5f, (float) j, (float) k)));
                            break;
                        case 3:
                            planeNode.setLocalTransform(new Transform(new Vector3f((float) j, (float) k, -1.5f)));
                            break;
                        case 4:
                            planeNode.setLocalTransform(new Transform(new Vector3f((float) j, -1.5f, (float) k)));
                            break;
                        case 5:
                            planeNode.setLocalTransform(new Transform(new Vector3f(1.5f, (float) j, (float) k)));
                            break;
                        default:
                            break;
                    }

                    planeNode.attachChild(planeGeometry);
                    planes[i][3 * (j + 1) + k + 1] = planeNode;
                    cube.attachChild(planeNode);
                }

            }
        }
        return cube;
    }

}
