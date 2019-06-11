package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
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

        rootNode.attachChild(cubes[0].node);
    }

    @Override
    public void simpleUpdate(float tpf) {
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

    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void initKeys() {
        inputManager.addMapping("E", new KeyTrigger(KeyInput.KEY_E)); //Move a parte de cima no sentido horário
        inputManager.addMapping("R", new KeyTrigger(KeyInput.KEY_R)); //Move a parte de cima no sentido anti-horário
        inputManager.addMapping("D", new KeyTrigger(KeyInput.KEY_D)); //Move a parte de baixo no sentido horário
        inputManager.addMapping("F", new KeyTrigger(KeyInput.KEY_F)); //Move a parte de baixo no sentido anti-horário
        inputManager.addMapping("I", new KeyTrigger(KeyInput.KEY_I)); //Move a parte da esquerda no sentido horário
        inputManager.addMapping("K", new KeyTrigger(KeyInput.KEY_K)); //Move a parte da esquerda no sentido anti-horário
        inputManager.addMapping("O", new KeyTrigger(KeyInput.KEY_O)); //Move a parte da direita no sentido horário
        inputManager.addMapping("L", new KeyTrigger(KeyInput.KEY_L)); //Move a parte da direita no sentido anti-horário
        inputManager.addMapping("T", new KeyTrigger(KeyInput.KEY_T)); //Move a parte da frente no sentido horário
        inputManager.addMapping("G", new KeyTrigger(KeyInput.KEY_G)); //Move a parte da frente no sentido anti-horário
        inputManager.addMapping("Y", new KeyTrigger(KeyInput.KEY_Y)); //Move a parte da traz no sentido horário
        inputManager.addMapping("H", new KeyTrigger(KeyInput.KEY_H)); //Move a parte da traz no sentido anti-horário

        inputManager.addMapping("Direita", new KeyTrigger(KeyInput.KEY_RIGHT)); //Rotaciona o cubo para a direita
        inputManager.addMapping("Esquerda", new KeyTrigger(KeyInput.KEY_LEFT)); //Rotaciona o cubo para a esquerda
        inputManager.addMapping("Cima", new KeyTrigger(KeyInput.KEY_UP)); //Rotaciona o cubo para cima
        inputManager.addMapping("Baixo", new KeyTrigger(KeyInput.KEY_DOWN)); //Rotaciona o cubo para baixo

        inputManager.addListener(actionListener, "E", "R", "D", "F", "I", "K", "O", "L", "T", "G", "Y", "H");
        inputManager.addListener(analogListener, "Direita", "Esquerda", "Cima", "Baixo");
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (isRunning && !keyPressed && !rotating) {
                switch (name) {
                    case "E":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.UP, true);
                        rotating = true;
                        break;
                    case "R":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.UP, false);
                        rotating = true;
                        break;
                    case "D":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.DOWN, true);
                        rotating = true;
                        break;
                    case "F":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.DOWN, false);
                        rotating = true;
                        break;
                    case "I":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.LEFT, true);
                        rotating = true;
                        break;
                    case "K":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.LEFT, false);
                        rotating = true;
                        break;
                    case "O":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.RIGHT, true);
                        rotating = true;
                        break;
                    case "L":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.RIGHT, false);
                        rotating = true;
                        break;
                    case "T":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.FRONT, true);
                        rotating = true;
                        break;
                    case "G":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.FRONT, false);
                        rotating = true;
                        break;
                    case "Y":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.BACK, true);
                        rotating = true;
                        break;
                    case "H":
                        currentState = cubes[0].rotateFace(Enums.CubeFaces.BACK, false);
                        rotating = true;
                        break;
                }
            }
        }
    };
    
    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
            
            switch(name) {
                case "Direita":
                    cubes[0].node.rotate(0, tpf, 0);
                    break;
                case "Esquerda":
                    cubes[0].node.rotate(0, -tpf, 0);
                    break;
                case "Cima":
                    cubes[0].node.rotate(-tpf, 0, 0);
                    break;
                case "Baixo":
                    cubes[0].node.rotate(tpf, 0, 0);
                    break;
            }

        }
    };

}
