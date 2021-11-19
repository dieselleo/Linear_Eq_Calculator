/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diese
 */

public class DatabaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/calculator";    
    private static final String driverName = "com.mysql.jdbc.Driver";   
    private static final String username = "root";   
    private static final String password = "root";
    private static Connection conn;

    public static Connection getConnection() {
    
        try{
			// Load the database driver
			Class.forName(driverName).newInstance();

			// Get a connection to the database
			conn = DriverManager.getConnection(url, username, password) ;
		}
		catch( SQLException se ){
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null ){
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e ){
			System.out.println( e ) ;
		}
        
        return conn;
        
    }    
    
}
