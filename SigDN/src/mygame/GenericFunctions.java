/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;

/**
 *
 * @author Joshua Sigona
 */
public class GenericFunctions {
    /**
     * Specify a model name. Returns the entire path.
     * @return 
     */
    public static String ModelName(String name) {
            StringBuilder sb = new StringBuilder("Models/")
                    .append(name)
                    .append("/")
                    .append(name)
                    .append(".j3o");
            return sb.toString();
    }
    
    public static Spatial LoadModel(AssetManager assetManager, String name) {
        return (Spatial)(assetManager.loadModel(ModelName(name)));
    }
}
