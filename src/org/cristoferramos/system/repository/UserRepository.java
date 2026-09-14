/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.repository;

import java.sql.CallableStatement;
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

    public UserRepository() {
    }

    @Override
    public void create(User user) {
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_create_users(?,?,?,?,?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            callSP.execute();
            callSP.close();
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario");
            e.printStackTrace();
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT id_user, name, lastname, email, user, password FROM `Users` WHERE `user` = ?";
        try {
            PreparedStatement stmt = conexionDB.getConnection().prepareStatement(sql);
            stmt.setString(1, username.trim());
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
                rs.close();
                stmt.close();
                return userFound;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findByEmail(String email) {
        String sql = "SELECT id_user, name, lastname, email, user, password FROM `Users` WHERE `email` = ?";
        try {
            PreparedStatement stmt = conexionDB.getConnection().prepareStatement(sql);
            stmt.setString(1, email.trim());
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
                rs.close();
                stmt.close();
                return userFound;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
