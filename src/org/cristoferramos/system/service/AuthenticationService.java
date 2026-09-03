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
    private UserService userService;
    private Validations validate;

    public AuthenticationService(){
        this.authRepo = new AuthenticationRepository();
        this.userService = new UserService();
        this.validate = new Validations();
    }

    public AuthenticationStatus authenticateUser(String usernameOrEmail, String password){

        if(validate.emptyText(usernameOrEmail) || validate.emptyText(password)){
            return AuthenticationStatus.CREDENTIALS_EMPTY;
        }

        boolean isEmail = usernameOrEmail.contains("@");
        boolean exists;

        if(isEmail){
            exists = userService.existsByEmail(usernameOrEmail);
        }else{
            exists = userService.existsByUsername(usernameOrEmail);
        }

        if(!exists){
            return AuthenticationStatus.NOT_EXIST_USER;
        }

        try{
            User user = authRepo.login(usernameOrEmail, password);
            
            if(user != null){
                
                return AuthenticationStatus.LOGIN_SUCCESS;
            }else{

                return AuthenticationStatus.INVALID_PASSWORD;
            }
        }catch(Exception e){
            e.printStackTrace();
            return AuthenticationStatus.ERROR_LOGIN;
        }
    }
    
    public User getAuthenticatedUser(String usernameOrEmail, String password) {
        return authRepo.login(usernameOrEmail, password);
    }
}
