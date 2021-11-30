/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diese
 */
public class Calculator implements CalculatorInterface{
    
    List<EquationInterface> equations;
    double[][] matrixA;
    double[][] matrixAI;
    double detA;
    double[][] matrixX;
    double[][] matrixB;
    
    public Calculator(Equation eq1, Equation eq2){
        this.equations = new ArrayList<EquationInterface>();
        this.equations.add(eq1);
        this.equations.add(eq2);
        setMatrices(equations);
        calcInvA();
        calcDetA();
        calculate(this.matrixAI, this.matrixB, this.detA);
    }
    
    public Calculator(Equation eq1, Equation eq2, Equation eq3){
        this.equations = new ArrayList<EquationInterface>();
        this.equations.add(eq1);
        this.equations.add(eq2);
        this.equations.add(eq3);
        setMatrices(equations);
    }

    @Override
    public List<EquationInterface> getEquations() {
        return equations;
    }

    @Override
    public void calculate(double[][] matrixAI, double[][] matrixB, double detA) {
        
        double[][] calc = new double[matrixAI.length][matrixAI.length];
        this.matrixX = new double[matrixAI.length][1];
        for(int i=0; i<calc.length; i++){
            for(int j=0; j< calc.length; j++){
                calc[i][j] = ((matrixAI[i][j]*matrixB[j][0]));
            }            
        }
        this.matrixX[0][0] = (calc[0][0]+calc[0][1])*(1/this.detA);
        this.matrixX[1][0] = (calc[1][0]+calc[1][1])*(1/this.detA);
        
    }
    
    public void setMatrices(List<EquationInterface> equations){
        
        int size = equations.size();
        this.matrixA = new double[size][size];
        this.matrixB = new double[size][1];
        for(int i=0; i<size; i++){
            this.matrixB[i][0] = equations.get(i).getC();
                for(int j=0; j< size; j++){	
                    switch (j){
                        case 0:
                            this.matrixA[i][j] = equations.get(i).getX();
                            break;
                        case 1:
                            this.matrixA[i][j] = equations.get(i).getY();
                            break;
                        case 2:
                            this.matrixA[i][j] = equations.get(i).getZ();
                            break;
                        default:
                    }              
            }
        }
        
    }
    
    public void calcInvA(){
        this.matrixAI = new double[this.matrixA.length][this.matrixA.length];
        this.matrixAI[0][0] = this.matrixA[1][1]; // a receives d
        this.matrixAI[1][1] = this.matrixA[0][0]; // d receives a
        this.matrixAI[0][1] = (this.matrixA[0][1])*-1; // b receives -b
        this.matrixAI[1][0] = (this.matrixA[1][0])*-1; // c receives -c
    }
    
    public void calcDetA(){
        this.detA = (this.matrixA[0][0]*this.matrixA[1][1])-(this.matrixA[0][1]*this.matrixA[1][0]); // ad - bc
    }
    
    public void printMatrixA(){
        System.out.println("\n == MATRIX A == ");
        for (int i = 0; i < this.matrixA.length; i++){
            System.out.print("|");
            for (int j = 0; j < this.matrixA[i].length; j++){
                System.out.print(this.matrixA[i][j] + " ");
            }
            System.out.print("|\n");
        }
    }
    
    public void printMatrixB(){
        System.out.println("\n == MATRIX B == ");
        for (int i = 0; i < this.matrixB.length; i++){
            System.out.print("|");
            for (int j = 0; j < this.matrixB[i].length; j++){
                System.out.print(this.matrixB[i][j] + " ");
            }
            System.out.print("|\n");
        }
    }
    
    public void printMatrixX(){
        System.out.println("\n == MATRIX X == ");
        for (int i = 0; i < this.matrixX.length; i++){
            System.out.print("|");
            for (int j = 0; j < this.matrixX[i].length; j++){
                System.out.print(this.matrixX[i][j] + " ");
            }
            System.out.print("|\n");
        }
    }
    
    public void printMatrixAI(){
        System.out.println("\n == MATRIX A INVERSE == ");
        for (int i = 0; i < this.matrixAI.length; i++){
            System.out.print("|");
            for (int j = 0; j < this.matrixAI[i].length; j++){
                System.out.print(this.matrixAI[i][j] + " ");
            }
            System.out.print("|\n");
        }
    }

    public double getDetA() {
        return detA;
    }
    
    
    
}
