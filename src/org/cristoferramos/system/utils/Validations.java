/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cristoferramos.system.utils;

/**
 *
 * @author Cristofer Ramos
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
    
    public Boolean validateLengthText(String text, int lengtMax){
        return text.length() <= lengtMax;
    }
    
    public Boolean validateEmail(String email){
        
        int dotCount = 0, arrobeCount = 0;
        //VALIDA LA EXISTENCIA DE PUNSTOS CONSECUTIVOS
        for(int index = 0; index < email.length(); index++){
            //System.out.println(email.charAt(index));
            if(email.charAt(index) == '.')
                dotCount++;
            if(dotCount>1)
                return false;
        }
        
        //VALIDA LA EXISTENCIA DE SOLO UN UNICO ARROBA
        for(int index = 0; index < email.length(); index ++){
            if(email.charAt(index) == '@')
                arrobeCount++;
        }
        if(arrobeCount != 1)
            return false;
        return true;
    }
}
