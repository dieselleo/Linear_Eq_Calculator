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
    
    public Calculator(Equation eq1, Equation eq2){
        this.equations = new ArrayList<EquationInterface>();
        this.equations.add(eq1);
        this.equations.add(eq2);
    }
    
    public Calculator(Equation eq1, Equation eq2, Equation eq3){
        this.equations = new ArrayList<EquationInterface>();
        this.equations.add(eq1);
        this.equations.add(eq2);
        this.equations.add(eq3);
    }

    @Override
    public List<EquationInterface> getEquations() {
        return equations;
    }

    @Override
    public int[] calculate(Equation eq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
