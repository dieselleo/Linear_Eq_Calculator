/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equations;

/**
 *
 * @author diese
 */
public class Equation implements EquationInterface{
    
    private String eq;
    private int x,y,z,n;
    
    public Equation (String eq){

        this.eq = eq;
        
    }
    
    public String getEquation (String eq){
        
        return this.eq;
        
    }
    
}
