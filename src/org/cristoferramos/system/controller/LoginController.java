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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.cristoferramos.system.service.AuthenticationService;
import org.cristoferramos.system.service.AuthenticationStatus;
import org.cristoferramos.system.utils.AlertInformation;
import org.cristoferramos.system.utils.ViewFactory;
        

/**
 *
 * @author Cristofer Ramos
 */

public class LoginController implements Initializable {
    
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField pwdContrasena;

    @FXML
    private Button btnLogIn;
    
    private AuthenticationService authService;
    private AlertInformation alertInfo;
    private ViewFactory viewFactory;
    
    public LoginController(){
        this.authService = new AuthenticationService();
        this.alertInfo = new AlertInformation();
        this.viewFactory = new ViewFactory();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    
    @FXML
    public void onLogin(MouseEvent event){
        String username = txtUsername.getText();
        String password = pwdContrasena.getText();
        
        AuthenticationStatus status = authService.authenticateUser(username, password);
        
        switch(status){
            case CREDENTIALS_EMPTY:
                alertInfo.viewAlert("ADVERTENCIA", "Campos Vacíos", 
                        "Error de Validación", 
                        "Campos de las credenciales vacíos");
                break;
                
            case NOT_EXIST_USER:
                alertInfo.viewAlert("ERROR", "Usuario No Existe", 
                        "Error de Autenticación", 
                        "El nombre de usuario no es válido");
                break;
                
            case INVALID_PASSWORD:
                alertInfo.viewAlert("ERROR", "Contraseña Incorrecta", 
                        "Error de Autenticación", 
                        "Contraseña incorrecta");
                pwdContrasena.clear(); // Limpiar campo contraseña
                break;
                
            case LOGIN_SUCCESS:

                viewFactory.loadScene("MainMenuView");
                break;
                
            case ERROR_LOGIN:
                alertInfo.viewAlert("ERROR", "Error del Sistema", 
                        "Error de Base de Datos", 
                        "Ocurrió un error al intentar iniciar sesión.");
                break;
        }
    }
    
    @FXML
    public void onRegister(MouseEvent event){
        viewFactory.viewRegister();
    } 
}
