/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linear_eq_calculator;

import java.sql.SQLException;
import menus.Menu;

/**
 *
 * @author diese
 */
public class Linear_Eq_Calculator {

    /**
     * @param args the command line arguments
     */
    
//    private static Connection conn = null;
//    private static Statement stmt = null;
//    private static ResultSet rs = null;
        
    public static void main(String[] args) throws SQLException {

        Menu menu = new Menu();
        while(menu.keepGoing()==true){
            menu.printMenu();
        }

//        Equation eq1 = new Equation(1,2,6);
//        Equation eq2 = new Equation(-6,12,-10);
//        Calculator calc = new Calculator(eq1, eq2);
//        for(EquationInterface e : calc.getEquations()){
//            System.out.println(e.getEquation());
//        }
//        calc.printMatrixA();
//        calc.printMatrixAI();
//        calc.printMatrixB();
//        System.out.println(calc.getDetA());
//        calc.printMatrixX();
//        
//        System.out.println("+++++++++++ new calculation ++++++++++++");
//        Equation eq3 = new Equation(3,2,-1,6);
//        Equation eq4 = new Equation(-2,2,1,3);
//        Equation eq5 = new Equation(1,1,1,4);
//        Calculator calc1 = new Calculator(eq3, eq4, eq5);
//        for(EquationInterface e : calc1.getEquations()){
//            System.out.println(e.getEquation());
//        }
//        calc1.printMatrixA();
//        calc1.printMatrixAI();
//        calc1.printMatrixB();
//        calc1.printCoFactor();
//        calc1.printCoFactorT();
//        System.out.println(calc1.getDetA());
//        calc1.printMatrixX();
        
    }
    
}
