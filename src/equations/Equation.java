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
    private Integer x,y,z,c;
    
    public Equation (Integer x, Integer y, Integer z, Integer c){

        this.x = x;
        this.y = y;
        this.z = z;
        this.c = c;
        
        this.equation=createEquation();        
    }
    
    public Equation (Integer x, Integer y, Integer c){

        this.x = x;
        this.y = y;
        this.z = 0;
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
    
//    private boolean isLeft(char var){
//        
//        char[] charEq = equationToChar();
//        
//        boolean isLeft=false;
//        
//        
//        
//        int equalsPos = new String(charEq).indexOf("=");
//        int varPos = new String(charEq).indexOf(var);
//        
//        if (varPos<equalsPos){
//            isLeft = true;
//        }
//        
//        return isLeft;
//        
//    }
//    
//    private char[] equationToChar(){
//        
//        char[] charEq = new char[this.equation.length()];
//        for(int i=0; i<this.equation.length(); i++){
//            charEq[i] = this.equation.charAt(i);
//        }
//        
//        return charEq;
//        
//    }
//    
//    private void makeXYZ(String eq){
//        
//        String equation;
//        
//        equation = eq.replace('x',',');
//        equation = equation.replace('y',',');
//        equation = equation.replace('z',',');
//        equation = equation.replace('=',',');
//        
//        String[] helper = new String[5];
//        helper = equation.split(",");
//        
//        this.x = Integer.parseInt(helper[0]);
//        this.y = Integer.parseInt(helper[1]);
//        this.z = Integer.parseInt(helper[2]);
//        this.n = Integer.parseInt(helper[4]);
//        
//    }
//    
//    private String addComas(String eq){
//        
//        String withComas="";
//        
//        int xPosition = eq.indexOf('x')+1;
//        withComas=eq.substring(0,xPosition)+","+eq.substring(xPosition);
//        int yPosition = withComas.indexOf('y')+1;
//        withComas=withComas.substring(0,yPosition)+","+withComas.substring(yPosition);
////        int zPosition = withComas.indexOf('z')+1;
////        withComas=withComas.substring(0,zPosition)+","+withComas.substring(zPosition);
//        for(int i : findZ(withComas)){
//            withComas = withComas.substring(0,i+1)+","+withComas.substring(i);
//        }
//        int equalsPosition = withComas.indexOf('=')+1;
//        withComas=withComas.substring(0,equalsPosition)+","+withComas.substring(equalsPosition);
//        
//        
//        
//        return withComas;
//    }
//    
//    public String getEquation (){
//        
//        return this.equation;
//        
//    }
//    
//    private int[] findZ(String eq){
//        
//        int[] zPos = new int[(eq.length() - eq.replaceAll("z","").length())];
//        int lastPos = 0;
//        for (int i=0; i<zPos.length; i++){
//            zPos[i] = eq.indexOf("z",lastPos);
//            lastPos = (zPos[i]+1);
//        }
//        
//        return zPos;
//    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
    
    public Integer getZ() {
        return z;
    }

    public Integer getC() {
        return c;
    }

    @Override
    public String getEquation() {
        return equation;
    }
    
    
    
}
