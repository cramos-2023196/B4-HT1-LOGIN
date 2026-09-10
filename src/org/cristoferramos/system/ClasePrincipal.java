/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.cristoferramos.system;

import javafx.application.Application;
import javafx.stage.Stage;
import org.cristoferramos.system.utils.SceneManager;
import org.cristoferramos.system.utils.ViewFactory;

/**
 *
 * @author Cristofer Ramos
 */

public class ClasePrincipal extends Application{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args){
        // TODO code application logic here
        launch(args);
    }
    
    @Override
    public void start (Stage stageRoot){
        SceneManager.getInstanciaSceneManager().setStagePrincipal(stageRoot);
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.viewLogin();
    }
}
