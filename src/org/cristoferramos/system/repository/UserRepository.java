/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.repository;

import java.sql.CallableStatement;
import org.cristoferramos.system.config.ConexionDB;
import org.cristoferramos.system.model.User;
import java.sql.SQLException;

/**
 *
 * @author Cristofer Ramos
 */
public class UserRepository 
        implements UserInterface{
    
    //CallableStatement
    private CallableStatement callSP;
    //ConexionDB
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
            System.out.println();
            e.printStackTrace();
        
        }
        
    }
    
}
