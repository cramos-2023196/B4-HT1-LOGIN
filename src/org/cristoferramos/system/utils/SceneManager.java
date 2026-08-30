/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.utils;

import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Cristofer Ramos
 */

public class SceneManager {
    
    private static SceneManager instanciaSceneManager;
    private Stage stagePrincipal;
 
    private SceneManager(){
 
    }
 
    public static SceneManager getInstanciaSceneManager(){
 
        if(instanciaSceneManager == null)
            instanciaSceneManager = new SceneManager();
        return instanciaSceneManager;
 
    }
 
    /**
     * Este metodo permite recibir un parametro de tipo Scene
     * y es la escena que se mostrará en el Stage
     * @param scene Escena a mostrar en el Stage
     */
 
    public void changeScene(Scene scene){
        
        try{
            stagePrincipal.setScene(scene);
            stagePrincipal.sizeToScene();
            stagePrincipal.show();
        
        }catch(NullPointerException objetoNulo){
 
        }
    }
    public Stage getStagePrincipal() {
        return stagePrincipal;
 
    }
    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
 
    }
}
