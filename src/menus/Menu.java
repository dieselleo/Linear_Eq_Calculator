/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DatabaseConnector;
import util.UserInput;

/**
 *
 * @author diese
 */
public class Menu implements MenuInterface{
    
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    @Override
    public void printMenu(String option) {
        
        switch (option){
            case "99":
                System.out.println("Hello, this is a Linear Equation Calculator");
                System.out.println("Choose one of the available options below:");
                System.out.println("1 - Enter with already existent user");
                System.out.println("2 - Create new user");
                System.out.println("0 - Exit system");
                break;
            case "1":
                System.out.println("We are delighted that you have already used the system!");
                System.out.println("Please enter your user:");
        {
            try {
                checkUser(UserInput.getInput());
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }
    
    public ResultSet checkUser (String user) throws SQLException{
        
        conn = DatabaseConnector.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * from user WHERE user_name="+user+"");
        
        while(rs.next()) {
            System.out.println( rs.getString("user_name")) ;
            return rs;
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return rs;
    }
    
}
