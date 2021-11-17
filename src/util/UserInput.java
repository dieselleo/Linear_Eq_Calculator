/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author diese
 */
public class UserInput {
    
    public static String getInput(){
        
        String i="";
        boolean test = false;
        while (test == false){
            try{            
                BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
                i=myReader.readLine();
                if (i.length()>0){
                    test=true;
                } else {
                    System.out.println("Fail on getting user input. Try again!");
                }
            } catch (Exception e){   
                System.out.println("Error");
            }
        }
        
        return i;
    }
    
}
