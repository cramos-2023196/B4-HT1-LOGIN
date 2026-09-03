/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.cristoferramos.system.utils.ViewFactory;

/**
 *
 * @author Cristofer Ramos
 */

public class MainMenuController implements Initializable {
    
    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnInicio;
    
    @FXML
    private Button btnPerfil;
    
    @FXML
    private Button btnConfiguracion;

    private ViewFactory viewFactory;

    public MainMenuController(){
        this.viewFactory = new ViewFactory();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("Menú Principal cargado correctamente.");
    }

    @FXML
    public void navegar(MouseEvent event){
        Button btnPresionado = (Button) event.getSource();
        System.out.println("Navegando a: " + btnPresionado.getText());
    }

    @FXML
    public void onCerrarSesion(MouseEvent event){
        System.out.println("Cerrando sesión y volviendo al Login...");
        
        // Lógica para destruir la sesión actual (si hubiera variables estáticas, limpiarlas aquí)
        // Redirigir a la vista de Login
        viewFactory.loadScene("LoginView");
    }
}
