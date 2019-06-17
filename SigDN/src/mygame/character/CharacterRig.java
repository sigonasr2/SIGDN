/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.character;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.SkeletonControl;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import mygame.GenericFunctions;
import mygame.Main;

/**
 *
 * @author Joshua Sigona
 */
public class CharacterRig extends Main{
    Spatial character;
    String[] topbones; //Controlled only by top motion. Anything not specified here is bottom control.
    AnimChannel top_channel,bot_channel;
    AnimControl control;
    SkeletonControl skeleton;
    
    Node primary_hand,secondary_hand,torso;
    
    Spatial weapon;
    
    boolean combat_stance=false;
    
    //Combat stance
    
    CharacterRig(String model,String... topbones) {
        this.character = GenericFunctions.LoadModel(assetManager,model);
        this.topbones = topbones;
        
        control = character.getControl(AnimControl.class);
        control.addListener(Main.game);
        bot_channel = control.createChannel();
        top_channel = control.createChannel();
        
        SkeletonControl skeleton = character.getControl(SkeletonControl.class);
        
        primary_hand = skeleton.getAttachmentsNode("Left Wrist");
        secondary_hand = skeleton.getAttachmentsNode("Right Wrist");
        torso = skeleton.getAttachmentsNode("Torso");
    }
    
    public void setWeapon(Spatial weapon) {
        this.weapon=weapon;
    }
    
    
    public void update(float tpf) {
        
    }
    
}
