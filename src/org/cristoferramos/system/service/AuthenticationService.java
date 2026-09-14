/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.service;

import org.cristoferramos.system.model.User;
import org.cristoferramos.system.repository.AuthenticationRepository;
import org.cristoferramos.system.utils.Validations;

/**
 *
 * @author Cristofer Ramos
 */

public class AuthenticationService {
    
    private AuthenticationRepository authRepo;
    private Validations validate;

    public AuthenticationService() {
        this.authRepo = new AuthenticationRepository();
        this.validate = new Validations();
    }

    public AuthenticationStatus authenticateUser(String usernameOrEmail, String password) {
        // 1. Validar campos vacíos
        if (validate.emptyText(usernameOrEmail) || validate.emptyText(password)) {
            return AuthenticationStatus.CREDENTIALS_EMPTY;
        }

        try {
            // 2. UNA SOLA CONSULTA: busca al usuario y valida la contraseña
            User user = authRepo.login(usernameOrEmail, password);
            
            if (user != null) {
                return AuthenticationStatus.LOGIN_SUCCESS;
            } else {
                // Necesitamos distinguir entre "usuario no existe" y "contraseña incorrecta"
                // Hacemos una segunda consulta SOLO para saber si el usuario existe
                boolean exists = userExists(usernameOrEmail);
                if (!exists) {
                    return AuthenticationStatus.NOT_EXIST_USER;
                } else {
                    return AuthenticationStatus.INVALID_PASSWORD;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AuthenticationStatus.ERROR_LOGIN;
        }
    }

    /**
     * Verifica si el usuario existe (por username o email)
     */
    private boolean userExists(String identifier) {
        boolean isEmail = identifier.contains("@");
        UserService userService = new UserService();
        if (isEmail) {
            return userService.existsByEmail(identifier);
        } else {
            return userService.existsByUsername(identifier);
        }
    }

    public User getAuthenticatedUser(String usernameOrEmail, String password) {
        return authRepo.login(usernameOrEmail, password);
    }
}
