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
    private ViewFactory viewFactory = new ViewFactory();

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    @FXML
    public void onCancel(MouseEvent event){
        viewFactory.viewLogin();
    }

    @FXML
    public void onCreateUser(MouseEvent event){
        String user = txtUser.getText().trim();
        String name = txtName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = pwdPassword.getText().trim();
        String confirmPassword = pwdConfirmPassword.getText().trim();

        if(validate.emptyText(user) || validate.emptyText(name) ||
           validate.emptyText(lastName) || validate.emptyText(email) ||
           validate.emptyText(password) || validate.emptyText(confirmPassword)) {
           alertInfo.viewAlert("ERROR", "Campos Vacíos", "Error de Validación", "Por favor complete todos los campos.");
           return;
        }

        if(!validate.validateEmail(email)){
           alertInfo.viewAlert("ERROR", "Email Inválido", "Error de Campo", "Ha ingresado un correo electrónico con formato incorrecto.");
           return;
        }

        String msgField = "";
        if(!validate.validateLengthText(user, 25)) msgField = "El usuario excede 25 caracteres.";
        if(!validate.validateLengthText(name, 50)) msgField = "El nombre excede 50 caracteres.";
        if(!validate.validateLengthText(lastName, 50)) msgField = "El apellido excede 50 caracteres.";
        if(!validate.validateLengthText(email, 50)) msgField = "El correo excede 50 caracteres.";
        if(!validate.validateLengthText(password, 35)) msgField = "La contraseña excede 35 caracteres.";

        if(!msgField.isEmpty()){
           alertInfo.viewAlert("ERROR", "Longitud Inválida", "Error de Campo", msgField);
           return;
        }

        if(!validate.equalsText(password, confirmPassword)){
           alertInfo.viewAlert("ERROR", "Contraseñas no coinciden", "Error de Contraseña", "Las contraseñas ingresadas no coinciden.");
           return;
        }

        UserStatus status = userService.createUser(user, name, lastName, email, password);
        
        switch(status){
            case USER_CREATED -> {
                alertInfo.viewAlert("INFO", "Usuario Creado", "Éxito", "El usuario ha sido registrado correctamente.");
                viewFactory.viewLogin(); // Regresa al Login tras la creación exitosa
            }
            case ERROR_USER_CREATE -> alertInfo.viewAlert("ERROR", "Error de Creación", "Error", "No se pudo registrar el usuario en la base de datos.");
            default -> alertInfo.viewAlert("ERROR", "Error Desconocido", "Error", "Ocurrió un error inesperado.");
        }
    }
}
