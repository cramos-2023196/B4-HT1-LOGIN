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
    /**
     * Busca al usuario por username O email y valida la contraseña.
     * Retorna el User si las credenciales son correctas, null en caso contrario.
     */
    User login(String usernameOrEmail, String password);
}
