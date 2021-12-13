/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DatabaseConnector;

/**
 *
 * @author diese
 */
public class User implements UserInterface{
    
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private String[] fullUser; 
    private String user;
    private String password;
    private String fName;
    private String lName;
    private char type;
    
    public User(String[] newUser){
        
        if (checkUser(newUser[0])==true){
            fullUser = getUser(newUser[0], newUser[1]);
            this.user=fullUser[0];
            this.password=fullUser[1];
            this.fName=fullUser[2];
            this.lName=fullUser[3];
            this.type=fullUser[4].charAt(0);
        } else {
            this.user=newUser[0];
            this.password=newUser[1];
            this.fName=newUser[2];
            this.lName=newUser[3];
            this.type='U';
            createUser(newUser);
        }
        
        
    }
    
    public String[] getUser(String user, String pass){
        
        String[] dbUser = new String[5];
        conn = DatabaseConnector.getConnection();
        try {
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from user WHERE user_name='"+user+"' and password='"+pass+"'");
            
            while(rs.next()) {
                dbUser[0]=rs.getString("user_name");            
                dbUser[1]=rs.getString("password");
                dbUser[2]=rs.getString("first_name");
                dbUser[3]=rs.getString("last_name");
                dbUser[4]=rs.getString("type");
            }
            
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return dbUser;
        
    }
    
    @Override
    public void createUser(String[] newUser){
        conn = DatabaseConnector.getConnection();
        try {
            
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO user (user_name, password, first_name, last_name, type) VALUES ('"+newUser[0]+"', '"+newUser[1]+"', '"+newUser[2]+"', '"+newUser[3]+"', 'U')");
            
            stmt.close();
            conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteUser(String user){
        conn = DatabaseConnector.getConnection();
        try {
            
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM user WHERE user_name='"+user+"'");
            
            stmt.close();
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean checkUser (String user){
        
        conn = DatabaseConnector.getConnection();
        try {
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT user_name from user WHERE user_name like '%"+user+"%'");
            
            while(rs.next()) {
                if (rs.getString("user_name").equals(user)){
                    return true;
                }
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
    }
    
    @Override
    public void getAllUsers() {
        
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT user_name from user");
            
            while(rs.next()) {
                System.out.println(rs.getString("user_name"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public boolean checkPassword(String pass) {
        
        if(pass.equalsIgnoreCase(this.password)){
            return true;
        }
        
        return false;
    }

    @Override
    public String getUserName(){
        return this.user;
    }

    @Override
    public String getFName(){
        return this.fName;
    }

    @Override
    public String getLName(){
        return this.lName;
    }

    @Override
    public char getType(){
        return this.type;
    }

    @Override
    public void setFName(String newFName, String user){
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE user SET first_name='"+newFName+"' WHERE user_name='"+user+"'");
            
            stmt.close();
            conn.close();
            
            this.fName=newFName;
            System.out.println("New First Name is "+newFName);

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setLName(String newLName, String user){
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE user SET last_name='"+newLName+"' WHERE user_name='"+user+"'");
            
            stmt.close();
            conn.close();
            
            this.lName=newLName;
            System.out.println("New Last Name is "+newLName);

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setPassword(String newPass, String user){
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE user SET password='"+newPass+"' WHERE user_name='"+user+"'");
            
            stmt.close();
            conn.close();
            
            this.password=newPass;
            System.out.println("New Password is "+newPass);
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
