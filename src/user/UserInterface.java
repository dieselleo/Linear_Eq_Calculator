/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.SQLException;

/**
 *
 * @author diese
 */
public interface UserInterface {
    
    public String[] getUser(String user, String pass) throws SQLException;
    
    public void createUser(String[] newUser) throws SQLException;
    
    public boolean checkPassword(String pass);
    
    public String getUserName();
    
    public String getFName();
    
    public String getLName();
    
    public char getType();
        
    public void setFName(String fName);
    
    public void setLName(String lName);
    
    public void setPassword (String pass);
    
}
