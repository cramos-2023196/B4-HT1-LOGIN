/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.model;

/**
 *
 * @author Cristofer Ramos
 */

public class User {
    private String idUser;
    private String email;
    private String name;
    private String lastname;
    private String password;
    private String user;
    
    //Contructor vacio
    public User(){
    }
    
    public User(String idUser, String email, String name, String lastname, String password, String user){
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.user = user;
    }
    
    public User(String password, String email, String name, String lastname,String user){
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.user = user;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
