/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.service;

import org.cristoferramos.system.model.User;
import org.cristoferramos.system.repository.UserRepository;
import org.cristoferramos.system.utils.AlertInformation;
import org.cristoferramos.system.utils.Validations;

/**
 *
 * @author Cristofer Ramos
 */

public class UserService {
    
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserRepository userRepo = new UserRepository();
    
    public UserStatus createUser(String user, String name, String lastName, String email, String password){
          if(validate.emptyText(user) == true ||
            validate.emptyText(name) == true ||
            validate.emptyText(lastName) == true ||
            validate.emptyText(email) == true ||
            validate.emptyText(password) == true){
            
           alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS VACIOS", 
                   "ERROR DE CAMPO", 
                   "DEJÓ CAMPOS VACIOS DEL FORMULARIO");
           return UserStatus.FIELDS_EMPTY;
        }
          try{
              User newUser = new User(password, email, name, lastName, user);
              userRepo.create(newUser);
              return UserStatus.USER_CREATED;
          }catch(Exception e){
              return UserStatus.ERROR_USER_CREATE;
          }
    }
    
    public boolean existsByUsername(String username){
        try{
            return userRepo.findByUsername(username) != null;
        }catch (Exception e){
            return false;
        }
    }
    
    public boolean existsByEmail(String email){
        try{
            return userRepo.findByEmail(email) != null;
        }catch (Exception e){
            return false;
        }
    }
}
