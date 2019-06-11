/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.models;

import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author Leonardo
 */
public class Face {

    private final int identifier;
    public ArrayList<Node> nodes = new ArrayList<>();
    public float rotation = 0;
    public boolean clockWise = false;
    public boolean rotate = false;
    public int rotationAxis;
    
    public Face(int identifier)
    {
        this.identifier = identifier;
    }
    
    public int getIdentifier(){
        return this.identifier;
    }

    public class RotationAxis {

        public static final int X = 0;
        public static final int Y = 1;
        public static final int Z = 2;
    }

    public boolean rotateFace(float tpf) {
        Node aux = new Node();
        for (Node n : nodes) {
            switch(identifier) {
                case Enums.CubeFaces.FRONT:
                    if(clockWise)
                        n.rotate(0f, 0f, -tpf);
                    else
                        n.rotate(0f, 0f, tpf);
                    break;
                case Enums.CubeFaces.BACK:
                    if(clockWise)
                        n.rotate(0f, 0f, tpf);
                    else
                        n.rotate(0f, 0f, -tpf);
                    break;
                case Enums.CubeFaces.RIGHT:
                    if(clockWise)
                        n.rotate(-tpf, 0f, 0f);
                    else
                        n.rotate(tpf, 0f, 0f);
                    break;
                case Enums.CubeFaces.LEFT:
                    if(clockWise)
                        n.rotate(tpf, 0f, 0f);
                    else
                        n.rotate(-tpf, 0f, 0f);
                    break;
                case Enums.CubeFaces.UP:
                    if(clockWise)
                        n.rotate(0f, -tpf, 0f);
                    else
                        n.rotate(0f, tpf, 0f);
                    break;
                case Enums.CubeFaces.DOWN:
                    if(clockWise)
                        n.rotate(0f, tpf, 0f);
                    else
                        n.rotate(0f, -tpf, 0f);
                    break;
            }
        }
        if (rotation >= Math.PI / 2) {
            rotate = false;
            rotation = 0;
            return false;
        }
        rotation += tpf;
        return true;
    }
}
