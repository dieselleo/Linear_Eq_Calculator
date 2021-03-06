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
    
    public void deleteUser(String user) throws SQLException;
    
    public boolean checkPassword(String pass);
    
    public boolean checkUser (String user) throws SQLException;
    
    public void getAllUsers();
    
    public String getUserName();
    
    public String getFName();
    
    public String getLName();
    
    public char getType();
        
    public void setFName(String newFName, String user) throws SQLException;
    
    public void setLName(String newLName, String user) throws SQLException;
    
    public void setPassword (String newPass, String user) throws SQLException;
    
}
