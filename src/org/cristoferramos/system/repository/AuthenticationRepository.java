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
    public User login(String usernameOrEmail, String password){
        String sql = "SELECT id_user, name, lastname, email, user, password FROM Users WHERE (BINARY user = ? OR BINARY email = ?) AND BINARY password = ?";
        
        try{
            PreparedStatement stmt = conexionDB.getConnection().prepareStatement(sql);
            
            stmt.setString(1, usernameOrEmail.trim());
            stmt.setString(2, usernameOrEmail.trim());
            stmt.setString(3, password); 
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                User userFound = new User(
                    rs.getString("id_user"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("lastname"),
                    rs.getString("password"),
                    rs.getString("user")
                );
                
                rs.close();
                stmt.close();
                
                return userFound; // Retorna el usuario autenticado
            } else {
                rs.close();
                stmt.close();
            }
        } catch (SQLException e){
            System.out.println("Error al hacer login: " + e.getMessage());
            e.printStackTrace();
        }
        return null; 
    }
}
