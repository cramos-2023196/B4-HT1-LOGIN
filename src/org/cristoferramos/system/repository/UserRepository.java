/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.cristoferramos.system.config.ConexionDB;
import org.cristoferramos.system.model.User;

/**
 *
 * @author Cristofer Ramos
 */

public class UserRepository implements UserInterface {
    
    private CallableStatement callSP;

    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();
    
    public UserRepository(){
    }
    
    @Override
    public void create(User user){
        try{
            callSP = conexionDB.getConnection().prepareCall("{call sp_create_users(?,?,?,?,?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            
            callSP.execute();
            
            callSP.close(); //Liberar los recursos utilizados
            
        }catch(SQLException e){
            System.out.println("Error al crear el usuario");
            //System.out.println();
            e.printStackTrace();
        }
    }

    public User findByUsername(String username){
        // MODIFICADO: Se usa `Users` (con mayúscula) y `user` entre backticks
        String sql = "SELECT * FROM `Users` WHERE `user` = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
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


    public User findByEmail(String email){
        // MODIFICADO: Se usa `Users` (con mayúscula) para coincidir con el nombre real de la tabla
        String sql = "SELECT * FROM `Users` WHERE email = ?";
        try(Connection conn = conexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, email);
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
}
