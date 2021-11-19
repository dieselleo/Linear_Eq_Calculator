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
    
    public User(String[] newUser) throws SQLException{
        
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
    
    public String[] getUser(String user, String pass) throws SQLException{
        
        String[] dbUser = new String[5];
        conn = DatabaseConnector.getConnection();
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
        
        return dbUser;
        
    }
    
    @Override
    public void createUser(String[] newUser) throws SQLException {
        conn = DatabaseConnector.getConnection();
        stmt = conn.createStatement();
        stmt.executeQuery("INSERT INTO user (user_name, password, first_name, type) VALUES ('"+newUser[0]+"', '"+newUser[1]+"', '"+newUser[2]+"', '"+newUser[3]+"', 'U')");
        
        stmt.close();
        conn.close();
    }
    
    public boolean checkUser (String user) throws SQLException{
        
        conn = DatabaseConnector.getConnection();
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
        
        return false;
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
    public void setFName(String fName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLName(String lName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
