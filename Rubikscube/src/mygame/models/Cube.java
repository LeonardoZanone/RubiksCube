/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Leonardo
 */
public class Cube {

    public Node node;
    public Node[] faces = new Node[6];
    public Material[] materials = new Material[6];

    public Cube(AssetManager assetManager, String name) {
        node = new Node(name);
        Box plane;
        Geometry planeGeometry;
        
        for (int i = 0; i < 6; i++) {
            
            faces[i] = new Node();
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.DarkGray);
            materials[i] = mat;
            
            switch (i) {
                case Enums.CubeFaces.FRONT:
                    plane = new Box(0.95f, 0.95f, 0f);
                    planeGeometry = new Geometry("Face", plane);
                    planeGeometry.setLocalTranslation(0f, 0f, 0.95f);
                    planeGeometry.setMaterial(mat);
                    faces[i].attachChild(planeGeometry);
                    break;
                case Enums.CubeFaces.BACK:
                    plane = new Box(0.95f, 0.95f, 0f);
                    planeGeometry = new Geometry("Face", plane);
                    planeGeometry.setLocalTranslation(0f, 0f, -0.95f);
                    planeGeometry.setMaterial(mat);
                    faces[i].attachChild(planeGeometry);
                    break;
                case Enums.CubeFaces.UP:
                    plane = new Box(0.95f, 0f, 0.95f);
                    planeGeometry = new Geometry("Face", plane);
                    planeGeometry.setLocalTranslation(0f, 0.95f, 0f);
                    planeGeometry.setMaterial(mat);
                    faces[i].attachChild(planeGeometry);
                    break;
                case Enums.CubeFaces.DOWN:
                    plane = new Box(0.95f, 0f, 0.95f);
                    planeGeometry = new Geometry("Face", plane);
                    planeGeometry.setLocalTranslation(0f, -0.95f, 0f);
                    planeGeometry.setMaterial(mat);
                    faces[i].attachChild(planeGeometry);
                    break;
                case Enums.CubeFaces.LEFT:
                    plane = new Box(0f, 0.95f, 0.95f);
                    planeGeometry = new Geometry("Face", plane);
                    planeGeometry.setLocalTranslation(-0.95f, 0f, 0f);
                    planeGeometry.setMaterial(mat);
                    faces[i].attachChild(planeGeometry);
                    break;
                case Enums.CubeFaces.RIGHT:
                    plane = new Box(0f, 0.95f, 0.95f);
                    planeGeometry = new Geometry("Face", plane);
                    planeGeometry.setLocalTranslation(0.95f, 0f, 0f);
                    planeGeometry.setMaterial(mat);
                    faces[i].attachChild(planeGeometry);
                    break;
                default:
                    plane = new Box(0f, 0f, 0f);
                    planeGeometry = new Geometry("Face", plane);
                    planeGeometry.setMaterial(mat);
                    faces[i].attachChild(planeGeometry);
                    break;

            }
            
            node.attachChild(faces[i]);
        }
    }

    
}
