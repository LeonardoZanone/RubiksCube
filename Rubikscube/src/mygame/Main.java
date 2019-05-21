package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Matrix3f;
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
    
    private boolean isRunning = true;
    
    Rubik[] cubes = new Rubik[2];
    boolean rotating = false;

    @Override
    public void simpleInitApp() {
        
//        flyCam.setEnabled(false);
        initKeys();
        
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
//        cubes[0].rotateFace(0, true, 0);
//        cubes[0].rotateFace(0, true, 0);
//        cubes[0].faces[0].rotate(0f, 0f, (float) Math.PI);

//          Cube test = new Cube(assetManager);
//          test.materials[0].setColor("Color", ColorRGBA.Blue);
//          rootNode.attachChild(test.node);
    }

 
    @Override
    public void simpleUpdate(float tpf) {
        if(cubes[0].faces.get(0).rotate)
        {
            cubes[0].faces.get(0).rotation += tpf;
            for(Node n : cubes[0].faces.get(0).nodes)
            {
                if(cubes[0].faces.get(0).clockWise)
                    n.rotate(0f, 0f, tpf);
                else
                    n.rotate(0f, 0f, -tpf);
            }
            if(cubes[0].faces.get(0).rotation >= Math.PI / 2)
            {
                cubes[0].faces.get(0).rotate = false;
                cubes[0].faces.get(0).rotation = 0;
                rotating = false;
            }
        }
        else if(cubes[0].faces.get(1).rotate)
        {
            cubes[0].faces.get(1).rotation += tpf;
            for(Node n : cubes[0].faces.get(1).nodes)
            {
                n.rotate(0f, tpf, 0f);
            }
            if(cubes[0].faces.get(1).rotation >= Math.PI / 2)
            {
                cubes[0].faces.get(1).rotate = false;
                cubes[0].faces.get(1).rotation = 0;
                rotating = false;
            }
        }
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
                switch(name){
                    case "E":
                        cubes[0].rotateFace(0, true);
                        rotating = true;
                        break;
                    case "R":
                        cubes[0].rotateFace(1, true);
                        rotating = true;
                        break;
                    case "D":
                        cubes[0].rotateFace(2, true);
                        rotating = true;
                        break;
                    case "F":
                        
                        break;
                    case "I":
                        
                        break;
                    case "K":
                        
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
