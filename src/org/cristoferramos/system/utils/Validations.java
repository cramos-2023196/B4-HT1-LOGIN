/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.utils;

/**
 *
 * @author informatica
 */
public class Validations {
    
    public Validations(){
    }
    
    public Boolean equalsText(String textOriginal,String textCompare){
        return textOriginal.equals(textCompare);
    }
    
    public Boolean emptyText(String text){
        boolean isEmpty = false;
        
        if(text.isEmpty() || text.isBlank())
            isEmpty = true;
        return isEmpty;
        
    }
    
    public Boolean validateLengtText(String text, int lengtMax){
        return text.length() <= lengtMax;
    }
    
    public Boolean validateEmail(String Email){
        return true;
    }
}
