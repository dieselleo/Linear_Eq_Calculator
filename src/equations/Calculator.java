/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;
import util.DatabaseConnector;

/**
 *
 * @author diese
 */
public class Calculator implements CalculatorInterface{
    
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    List<EquationInterface> equations;
    double[][] matrixA;
    double[][] matrixAI;
    double detA;
    double[] matrixX;
    double[][] matrixB;
    double[][] coFactor;
    double[][] coFactorT;
    
    public Calculator(List<EquationInterface> equations){
        this.equations = equations;
        if(this.equations.size()>2){
            this.coFactor = new double[3][3];
            this.coFactorT = new double[3][3];
            setMatrices(equations);
            calcDetA();
            calcInvA();
            calculate();
        } else {
            setMatrices(equations);
            calcInvA();
            calcDetA();
            calculate();
        }        
    }

    @Override
    public List<EquationInterface> getEquations() {
        return equations;
    }

    @Override
    public void calculate() {
        if(this.equations.size()==2){
            double[][] calc = new double[this.matrixAI.length][this.matrixAI.length];
            this.matrixX = new double[this.matrixAI.length];
            for(int i=0; i<calc.length; i++){
                for(int j=0; j< calc.length; j++){
                    calc[i][j] = ((this.matrixAI[i][j]*this.matrixB[j][0]));
                }            
            }
        this.matrixX[0] = (calc[0][0]+calc[0][1])*(1/this.detA);
        this.matrixX[1] = (calc[1][0]+calc[1][1])*(1/this.detA);
        } else {
            // calculating matrix inverse
             double[][] calc = new double[3][3];
             for(int i=0; i<matrixAI.length; i++){
                for(int j=0; j< matrixAI.length; j++){
                    calc[i][j] = ((this.matrixAI[i][j]*this.matrixB[j][0]));
                }            
            }
             calc[0][0] = calc[0][0]+
                     calc[0][1]+
                     calc[0][2];
             calc[1][0] = calc[1][0]+
                     calc[1][1]+
                     calc[1][2];
             calc[2][0] = calc[2][0]+
                     calc[2][1]+
                     calc[2][2];
             
             // multiplying matrix inverse by matrix b
             this.matrixX = new double[calc.length];
             this.matrixX[0] = calc[0][0];
             this.matrixX[1] = calc[1][0];
             this.matrixX[2] = calc[2][0];
        }
        
        
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
//        if (size==2){
//            
//        } else {
//            System.out.println("setting 3x3");
//        }
        
    }
    
    public void calcInvA(){
        
        if(this.equations.size()==2){
            this.matrixAI = new double[this.matrixA.length][this.matrixA.length];
            this.matrixAI[0][0] = this.matrixA[1][1]; // a receives d
            this.matrixAI[1][1] = this.matrixA[0][0]; // d receives a
            this.matrixAI[0][1] = (this.matrixA[0][1])*-1; // b receives -b
            this.matrixAI[1][0] = (this.matrixA[1][0])*-1; // c receives -c
        } else {
            this.matrixAI = new double[this.matrixA.length][this.matrixA.length];
            // multiplying co-factor transposed by detA
            for(int i=0; i<(this.matrixAI.length); i++){
                for(int j=0; j<(this.matrixAI.length); j++){
                    this.matrixAI[i][j] = this.coFactorT[i][j]*this.detA;
                }
            }            
        }
    }
    
