/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.cristoferramos.system.service.UserService;
import org.cristoferramos.system.service.UserStatus;
import org.cristoferramos.system.utils.AlertInformation;
import org.cristoferramos.system.utils.Validations;
import org.cristoferramos.system.utils.ViewFactory;


/**
 *
 * @author Cristofer Ramos
 */
public class RegisterUserController implements Initializable{
    
    @FXML private TextField txtUser;
    @FXML private TextField txtName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private PasswordField pwdPassword;
    @FXML private PasswordField pwdConfirmPassword;
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserService userService = new UserService();

    @Override
    public void initialize (URL url, ResourceBundle rb){ 
    }
    
    @FXML
    public void onCancel(MouseEvent event){
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
    
    @FXML
    public void onCreateUser(MouseEvent event){
        String user, name, lastName, email, password, confirmPassword;
        user = txtUser.getText().trim();
        name = txtName.getText().trim();
        lastName = txtLastName.getText().trim();
        email = txtEmail.getText().trim();
        password = pwdPassword.getText().trim();
        confirmPassword = pwdConfirmPassword.getText().trim();

        if( validate.emptyText(user) == true ||
            validate.emptyText(name) == true ||
            validate.emptyText(lastName) == true ||
            validate.emptyText(email) == true ||
            validate.emptyText(password) == true ||
            validate.emptyText(confirmPassword) == true){

           alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS VACIOS", 
                   "ERROR DE CAMPO", 
                   "DEJÓ CAMPOS VACIOS DEL FORMULARIO");
            return;
        }

        boolean isValiEmail = validate.validateEmail(email);
        if(isValiEmail == false){
            alertInfo.viewAlert("ERROR", "ERROR EMAIL", "ERROR DE CAMPO", "HAS INGRESADO UN EMAIL INCORRECTO");
            return;
        }

        String msgField = "";
        if(validate.validateLengthText(user, 25) == false){
            msgField = "El campo USUARIO es mayor a 25 caracteres";
        }
        if(validate.validateLengthText(name, 50) == false){     
            msgField = "El campo NOMBRES es mayor a 50 caracteres";
        }
        if(validate.validateLengthText(lastName, 50) == false){
            msgField = "EL campo APELLIDOS es mayor a 50 caracteres";
        }
        if(validate.validateLengthText(email, 50) == false){
            msgField = "El campo EMAIL es mayor a 50 caracters";
        }
        if(validate.validateLengthText(password, 35) == false){
            msgField = "El campo PASSWORD es mayor a 35 caracteres";
        }
        if(msgField.isEmpty() == false){
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPO", "ERROR", msgField);
            return;
        }

        if( validate.equalsText(password, confirmPassword) == false ){
            alertInfo.viewAlert("ERROR", "ERROR DE CONTRASENA",
                            "ERROR", "SUS CONTRASEÑAS NO COINCIDEN");
            return;
        }

        UserStatus status =
        userService.createUser(user, name, lastName, email, password);
        switch(status){
            case UserStatus.ERROR_USER_CREATE->
                System.out.println("Error al crear en el ctrl");
            case UserStatus.USER_CREATED->
                System.out.println("Si se creo el usuario");
            case UserStatus.FIELDS_EMPTY->
                System.out.println("Los campos no esta vacions");
            case UserStatus.VALUE_LENGTH_INVALID->
                System.out.println("Validar logintud de texto");
            default -> System.out.println("ERROR DESCONOCIDO");
        }
    } 
}
