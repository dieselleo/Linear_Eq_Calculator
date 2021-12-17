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
        
    }
    
}
