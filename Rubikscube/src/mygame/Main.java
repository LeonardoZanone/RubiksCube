package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import mygame.models.Enums;
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

    private boolean isRunning = true;

    Rubik[] cubes = new Rubik[2];
    ColorRGBA[][] currentState = new ColorRGBA[6][9];
    boolean rotating = false;

    @Override
    public void simpleInitApp() {

        flyCam.setEnabled(false);
        initKeys();

//        for (int i = 0; i < 27; i++) {
//            initState[i] = ColorRGBA.Blue;
//        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if(i == Enums.FacesColors.BLUE)
                    currentState[i][j] = ColorRGBA.Blue;
                if(i == Enums.FacesColors.WHITE)
                    currentState[i][j] = ColorRGBA.White;
                if(i == Enums.FacesColors.RED)
                    currentState[i][j] = ColorRGBA.Red;
                if(i == Enums.FacesColors.GREEN)
                    currentState[i][j] = ColorRGBA.Green;
                if(i == Enums.FacesColors.YELLOW)
                    currentState[i][j] = ColorRGBA.Yellow;
                if(i == Enums.FacesColors.ORANGE)
                    currentState[i][j] = ColorRGBA.Orange;
            }
        }
        
        cubes[0] = new Rubik(assetManager, currentState);
        cubes[0].node.scale(0.5f);
//        
//        for (int i = 0; i < 54; i++) {
//            if(cubes[0].materials[i] != null)
//            {
//                cubes[0].materials[i].setColor("Color", ColorRGBA.Cyan);
//                System.out.println("mygame.Main.simpleInitApp() i: " + i);
//            }
//        }
//        cubes[1] = new Rubik(assetManager);
//        cubes[0].node.setLocalTransform(new Transform(new Vector3f(0f, 0f, 0f)));
//        
//        cubes[1].node.setLocalTransform(new Transform(new Vector3f(-3f, 2f, 1.5f)));
//        cubes[1].node.setLocalScale(new Vector3f(0.35f, 0.35f, 0.35f));
//        cubes[1].node.rotate(0f, (float)Math.PI, 0f);
//        rootNode.attachChild(cubes[0].node);
        rootNode.attachChild(cubes[0].node);

//        for (int i = 0; i < 27; i++) {
//            if(i != 13)
//                cubes[0].colors[i].setColor("Color", ColorRGBA.Green);
////            System.out.println("mygame.Main.simpleInitApp() i: " + i + " Color: " + cubes[0].colors[i]);
//        }
//        cubes[0].rotateFace(0, true, 0);
//        cubes[0].rotateFace(0, true, 0);
//        cubes[0].faces[0].rotate(0f, 0f, (float) Math.PI);

