package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
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
    private boolean isRunning = true;

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
            if (isRunning && !keyPressed) {
                switch(name){
                    case "E":
                        
                        break;
                    case "R":
                        
                        break;
                    case "D":
                        
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
