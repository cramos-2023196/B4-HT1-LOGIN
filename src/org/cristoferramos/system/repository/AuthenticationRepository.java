/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.repository;

import org.cristoferramos.system.model.User;
import org.cristoferramos.system.config.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cristofer Ramos
 */

public class AuthenticationRepository implements AuthenticationInterface {
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    @Override
    public User login(String usernameOrEmail, String password) {
        // IMPORTANTE: NO usamos try-with-resources con la Connection
        // porque cerraría la conexión singleton y las siguientes consultas fallarían.
        String sql = "SELECT id_user, name, lastname, email, user, password FROM `Users` WHERE `user` = ? OR `email` = ?";
        
        try {
            PreparedStatement stmt = conexionDB.getConnection().prepareStatement(sql);
            stmt.setString(1, usernameOrEmail.trim());
            stmt.setString(2, usernameOrEmail.trim());
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User userFound = new User(
                    rs.getString("id_user"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("lastname"),
                    rs.getString("password"),
                    rs.getString("user")
                );
                
                // Cerramos SOLO el ResultSet y el Statement, NO la Connection
                rs.close();
                stmt.close();
                
                // Validamos la contraseña en Java (como lo hace Jefferson)
                if (userFound.getPassword().equals(password)) {
                    return userFound;
                }
            } else {
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al hacer login: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Usuario no existe o contraseña incorrecta
    }
}
