package mygame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.animation.*;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.scene.Node;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication implements
        AnimEventListener{
    public static Main game;
    private AnimChannel channel;
    private AnimControl control;
    private Geometry geom;
    Spatial bow;
    Spatial bow1,bow2;

    public static void main(String[] args) {
        game = new Main();
        game.start();
    }

    @Override
    public void simpleInitApp() {
        
        bow1 = (Spatial) assetManager.loadModel("Models/Ar_BigBow_Fire_00/Ar_BigBow_Fire_00.j3o");
        bow2 = (Spatial) assetManager.loadModel("Models/Smallbow_Mimiyala/Smallbow_Mimiyala.j3o");
        
        bow = bow1;
        
        flyCam.setMoveSpeed(20f);
        flyCam.setZoomSpeed(20f);
        cam.setLocation(new Vector3f(6.4013605f, 7.488437f, 12.843031f));
        cam.setRotation(new Quaternion(-0.060740203f, 0.93925786f, -0.2398315f, -0.2378785f));

        // sunset light
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -0.7f, 1).normalizeLocal());
        dl.setColor(new ColorRGBA(1f, 1f, 1f, 0.5f));
        rootNode.addLight(dl);
        DirectionalLight dl2 = new DirectionalLight();
        dl2.setColor(new ColorRGBA(1f, 1f, 1f, 0.5f));
        dl2.setDirection(new Vector3f(0.1f, -0.7f, -1).normalizeLocal());
        rootNode.addLight(dl2);
        Spatial Archer = (Spatial) assetManager.loadModel("Models/Archer_finalrig/Archer_finalrig.mesh.j3o");
        float scale = 0.05f;
        Archer.scale(scale, scale, scale);
        
        
        control = Archer.getControl(AnimControl.class);
        control.addListener(this);
        channel = control.createChannel();
        
        for (String anim : control.getAnimationNames())
            System.out.println(anim);
        
        channel.setAnim("Idle Animation");
        geom = (Geometry)((Node)Archer).getChild(0);
        SkeletonControl skeletonControl = Archer.getControl(SkeletonControl.class);
        
        final Node n = skeletonControl.getAttachmentsNode("Torso");
        bow.setLocalTranslation(5.7983f,2.0104f,12.5006f);
        bow.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.PI*(-90f/180), new Vector3f(0,1,0))
        .mult(new Quaternion().fromAngleAxis(FastMath.PI*(45f/180), new Vector3f(1,0,0)))
        .mult(new Quaternion().fromAngleAxis(FastMath.PI*(0f/180), new Vector3f(0,0,1)))
        );
        //bow.setLocalRotation();
        //new Quaternion().fromAngleAxis(FastMath.PI*3f/2, new Vector3f(0,1,0))));
        //bow.setLocalRotation(new Matrix3f());
        //n.set
        n.attachChild(bow);
        //n.setLocalTranslation(5.7983f,99.0104f,12.5006f);
        for (Spatial s : n.getChildren()) {
            System.out.println(s.getName());
            //System.out.println(s.getLocalTransform());
            System.out.println(s.getWorldTransform());
        }
        
        rootNode.attachChild(Archer);
        //rootNode.attachChild(bow);        
        inputManager.addMapping("Swap Weapon",
                new KeyTrigger(KeyInput.KEY_SPACE));
    
        ActionListener actionListener = new ActionListener(){
            public void onAction(String name, boolean pressed, float tpf){
                if (pressed) {
                    n.detachChild(bow);
                    if (Main.game.bow.equals(Main.game.bow1)) {
                        Main.game.bow = Main.game.bow2;
                    } else {
                        Main.game.bow = Main.game.bow1;
                    }
                    bow.setLocalTranslation(5.7983f,2.0104f,12.5006f);
                    bow.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.PI*(-90f/180), new Vector3f(0,1,0))
                    .mult(new Quaternion().fromAngleAxis(FastMath.PI*(45f/180), new Vector3f(1,0,0)))
                    .mult(new Quaternion().fromAngleAxis(FastMath.PI*(0f/180), new Vector3f(0,0,1)))
                    );
                    n.attachChild(bow);
                }
            }
        };
        
        inputManager.addListener(actionListener, "Swap Weapon");
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }
}
