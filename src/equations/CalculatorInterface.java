/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equations;

import java.util.List;

/**
 *
 * @author diese
 */
public interface CalculatorInterface {
    
    public List<EquationInterface> getEquations();
    
    public void calculate(double[][] matrixA, double[][] matrixB, double detA);
    
}