    public void calcDetA(){
        if(this.equations.size()==2){
            this.detA = (this.matrixA[0][0]*this.matrixA[1][1])-(this.matrixA[0][1]*this.matrixA[1][0]); // ad - bc
        } else {
            // getting minor matrix
            this.coFactor[0][0] = ((this.matrixA[1][1]*this.matrixA[2][2])-(this.matrixA[1][2]*this.matrixA[2][1]));
            this.coFactor[0][1] = ((this.matrixA[1][0]*this.matrixA[2][2])-(this.matrixA[1][2]*this.matrixA[2][0]));
            this.coFactor[0][2] = ((this.matrixA[1][0]*this.matrixA[2][1])-(this.matrixA[1][1]*this.matrixA[2][0]));
            this.coFactor[1][0] = ((this.matrixA[0][1]*this.matrixA[2][2])-(this.matrixA[0][2]*this.matrixA[2][1]));
            this.coFactor[1][1] = ((this.matrixA[0][0]*this.matrixA[2][2])-(this.matrixA[0][2]*this.matrixA[2][0]));
            this.coFactor[1][2] = ((this.matrixA[0][0]*this.matrixA[2][1])-(this.matrixA[0][1]*this.matrixA[2][0]));
            this.coFactor[2][0] = ((this.matrixA[0][1]*this.matrixA[1][2])-(this.matrixA[0][2]*this.matrixA[1][1]));
            this.coFactor[2][1] = ((this.matrixA[0][0]*this.matrixA[1][2])-(this.matrixA[0][2]*this.matrixA[1][0]));
            this.coFactor[2][2] = ((this.matrixA[0][0]*this.matrixA[1][1])-(this.matrixA[0][1]*this.matrixA[1][0]));
            
            //placing signs for co-factor
            this.coFactor[0][1] = (this.coFactor[0][1]*-1);
            this.coFactor[1][0] = (this.coFactor[1][0]*-1);
            this.coFactor[1][2] = (this.coFactor[1][2]*-1);
            this.coFactor[2][1] = (this.coFactor[2][1]*-1);
            
            // calculating determinant
            this.detA = (this.matrixA[0][0]*this.coFactor[0][0])+
                    (this.matrixA[0][1]*this.coFactor[0][1])+
                    (this.matrixA[0][2]*this.coFactor[0][2]);
            this.detA = (1/this.detA);
            
            // creating transpose of co-factor
            this.coFactorT[0][0] = this.coFactor[0][0];
            this.coFactorT[0][1] = this.coFactor[1][0];
            this.coFactorT[0][2] = this.coFactor[2][0];
            this.coFactorT[1][0] = this.coFactor[0][1];
            this.coFactorT[1][1] = this.coFactor[1][1];
            this.coFactorT[1][2] = this.coFactor[2][1];
            this.coFactorT[2][0] = this.coFactor[0][2];
            this.coFactorT[2][1] = this.coFactor[1][2];
            this.coFactorT[2][2] = this.coFactor[2][2];
        }
        
    }
    
    public void recordOperation(String user){
        
        int records=1;
        conn = DatabaseConnector.getConnection();
        try {
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT count(id) as records from equations");
            while(rs.next()) {
                records = (Integer.parseInt(rs.getString("records")))+1;
            }
            
            if(this.equations.size()==2){
                stmt.executeUpdate("INSERT INTO equations (id, equation_1, equation_2, user) VALUES ("+records+",'"+this.equations.get(0).getEquation()+"', '"+this.equations.get(1).getEquation()+"', '"+user+"')");
                stmt.executeUpdate("INSERT INTO eq_details (equation, x, y) VALUES ("+records+",'"+this.matrixX[0]+"', '"+this.matrixX[1]+"')");
            } else {
                stmt.executeUpdate("INSERT INTO equations (id, equation_1, equation_2, equation_3, user) VALUES ("+records+",'"+this.equations.get(0).getEquation()+"', '"+this.equations.get(1).getEquation()+"', '"+this.equations.get(2).getEquation()+"', '"+user+"')");
                stmt.executeUpdate("INSERT INTO eq_details (equation, x, y, z) VALUES ("+records+",'"+this.matrixX[0]+"', '"+this.matrixX[1]+"', '"+this.matrixX[2]+"')");
            }
            
            rs.close();
            stmt.close();
            conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            System.out.println("| "+ this.matrixX[i] + " |");
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
    
    public void printCoFactor(){
        System.out.println("\n == MATRIX CO-FACTOR == ");
        for (int i = 0; i < this.coFactor.length; i++){
            System.out.print("|");
            for (int j = 0; j < this.coFactor[i].length; j++){
                System.out.print(this.coFactor[i][j] + " ");
            }
            System.out.print("|\n");
        }
    }
    
    public void printCoFactorT(){
        System.out.println("\n == MATRIX CO-FACTOR TRANSPOSED == ");
        for (int i = 0; i < this.coFactorT.length; i++){
            System.out.print("|");
            for (int j = 0; j < this.coFactorT[i].length; j++){
                System.out.print(this.coFactorT[i][j] + " ");
            }
            System.out.print("|\n");
        }
    }

    public double getDetA() {
        return detA;
    }
    
    
    
}
