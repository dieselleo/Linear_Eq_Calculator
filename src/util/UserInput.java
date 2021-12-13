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
    
    public static String getInput() {
        
        String input="";
        try{            
            BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
            input = myReader.readLine();
            } catch (Exception e){  
                System.out.println("Error");
            }
        
        return input;
    }
    
    public static String getText(){
        
        String text="";
        boolean t=false;
        while (t==false){
            try{            
                BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
                text=myReader.readLine();
                if ((!text.matches("^-?\\d*\\.{0,1}\\d+$")) && (text.length()>1)){
                    t=true;
                } else {
                System.out.println("You did not insert a text");
                }
            } catch (Exception e){  
                System.out.println("Error");
            } 
        }
        return text;
    }
    
    public static double getNumber (){
        /*
        * Author: Leonardo Diesel
        */
        double number=0.0;
        while (number==0.0){
            try{            
                BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
                number=Double.parseDouble(myReader.readLine());
            } catch (Exception e){   
                System.out.println("You did not insert a number!");
            }
        }
        return number;
    }  
    
    public static Integer getOption (){
        /*
        * Author: Leonardo Diesel
        */
        Integer opt=-1;
        while (opt==-1){
            try{            
                BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
                opt=Integer.parseInt(myReader.readLine());
            } catch (Exception e){   
                System.out.println("You did not insert a number!");
            }
        }
        return opt;
    } 
    
}
