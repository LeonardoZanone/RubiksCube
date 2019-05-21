/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.models;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;

/**
 *
 * @author Leonardo
 */
public class Rubik {

    private final Cube[][][] cubes = new Cube[3][3][3];
    public Node node = new Node();
    public ArrayList<ArrayList<Node>> faces = new ArrayList<>();
    
    public Rubik(AssetManager assetManager) {

        for (int i = 0; i < 6; i++) {
            faces.add(new ArrayList<Node>());
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    cubes[i + 1][j + 1][k + 1] = new Cube(assetManager, String.format("Cube %d %d %d", i, j, k));
//                    cubes[i + 1][j + 1][k + 1].node.setLocalTransform(new Transform(new Vector3f(0f, 0f, 0f)));
//                    cubes[i + 1][j + 1][k + 1].node.setLocalTranslation(0f, 0f, 0f);
//                    cubes[i + 1][j + 1][k + 1].node.center();
                    for(Spatial s: cubes[i + 1][j + 1][k + 1].node.getChildren())
                        s.move(2 * i, 2 * j, 2 * k);
//                    cubes[i + 1][j + 1][k + 1].node.getChild(0).move(2 * i, 2 * j, 2 * k);

                    if (k == 1) {
                        cubes[i + 1][j + 1][k + 1].materials[0].setColor("Color", ColorRGBA.Blue);
                        faces.get(0).add(cubes[i + 1][j + 1][k + 1].node);
                    }
                    if (k == -1) {
                        cubes[i + 1][j + 1][k + 1].materials[3].setColor("Color", ColorRGBA.Green);
                        faces.get(3).add(cubes[i + 1][j + 1][k + 1].node);
                    }
                    if (i == 1) {
                        cubes[i + 1][j + 1][k + 1].materials[5].setColor("Color", ColorRGBA.Orange);
                        faces.get(5).add(cubes[i + 1][j + 1][k + 1].node);
                    }
                    if (i == -1) {
                        cubes[i + 1][j + 1][k + 1].materials[2].setColor("Color", ColorRGBA.Red);
                        faces.get(2).add(cubes[i + 1][j + 1][k + 1].node);
                    }
                    if (j == 1) {
                        cubes[i + 1][j + 1][k + 1].materials[1].setColor("Color", ColorRGBA.White);
                        faces.get(1).add(cubes[i + 1][j + 1][k + 1].node);
                    }
                    if (j == -1) {
                        cubes[i + 1][j + 1][k + 1].materials[4].setColor("Color", ColorRGBA.Yellow);
                        faces.get(4).add(cubes[i + 1][j + 1][k + 1].node);
                    }

                    node.attachChild(cubes[i + 1][j + 1][k + 1].node);
                }
            }
        }

    }

    public void rotateFace(int face, boolean clockWise, float tpf) {

        Node aux = new Node();
        
        node.attachChild(aux);

//        for(Node n : faces.get(face)) {
//            aux.attachChild(n);
//        }

        System.out.println("=============================================================================================================");
        System.out.println("=============================================================================================================");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if(face == 0 || face == 1)
                        if(cubes[i][j][k].node.getWorldTranslation().z > 0.8f)
                            aux.attachChild(cubes[i][j][k].node);
                    if(face == 2)
                        if(cubes[i][j][k].node.getWorldTranslation().x > 0.8f)
                            aux.attachChild(cubes[i][j][k].node);
                    
                    System.out.println("mygame.models.Rubik.rotateFace() Face: " + cubes[i][j][k].node.getName());
                    System.out.println("mygame.models.Rubik.rotateFace() Local - x: " + cubes[i][j][k].node.getLocalTranslation().x);
                    System.out.println("mygame.models.Rubik.rotateFace() World - x: " + cubes[i][j][k].node.getWorldTranslation().x);
                    System.out.println("mygame.models.Rubik.rotateFace() Local - y: " + cubes[i][j][k].node.getLocalTranslation().y);
                    System.out.println("mygame.models.Rubik.rotateFace() World - y: " + cubes[i][j][k].node.getWorldTranslation().y);
                    System.out.println("mygame.models.Rubik.rotateFace() Local - z: " + cubes[i][j][k].node.getLocalTranslation().z);
                    System.out.println("mygame.models.Rubik.rotateFace() World - z: " + cubes[i][j][k].node.getWorldTranslation().z);
                }
            }
        }

        Matrix4f matrix = new Matrix4f();
        aux.getLocalToWorldMatrix(matrix);
        if(face == 0 || face == 1)
            matrix.angleRotation(new Vector3f(0f, 0f, 90f));
        else if(face == 2)
            matrix.angleRotation(new Vector3f(90f, 0f, 0f));
        
        aux.getWorldRotation().set(aux.getWorldRotation().set(matrix.toRotationQuat()));
        
        
        System.out.println("=============================================================================================================");
        System.out.println("=============================================================================================================");

