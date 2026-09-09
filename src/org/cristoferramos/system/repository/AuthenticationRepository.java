/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.repository;

import org.cristoferramos.system.model.User;
import org.cristoferramos.system.config.ConexionDB;
import java.sql.*;

/**
 *
 * @author Cristofer Ramos
 */

public class AuthenticationRepository implements AuthenticationInterface {
    
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    public User login(String usernameOrEmail, String password){
        
        // MODIFICADO: Se usa `Users` (con mayúscula) y `user` entre backticks para evitar conflicto con palabra reservada
        String sql = "SELECT * FROM `Users` WHERE (`user` = ? OR email = ?) AND password = ?";
        
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, password);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return new User(
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("lastname"),
                    rs.getString("user")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userExistsByUsername(String username){
        // MODIFICADO: Se usa `Users` (con mayúscula) y `user` entre backticks
        String sql = "SELECT COUNT(*) FROM `Users` WHERE `user` = ?";
        try (Connection conn = conexionDB.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean userExistsByEmail(String email){
        // MODIFICADO: Se usa `Users` (con mayúscula) para coincidir con el nombre real de la tabla
        String sql = "SELECT COUNT(*) FROM `Users` WHERE email = ?";
        try(Connection conn = conexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
