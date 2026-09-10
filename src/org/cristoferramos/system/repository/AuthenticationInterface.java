/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.repository;

import org.cristoferramos.system.model.User;

/**
 *
 * @author Cristofer Ramos
 */

public interface AuthenticationInterface{
    User login(String usernameOrEmail, String password);
    boolean userExistsByUsername(String username);
    boolean userExistsByEmail(String email);
}