//          Cube test = new Cube(assetManager);
//          test.materials[0].setColor("Color", ColorRGBA.Blue);
//          rootNode.attachChild(test.node);
    }

    @Override
    public void simpleUpdate(float tpf) {
        cubes[0].node.rotate(tpf, tpf, tpf);
        for (int i = 0; i < 6; i++) {
            if (cubes[0].faces.get(i).rotate) {
                rotating = cubes[0].faces.get(i).rotateFace(tpf);
                if(!rotating) {
                    rootNode.detachAllChildren();
                    cubes[0] = new Rubik(assetManager, currentState);
                    cubes[0].node.scale(0.5f);
                    rootNode.attachChild(cubes[0].node);
                }
            }
        }
//        if(cubes[0].faces.get(0).rotate)
//        {
//            cubes[0].faces.get(0).rotation += tpf;
//            for(Node n : cubes[0].faces.get(0).nodes)
//            {
//                if(cubes[0].faces.get(0).clockWise)
//                    n.rotate(0f, 0f, tpf);
//                else
//                    n.rotate(0f, 0f, -tpf);
//            }
//            if(cubes[0].faces.get(0).rotation >= Math.PI / 2)
//            {
//                cubes[0].faces.get(0).rotate = false;
//                cubes[0].faces.get(0).rotation = 0;
//                rotating = false;
//            }
//        }
//        else if(cubes[0].faces.get(1).rotate)
//        {
//            cubes[0].faces.get(1).rotation += tpf;
//            for(Node n : cubes[0].faces.get(1).nodes)
//            {
//                n.rotate(0f, tpf, 0f);
//            }
//            if(cubes[0].faces.get(1).rotation >= Math.PI / 2)
//            {
//                cubes[0].faces.get(1).rotate = false;
//                cubes[0].faces.get(1).rotation = 0;
//                rotating = false;
//            }
//        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void initKeys() {
        inputManager.addMapping("E", new KeyTrigger(KeyInput.KEY_E)); //Move a parte de cima para a esquerda
        inputManager.addMapping("R", new KeyTrigger(KeyInput.KEY_R)); //Move a parte de cima para a direita
        inputManager.addMapping("D", new KeyTrigger(KeyInput.KEY_D)); //Move a parte de baixo para a esquerda
        inputManager.addMapping("F", new KeyTrigger(KeyInput.KEY_F)); //Move a parte de baixo para a direita
        inputManager.addMapping("I", new KeyTrigger(KeyInput.KEY_I)); //Move a parte da esquerda para cima
        inputManager.addMapping("K", new KeyTrigger(KeyInput.KEY_K)); //Move a parte da esquerda para baixo
        inputManager.addMapping("O", new KeyTrigger(KeyInput.KEY_O)); //Move a parte da direita para cima
        inputManager.addMapping("L", new KeyTrigger(KeyInput.KEY_L)); //Move a parte da direita para baixo 
        inputManager.addMapping("L", new KeyTrigger(KeyInput.KEY_T)); //Move a parte da frente para direira
        inputManager.addMapping("L", new KeyTrigger(KeyInput.KEY_G)); //Move a parte da frente para esquerda
        inputManager.addMapping("L", new KeyTrigger(KeyInput.KEY_Y)); //Move a parte da traz para direita
        inputManager.addMapping("L", new KeyTrigger(KeyInput.KEY_H)); //Move a parte da traz para esquerda

        inputManager.addMapping("Direita", new KeyTrigger(KeyInput.KEY_RIGHT)); //Rotaciona o cubo para a direita
        inputManager.addMapping("Esquerda", new KeyTrigger(KeyInput.KEY_LEFT)); //Rotaciona o cubo para a esquerda
        inputManager.addMapping("Cima", new KeyTrigger(KeyInput.KEY_UP)); //Rotaciona o cubo para cima
        inputManager.addMapping("Baixo", new KeyTrigger(KeyInput.KEY_DOWN)); //Rotaciona o cubo para baixo

        inputManager.addListener(actionListener, "E", "R", "D", "F", "I", "K", "O", "L", "Direita", "Esquerda", "Cima", "Baixo");
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (isRunning && !keyPressed && !rotating) {
                switch (name) {
                    case "E":
                        currentState = cubes[0].rotateFace(0, true);
                        rotating = true;
                        break;
                    case "R":
                        cubes[0].rotateFace(1, true);
                        rotating = true;
                        break;
                    case "D":
                        currentState = cubes[0].rotateFace(2, true);
                        rotating = true;
                        break;
                    case "F":
                        cubes[0].rotateFace(3, true);
                        rotating = true;
                        break;
                    case "I":
                        cubes[0].rotateFace(4, true);
                        rotating = true;
                        break;
                    case "K":
                        cubes[0].rotateFace(5, true);
                        rotating = true;
                        break;
                    case "O":
                        break;
                    case "L":

                        break;
                    case "Direita":

                        break;
                    case "Esquerda":

                        break;
                    case "Cima":

                        break;
                    case "Baixo":

                        break;
                }
            }
        }
    };

}
