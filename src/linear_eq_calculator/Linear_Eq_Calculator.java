/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linear_eq_calculator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import menus.Menu;
import util.DatabaseConnector;
import util.UserInput;

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
        // TODO code application logic here
        
//        conn = DatabaseConnector.getConnection();
//        stmt = conn.createStatement();
//        rs = stmt.executeQuery("SELECT * from user");
//        
//        while(rs.next()) {
//            System.out.println( rs.getString("user_name")) ;
//        }
//        
//        rs.close();
//        stmt.close();
//        conn.close();

        Menu menu = new Menu();
        menu.printMenu("99");
        while(!UserInput.getInput().equals("0")){
            menu.printMenu(UserInput.getInput());
        }
        
    }
    
}
