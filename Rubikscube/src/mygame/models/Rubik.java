/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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
    public ArrayList<Face> faces = new ArrayList<>();
    ColorRGBA[][] currentState = new ColorRGBA[6][9];
    public Material[] materials = new Material[54]; 

    
    public Rubik(AssetManager assetManager, ColorRGBA [][] state) {

        currentState = state;
        for (int i = 0; i < 6; i++) {
            faces.add(new Face(i));
        }
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    cubes[i + 1][j + 1][k + 1] = new Cube(assetManager, String.format("Cube %d %d %d", i, j, k));
                    for(Spatial s: cubes[i + 1][j + 1][k + 1].node.getChildren())
                        s.move(2 * i, 2 * j, 2 * k);
                }
            }
        }
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    
                    if (k == 1) {
                        materials[9 * Enums.CubeFaces.FRONT + 3 * (i + 1) + j + 1] = cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.FRONT];
                        cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.FRONT].setColor("Color", state[Enums.CubeFaces.FRONT][3*(i + 1) + j + 1]);
                        faces.get(Enums.CubeFaces.FRONT).nodes.add(cubes[i + 1][j + 1][k + 1].node);
                        faces.get(Enums.CubeFaces.FRONT).rotationAxis = Face.RotationAxis.Z;
                    }
                    if (j == 1) {
                        materials[9 * Enums.CubeFaces.UP + 3 * (i + 1) + k + 1] = cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.UP];
                        cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.UP].setColor("Color", state[Enums.CubeFaces.UP][3*(i + 1) + k + 1]);
                        faces.get(Enums.CubeFaces.UP).nodes.add(cubes[i + 1][j + 1][k + 1].node);
                        faces.get(Enums.CubeFaces.UP).rotationAxis = Face.RotationAxis.Y;
                    }
                    if (i == 1) {
                        materials[9 * Enums.CubeFaces.RIGHT + 3 * (j + 1) + k + 1] = cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.RIGHT];
                        cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.RIGHT].setColor("Color", state[Enums.CubeFaces.RIGHT][3*(j + 1) + k + 1]);
                        faces.get(Enums.CubeFaces.RIGHT).nodes.add(cubes[i + 1][j + 1][k + 1].node);
                        faces.get(Enums.CubeFaces.RIGHT).rotationAxis = Face.RotationAxis.X;
                    }
                    if (k == -1) {
                        materials[9 * Enums.CubeFaces.BACK + 3 * (j + 1) + i + 1] = cubes[j + 1][i + 1][k + 1].materials[Enums.CubeFaces.BACK];
                        cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.BACK].setColor("Color", state[Enums.CubeFaces.BACK][3*(i + 1) + j + 1]);
                        faces.get(Enums.CubeFaces.BACK).nodes.add(cubes[i + 1][j + 1][k + 1].node);
                        faces.get(Enums.CubeFaces.BACK).rotationAxis = Face.RotationAxis.Z;
                    }
                    if (j == -1) {
                        materials[9 * Enums.CubeFaces.DOWN + 3 * (k + 1) + i + 1] = cubes[k + 1][j + 1][i + 1].materials[Enums.CubeFaces.DOWN];
                        cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.DOWN].setColor("Color", state[Enums.CubeFaces.DOWN][3*(i + 1) + k + 1]);
                        faces.get(Enums.CubeFaces.DOWN).nodes.add(cubes[i + 1][j + 1][k + 1].node);
                        faces.get(Enums.CubeFaces.DOWN).rotationAxis = Face.RotationAxis.Y;
                    }
                    if (i == -1) {
                        materials[9 * Enums.CubeFaces.LEFT + 3 * (k + 1) + j + 1] = cubes[i + 1][k + 1][j + 1].materials[Enums.CubeFaces.LEFT];
                        cubes[i + 1][j + 1][k + 1].materials[Enums.CubeFaces.LEFT].setColor("Color", state[Enums.CubeFaces.LEFT][3*(j + 1) + k + 1]);
                        faces.get(Enums.CubeFaces.LEFT).nodes.add(cubes[i + 1][j + 1][k + 1].node);
                        faces.get(Enums.CubeFaces.LEFT).rotationAxis = Face.RotationAxis.X;
                    }
                    
                    node.attachChild(cubes[i + 1][j + 1][k + 1].node);
                }
            }
        }
    }

    public ColorRGBA[][] rotateFace(int face, boolean clockWise) {
        this.faces.get(face).rotate = true;
        this.faces.get(face).clockWise = clockWise;
        
        ColorRGBA newState[][] = new ColorRGBA[6][9];
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                newState[i][j] = new ColorRGBA(currentState[i][j]);
            }
        }

        switch(face) {
            case Enums.CubeFaces.FRONT:
                if(clockWise)
                {
                    newState[Enums.CubeFaces.DOWN][2].set(currentState[Enums.CubeFaces.RIGHT][2]);
                    newState[Enums.CubeFaces.DOWN][5].set(currentState[Enums.CubeFaces.RIGHT][5]);
                    newState[Enums.CubeFaces.DOWN][8].set(currentState[Enums.CubeFaces.RIGHT][8]);
                    newState[Enums.CubeFaces.RIGHT][2].set(currentState[Enums.CubeFaces.UP][8]);
                    newState[Enums.CubeFaces.RIGHT][5].set(currentState[Enums.CubeFaces.UP][5]);
                    newState[Enums.CubeFaces.RIGHT][8].set(currentState[Enums.CubeFaces.UP][2]);
                    newState[Enums.CubeFaces.UP][2].set(currentState[Enums.CubeFaces.LEFT][2]);
                    newState[Enums.CubeFaces.UP][5].set(currentState[Enums.CubeFaces.LEFT][5]);
                    newState[Enums.CubeFaces.UP][8].set(currentState[Enums.CubeFaces.LEFT][8]);
                    newState[Enums.CubeFaces.LEFT][2].set(currentState[Enums.CubeFaces.DOWN][8]);
                    newState[Enums.CubeFaces.LEFT][5].set(currentState[Enums.CubeFaces.DOWN][5]);
                    newState[Enums.CubeFaces.LEFT][8].set(currentState[Enums.CubeFaces.DOWN][2]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.FRONT][3 * i + j].set(currentState[Enums.CubeFaces.FRONT][6 - 3 * j + i]);
                        }
                    }
                }
                else
                {
                    newState[Enums.CubeFaces.RIGHT][2].set(currentState[Enums.CubeFaces.DOWN][2]);
                    newState[Enums.CubeFaces.RIGHT][5].set(currentState[Enums.CubeFaces.DOWN][5]);
                    newState[Enums.CubeFaces.RIGHT][8].set(currentState[Enums.CubeFaces.DOWN][8]);
                    newState[Enums.CubeFaces.UP][8].set(currentState[Enums.CubeFaces.RIGHT][2]);
                    newState[Enums.CubeFaces.UP][5].set(currentState[Enums.CubeFaces.RIGHT][5]);
                    newState[Enums.CubeFaces.UP][2].set(currentState[Enums.CubeFaces.RIGHT][8]);
                    newState[Enums.CubeFaces.LEFT][2].set(currentState[Enums.CubeFaces.UP][2]);
                    newState[Enums.CubeFaces.LEFT][5].set(currentState[Enums.CubeFaces.UP][5]);
                    newState[Enums.CubeFaces.LEFT][8].set(currentState[Enums.CubeFaces.UP][8]);
                    newState[Enums.CubeFaces.DOWN][8].set(currentState[Enums.CubeFaces.LEFT][2]);
                    newState[Enums.CubeFaces.DOWN][5].set(currentState[Enums.CubeFaces.LEFT][5]);
                    newState[Enums.CubeFaces.DOWN][2].set(currentState[Enums.CubeFaces.LEFT][8]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.FRONT][6 - 3 * j + i].set(currentState[Enums.CubeFaces.FRONT][3 * i + j]);
                        }
                    }
                }
                
                break;
            case Enums.CubeFaces.RIGHT:
                if(clockWise)
                {
                    newState[Enums.CubeFaces.UP][8].set(currentState[Enums.CubeFaces.FRONT][6]);
                    newState[Enums.CubeFaces.UP][7].set(currentState[Enums.CubeFaces.FRONT][7]);
                    newState[Enums.CubeFaces.UP][6].set(currentState[Enums.CubeFaces.FRONT][8]);
                    newState[Enums.CubeFaces.FRONT][6].set(currentState[Enums.CubeFaces.DOWN][6]);
                    newState[Enums.CubeFaces.FRONT][7].set(currentState[Enums.CubeFaces.DOWN][7]);
                    newState[Enums.CubeFaces.FRONT][8].set(currentState[Enums.CubeFaces.DOWN][8]);
                    newState[Enums.CubeFaces.DOWN][8].set(currentState[Enums.CubeFaces.BACK][6]);
                    newState[Enums.CubeFaces.DOWN][7].set(currentState[Enums.CubeFaces.BACK][7]);
                    newState[Enums.CubeFaces.DOWN][6].set(currentState[Enums.CubeFaces.BACK][8]);
                    newState[Enums.CubeFaces.BACK][6].set(currentState[Enums.CubeFaces.UP][6]);
                    newState[Enums.CubeFaces.BACK][7].set(currentState[Enums.CubeFaces.UP][7]);
                    newState[Enums.CubeFaces.BACK][8].set(currentState[Enums.CubeFaces.UP][8]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.RIGHT][3 * i + j].set(currentState[Enums.CubeFaces.RIGHT][6 - 3 * j + i]);
                        }
                    }
                }
                else
                {
                    newState[Enums.CubeFaces.FRONT][6].set(currentState[Enums.CubeFaces.UP][8]);
                    newState[Enums.CubeFaces.FRONT][7].set(currentState[Enums.CubeFaces.UP][7]);
                    newState[Enums.CubeFaces.FRONT][8].set(currentState[Enums.CubeFaces.UP][6]);
                    newState[Enums.CubeFaces.DOWN][6].set(currentState[Enums.CubeFaces.FRONT][6]);
                    newState[Enums.CubeFaces.DOWN][7].set(currentState[Enums.CubeFaces.FRONT][7]);
                    newState[Enums.CubeFaces.DOWN][8].set(currentState[Enums.CubeFaces.FRONT][8]);
                    newState[Enums.CubeFaces.BACK][6].set(currentState[Enums.CubeFaces.DOWN][8]);
                    newState[Enums.CubeFaces.BACK][7].set(currentState[Enums.CubeFaces.DOWN][7]);
                    newState[Enums.CubeFaces.BACK][8].set(currentState[Enums.CubeFaces.DOWN][6]);
                    newState[Enums.CubeFaces.UP][6].set(currentState[Enums.CubeFaces.BACK][6]);
                    newState[Enums.CubeFaces.UP][7].set(currentState[Enums.CubeFaces.BACK][7]);
                    newState[Enums.CubeFaces.UP][8].set(currentState[Enums.CubeFaces.BACK][8]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.RIGHT][6 - 3 * j + i].set(currentState[Enums.CubeFaces.RIGHT][3 * i + j]);
                        }
                    }
                }
                
                break;
            case Enums.CubeFaces.DOWN:
                if(clockWise)
                {
                    newState[Enums.CubeFaces.FRONT][0].set(currentState[Enums.CubeFaces.LEFT][0]);
                    newState[Enums.CubeFaces.FRONT][3].set(currentState[Enums.CubeFaces.LEFT][1]);
                    newState[Enums.CubeFaces.FRONT][6].set(currentState[Enums.CubeFaces.LEFT][2]);
                    newState[Enums.CubeFaces.RIGHT][0].set(currentState[Enums.CubeFaces.FRONT][6]);
                    newState[Enums.CubeFaces.RIGHT][1].set(currentState[Enums.CubeFaces.FRONT][3]);
                    newState[Enums.CubeFaces.RIGHT][2].set(currentState[Enums.CubeFaces.FRONT][0]);
                    newState[Enums.CubeFaces.BACK][0].set(currentState[Enums.CubeFaces.RIGHT][0]);
                    newState[Enums.CubeFaces.BACK][3].set(currentState[Enums.CubeFaces.RIGHT][1]);
                    newState[Enums.CubeFaces.BACK][6].set(currentState[Enums.CubeFaces.RIGHT][2]);
                    newState[Enums.CubeFaces.LEFT][0].set(currentState[Enums.CubeFaces.BACK][6]);
                    newState[Enums.CubeFaces.LEFT][1].set(currentState[Enums.CubeFaces.BACK][3]);
                    newState[Enums.CubeFaces.LEFT][2].set(currentState[Enums.CubeFaces.BACK][0]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.DOWN][3 * i + j].set(currentState[Enums.CubeFaces.DOWN][6 - 3 * j + i]);
                        }
                    }
                }
                else
                {
                    newState[Enums.CubeFaces.LEFT][0].set(currentState[Enums.CubeFaces.FRONT][0]);
                    newState[Enums.CubeFaces.LEFT][1].set(currentState[Enums.CubeFaces.FRONT][3]);
                    newState[Enums.CubeFaces.LEFT][2].set(currentState[Enums.CubeFaces.FRONT][6]);
                    newState[Enums.CubeFaces.FRONT][6].set(currentState[Enums.CubeFaces.RIGHT][0]);
                    newState[Enums.CubeFaces.FRONT][3].set(currentState[Enums.CubeFaces.RIGHT][1]);
                    newState[Enums.CubeFaces.FRONT][0].set(currentState[Enums.CubeFaces.RIGHT][2]);
                    newState[Enums.CubeFaces.RIGHT][0].set(currentState[Enums.CubeFaces.BACK][0]);
                    newState[Enums.CubeFaces.RIGHT][1].set(currentState[Enums.CubeFaces.BACK][3]);
                    newState[Enums.CubeFaces.RIGHT][2].set(currentState[Enums.CubeFaces.BACK][6]);
                    newState[Enums.CubeFaces.BACK][6].set(currentState[Enums.CubeFaces.LEFT][0]);
                    newState[Enums.CubeFaces.BACK][3].set(currentState[Enums.CubeFaces.LEFT][1]);
                    newState[Enums.CubeFaces.BACK][0].set(currentState[Enums.CubeFaces.LEFT][2]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.DOWN][6 - 3 * j + i].set(currentState[Enums.CubeFaces.DOWN][3 * i + j]);
                        }
                    }
                }
                break;
            case Enums.CubeFaces.BACK:
                if(!clockWise)
                {
                    newState[Enums.CubeFaces.RIGHT][6].set(currentState[Enums.CubeFaces.UP][0]);
                    newState[Enums.CubeFaces.RIGHT][3].set(currentState[Enums.CubeFaces.UP][3]);
                    newState[Enums.CubeFaces.RIGHT][0].set(currentState[Enums.CubeFaces.UP][6]);
                    newState[Enums.CubeFaces.UP][0].set(currentState[Enums.CubeFaces.LEFT][0]);
                    newState[Enums.CubeFaces.UP][3].set(currentState[Enums.CubeFaces.LEFT][3]);
                    newState[Enums.CubeFaces.UP][6].set(currentState[Enums.CubeFaces.LEFT][6]);
                    newState[Enums.CubeFaces.LEFT][6].set(currentState[Enums.CubeFaces.DOWN][0]);
                    newState[Enums.CubeFaces.LEFT][3].set(currentState[Enums.CubeFaces.DOWN][3]);
                    newState[Enums.CubeFaces.LEFT][0].set(currentState[Enums.CubeFaces.DOWN][6]);
                    newState[Enums.CubeFaces.DOWN][0].set(currentState[Enums.CubeFaces.RIGHT][0]);
                    newState[Enums.CubeFaces.DOWN][3].set(currentState[Enums.CubeFaces.RIGHT][3]);
                    newState[Enums.CubeFaces.DOWN][6].set(currentState[Enums.CubeFaces.RIGHT][6]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.BACK][3 * i + j].set(currentState[Enums.CubeFaces.BACK][6 - 3 * j + i]);
                        }
                    }
                }
                else
                {
                    newState[Enums.CubeFaces.UP][0].set(currentState[Enums.CubeFaces.RIGHT][6]);
                    newState[Enums.CubeFaces.UP][3].set(currentState[Enums.CubeFaces.RIGHT][3]);
                    newState[Enums.CubeFaces.UP][6].set(currentState[Enums.CubeFaces.RIGHT][0]);
                    newState[Enums.CubeFaces.LEFT][0].set(currentState[Enums.CubeFaces.UP][0]);
                    newState[Enums.CubeFaces.LEFT][3].set(currentState[Enums.CubeFaces.UP][3]);
                    newState[Enums.CubeFaces.LEFT][6].set(currentState[Enums.CubeFaces.UP][6]);
                    newState[Enums.CubeFaces.DOWN][0].set(currentState[Enums.CubeFaces.LEFT][6]);
                    newState[Enums.CubeFaces.DOWN][3].set(currentState[Enums.CubeFaces.LEFT][3]);
                    newState[Enums.CubeFaces.DOWN][6].set(currentState[Enums.CubeFaces.LEFT][0]);
                    newState[Enums.CubeFaces.RIGHT][0].set(currentState[Enums.CubeFaces.DOWN][0]);
                    newState[Enums.CubeFaces.RIGHT][3].set(currentState[Enums.CubeFaces.DOWN][3]);
                    newState[Enums.CubeFaces.RIGHT][6].set(currentState[Enums.CubeFaces.DOWN][6]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.BACK][6 - 3 * j + i].set(currentState[Enums.CubeFaces.BACK][3 * i + j]);
                        }
                    }
                }
                
                break;
            case Enums.CubeFaces.LEFT:
                if(!clockWise)
                {
                    newState[Enums.CubeFaces.UP][2].set(currentState[Enums.CubeFaces.FRONT][0]);
                    newState[Enums.CubeFaces.UP][1].set(currentState[Enums.CubeFaces.FRONT][1]);
                    newState[Enums.CubeFaces.UP][0].set(currentState[Enums.CubeFaces.FRONT][2]);
                    newState[Enums.CubeFaces.FRONT][0].set(currentState[Enums.CubeFaces.DOWN][0]);
                    newState[Enums.CubeFaces.FRONT][1].set(currentState[Enums.CubeFaces.DOWN][1]);
                    newState[Enums.CubeFaces.FRONT][2].set(currentState[Enums.CubeFaces.DOWN][2]);
                    newState[Enums.CubeFaces.DOWN][2].set(currentState[Enums.CubeFaces.BACK][0]);
                    newState[Enums.CubeFaces.DOWN][1].set(currentState[Enums.CubeFaces.BACK][1]);
                    newState[Enums.CubeFaces.DOWN][0].set(currentState[Enums.CubeFaces.BACK][2]);
                    newState[Enums.CubeFaces.BACK][0].set(currentState[Enums.CubeFaces.UP][0]);
                    newState[Enums.CubeFaces.BACK][1].set(currentState[Enums.CubeFaces.UP][1]);
                    newState[Enums.CubeFaces.BACK][2].set(currentState[Enums.CubeFaces.UP][2]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.LEFT][3 * i + j].set(currentState[Enums.CubeFaces.LEFT][6 - 3 * j + i]);
                        }
                    }
                }
                else
                {
                    newState[Enums.CubeFaces.FRONT][0].set(currentState[Enums.CubeFaces.UP][2]);
                    newState[Enums.CubeFaces.FRONT][1].set(currentState[Enums.CubeFaces.UP][1]);
                    newState[Enums.CubeFaces.FRONT][2].set(currentState[Enums.CubeFaces.UP][0]);
                    newState[Enums.CubeFaces.DOWN][0].set(currentState[Enums.CubeFaces.FRONT][0]);
                    newState[Enums.CubeFaces.DOWN][1].set(currentState[Enums.CubeFaces.FRONT][1]);
                    newState[Enums.CubeFaces.DOWN][2].set(currentState[Enums.CubeFaces.FRONT][2]);
                    newState[Enums.CubeFaces.BACK][0].set(currentState[Enums.CubeFaces.DOWN][2]);
                    newState[Enums.CubeFaces.BACK][1].set(currentState[Enums.CubeFaces.DOWN][1]);
                    newState[Enums.CubeFaces.BACK][2].set(currentState[Enums.CubeFaces.DOWN][0]);
                    newState[Enums.CubeFaces.UP][0].set(currentState[Enums.CubeFaces.BACK][0]);
                    newState[Enums.CubeFaces.UP][1].set(currentState[Enums.CubeFaces.BACK][1]);
                    newState[Enums.CubeFaces.UP][2].set(currentState[Enums.CubeFaces.BACK][2]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.LEFT][6 - 3 * j + i].set(currentState[Enums.CubeFaces.LEFT][3 * i + j]);
                        }
                    }
                }
                
                break;
            case Enums.CubeFaces.UP:
                if(!clockWise)
                {
                    newState[Enums.CubeFaces.FRONT][2].set(currentState[Enums.CubeFaces.LEFT][6]);
                    newState[Enums.CubeFaces.FRONT][5].set(currentState[Enums.CubeFaces.LEFT][7]);
                    newState[Enums.CubeFaces.FRONT][8].set(currentState[Enums.CubeFaces.LEFT][8]);
                    newState[Enums.CubeFaces.RIGHT][6].set(currentState[Enums.CubeFaces.FRONT][8]);
                    newState[Enums.CubeFaces.RIGHT][7].set(currentState[Enums.CubeFaces.FRONT][5]);
                    newState[Enums.CubeFaces.RIGHT][8].set(currentState[Enums.CubeFaces.FRONT][2]);
                    newState[Enums.CubeFaces.BACK][2].set(currentState[Enums.CubeFaces.RIGHT][6]);
                    newState[Enums.CubeFaces.BACK][5].set(currentState[Enums.CubeFaces.RIGHT][7]);
                    newState[Enums.CubeFaces.BACK][8].set(currentState[Enums.CubeFaces.RIGHT][8]);
                    newState[Enums.CubeFaces.LEFT][6].set(currentState[Enums.CubeFaces.BACK][8]);
                    newState[Enums.CubeFaces.LEFT][7].set(currentState[Enums.CubeFaces.BACK][5]);
                    newState[Enums.CubeFaces.LEFT][8].set(currentState[Enums.CubeFaces.BACK][2]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.UP][3 * i + j].set(currentState[Enums.CubeFaces.UP][6 - 3 * j + i]);
                        }
                    }
                }
                else
                {
                    newState[Enums.CubeFaces.LEFT][6].set(currentState[Enums.CubeFaces.FRONT][2]);
                    newState[Enums.CubeFaces.LEFT][7].set(currentState[Enums.CubeFaces.FRONT][5]);
                    newState[Enums.CubeFaces.LEFT][8].set(currentState[Enums.CubeFaces.FRONT][8]);
                    newState[Enums.CubeFaces.FRONT][8].set(currentState[Enums.CubeFaces.RIGHT][6]);
                    newState[Enums.CubeFaces.FRONT][5].set(currentState[Enums.CubeFaces.RIGHT][7]);
                    newState[Enums.CubeFaces.FRONT][2].set(currentState[Enums.CubeFaces.RIGHT][8]);
                    newState[Enums.CubeFaces.RIGHT][6].set(currentState[Enums.CubeFaces.BACK][2]);
                    newState[Enums.CubeFaces.RIGHT][7].set(currentState[Enums.CubeFaces.BACK][5]);
                    newState[Enums.CubeFaces.RIGHT][8].set(currentState[Enums.CubeFaces.BACK][8]);
                    newState[Enums.CubeFaces.BACK][8].set(currentState[Enums.CubeFaces.LEFT][6]);
                    newState[Enums.CubeFaces.BACK][5].set(currentState[Enums.CubeFaces.LEFT][7]);
                    newState[Enums.CubeFaces.BACK][2].set(currentState[Enums.CubeFaces.LEFT][8]);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[Enums.CubeFaces.UP][6 - 3 * j + i].set(currentState[Enums.CubeFaces.UP][3 * i + j]);
                        }
                    }
                }
                
                break;
        }
        
        return newState;
    }

}
