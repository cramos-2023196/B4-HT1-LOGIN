/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import org.cristoferramos.system.ClasePrincipal;

/**
 *
 * @author informatica
 */
public class ViewFactory {
    private final String PATH_VIEW = "/org/cristoferramos/system/view/";
    
    public Scene loadFileFXML(String nameFXML, int width, int height){
        String pathOffile = PATH_VIEW + nameFXML;
        try{
            //FXMLLoader
            FXMLLoader loaderFXML = new FXMLLoader();
            //Leer la URL del archvo
            //Lamar al archivo amin
            URL urlFile = ClasePrincipal.class.getResource(pathOffile);
            loaderFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loaderFXML.setLocation(urlFile);
            
            return new Scene(loaderFXML.load(), width, height);
                    
            
        }catch(IOException e){
            throw new UncheckedIOException(e);
            
        }
        
    }
    
    public void loadScene(String nameFXML){
        Scene scene = null;
        try{
            switch (nameFXML){
                case "login" ->{
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("Login de Usuario");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(false);
                    scene = loadFileFXML("LoginView.fxml", 400, 500);
                }
                case "registre"->{
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("REGISTRO DE USUARIO");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(false);
                    scene = loadFileFXML("RegistreView.fxml", 400, 500);
                }
                default -> scene = loadFileFXML("LoginView.fxml", 300, 400);
            }
            SceneManager.getInstanciaSceneManager().changeScene(scene);
        }catch(NullPointerException objetoNulo){
            //Alert
            System.out.print("error load scene");
        }
    
    }
    public void viewLogin(){
        loadScene("login");
    }
    public void viewRegister(){
        loadScene("registre");
    
    }
}
