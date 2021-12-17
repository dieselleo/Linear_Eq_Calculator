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
    
    private String equation;
    private Double x,y,z,c;
    
    public Equation (Double x, Double y, Double z, Double c){

        this.x = x;
        this.y = y;
        this.z = z;
        this.c = c;
        
        this.equation=createEquation();        
    }
    
    public Equation (Double x, Double y, Double c){

        this.x = x;
        this.y = y;
        this.z = 0.0;
        this.c = c;
        
        this.equation=createEquation();        
    }
    
    private String createEquation(){
        
        String equation="";
        
        equation = equation = (this.x+"x ");
        
        if(this.y>0){
            equation = equation.concat("+ "+this.y+"y ");
        } else {
            equation = equation.concat(this.y+"y ");
        }
        if (this.z>0){
            equation = equation.concat("+ "+this.z+"z ");
        } else if (this.z<0){
            equation = equation.concat(this.z+"z ");
        }
        
        equation = equation.concat("= "+this.c);
        
        return equation;        
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
    
    public Double getZ() {
        return z;
    }

    public Double getC() {
        return c;
    }

    @Override
    public String getEquation() {
        return equation;
    }
    
    
    
}