//        node.detachChild(aux);
        
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    cubes[i][j][k];
//                    System.out.println("mygame.models.Rubik.rotateFace() Face: " + cubes[i][j][k].node.getName());
//                    System.out.println("mygame.models.Rubik.rotateFace() Local - x: " + cubes[i][j][k].node.getLocalTranslation().x);
//                    System.out.println("mygame.models.Rubik.rotateFace() World - x: " + cubes[i][j][k].node.getWorldTranslation().x);
//                    System.out.println("mygame.models.Rubik.rotateFace() Local - y: " + cubes[i][j][k].node.getLocalTranslation().y);
//                    System.out.println("mygame.models.Rubik.rotateFace() World - y: " + cubes[i][j][k].node.getWorldTranslation().y);
//                    System.out.println("mygame.models.Rubik.rotateFace() Local - z: " + cubes[i][j][k].node.getLocalTranslation().z);
//                    System.out.println("mygame.models.Rubik.rotateFace() World - z: " + cubes[i][j][k].node.getWorldTranslation().z);
//                }
//            }
//        }
        
        System.out.println("=============================================================================================================");
        System.out.println("=============================================================================================================");
        
        for(Spatial s : aux.getChildren()) {
            String name = s.getName();
            String pos [] = name.replaceAll("Cube", "").trim().split(" ");
            cubes[Integer.parseInt(pos[0]) + 1][Integer.parseInt(pos[1]) + 1][Integer.parseInt(pos[2]) + 1].node.setLocalTranslation(s.getLocalTranslation());
            cubes[Integer.parseInt(pos[0]) + 1][Integer.parseInt(pos[1]) + 1][Integer.parseInt(pos[2]) + 1].node.setLocalTransform(s.getLocalTransform());
//            node.attachChild(cubes[Integer.parseInt(pos[0]) + 1][Integer.parseInt(pos[1]) + 1][Integer.parseInt(pos[2]) + 1].node);
//            cubes[Integer.parseInt(pos[0]) + 1][Integer.parseInt(pos[1]) + 1][Integer.parseInt(pos[2]) + 1].node.attachChild(s);
        }
        
        node.detachAllChildren();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    node.attachChild(cubes[i][j][k].node);
                }
            }
        }
//        Quaternion q = aux.getWorldRotation();;
//        q.toRotationMatrix().multLocal(new Matrix3f( (float) Math.cos( (float) Math.PI), -(float) Math.sin( (float) Math.PI), 0, (float) Math.sin( (float) Math.PI), 0, 0, 0, 0, 1));
        
//        matrix.mult(new Matrix4f( (float) Math.cos( (float) Math.PI), -(float) Math.sin( (float) Math.PI), 0f, (float) Math.sin( (float) Math.PI), 0f, 0f, 0f, 0f, 1f));
        
//        aux.setLocalRotation(matrix.toRotationQuat());
//        aux.getLocalRotation().toRotationMatrix().mult(new Matrix3f(0, -1, 0, 1, 0, 0, 0, 0, 1));
//        aux.setLocalRotation(new Matrix3f( (float) Math.cos( (float) Math.PI), -(float) Math.sin( (float) Math.PI), 0, (float) Math.sin( (float) Math.PI), 0, 0, 0, 0, 1));

//        aux.rotate(0f, 0f, (float) Math.PI / 2);
//        aux.setLocalRotation(0f, 0f, (float) Math.PI / 2);
        
//        node.attachChild(aux);

//        System.out.println("=================================================================");
//        System.out.println("mygame.models.Rubik.rotateFace() Depois");
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    System.out.println("mygame.models.Rubik.rotateFace() x: " + cubes[i][j][k].node.getWorldTranslation().x
//                            + " y: " + cubes[i][j][k].node.getWorldTranslation().y
//                            + " z: " + cubes[i][j][k].node.getWorldTranslation().z);
//                }
//            }
//        }
        
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    node.attachChild(cubes[i][j][k].node);
//                }
//            }
//        }

//        System.out.println("=================================================================");
//        System.out.println("mygame.models.Rubik.rotateFace() Bem Depois");
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    System.out.println("mygame.models.Rubik.rotateFace() x: " + cubes[i][j][k].node.getWorldTranslation().x
//                            + " y: " + cubes[i][j][k].node.getWorldTranslation().y
//                            + " z: " + cubes[i][j][k].node.getWorldTranslation().z);
//                    if (cubes[i][j][k].node.getWorldTranslation().x > 0.8) {
//                        aux.attachChild(cubes[i][j][k].node);
//                    }
//                }
//            }
//        }
//
//        aux.rotate((float) Math.PI / 2, 0f, 0f);
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    node.attachChild(cubes[i][j][k].node);
//                }
//            }
//        }
    }

}
