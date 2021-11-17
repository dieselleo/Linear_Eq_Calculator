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
    private String user = null;
    private String pass = null;
    private Integer menuLvl = 1;
    private Integer option = null;
    private String[] newUser = new String[4];

    @Override
    public void printMenu() {
        
        switch (menuLvl){
            
            case 0:
                System.out.println("I'm here");
                this.menuLvl=0;
                break;
            
            //menu lvl 1
            //main menu
            case 1:
                System.out.println("Hello, this is a Linear Equation Calculator");
                System.out.println("Choose one of the available options below:");
                System.out.println("1 - Enter with already existent user");
                System.out.println("2 - Create new user");
                System.out.println("0 - Exit system");
                this.option=Integer.parseInt(UserInput.getInput());
                while (this.option<0 || this.option>2){
                    System.out.println("Invalid Option! Please, try again!");
                    this.option=Integer.parseInt(UserInput.getInput());
                }
                if(option==0){
                    this.menuLvl--;
                } else {
                    this.menuLvl++;
                }
                break;
                
            //menu lvl 2
            //1 - Enter with already existent user
            //2 - Create new user
            case 2:
                switch (option){
                    case 1:
                        System.out.println("We are delighted that you have already used the system!");
                        System.out.println("Please enter your user:");
                        user=UserInput.getInput();
                        {
                            try {
                                if (checkUser(user)==true){
                                    System.out.println("Enter your password:");
                                    pass=UserInput.getInput();
                                    while (checkPassword(pass, user)==false && !(pass.equals("0"))){
                                        System.out.println("Wrong password! Try again or enter 0 to exit.");
                                        pass=UserInput.getInput();
                                    }
                                    if(pass.equals("0")){
                                        this.menuLvl--; //menu  lvl 1
                                    } else {
                                        System.out.println("Your welcome "+user);
                                        this.menuLvl++; //menu lvl 3
                                    }                                    
                                } else {
                                    System.out.println("User not found!");
                                    this.menuLvl--;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Your Welcome! Please enter the requiered field and press enter:");
                        System.out.println("User name:");
                        this.newUser[0] = UserInput.getInput();
                        System.out.println("Password:");
                        this.newUser[1] = UserInput.getInput();
                        System.out.println("First name:");
                        this.newUser[2] = UserInput.getInput();
                        System.out.println("Last name:");
                        this.newUser[3] = UserInput.getInput();
                        System.out.println("\nWould you like to create this user?");
                        System.out.println("User name: "+newUser[0]);
                        System.out.println("Password: "+newUser[1]);
                        System.out.println("First name: "+newUser[2]);
                        System.out.println("Last name: "+newUser[3]);
                        System.out.println("\n1 - Yes");
                        System.out.println("0 - No");
                        this.option = Integer.parseInt(UserInput.getInput());
                        
                        while (this.option<0 || this.option>1){
                            System.out.println("Invalid Option. Try again!");
                            this.option = Integer.parseInt(UserInput.getInput());
                        }
                        
                        if(option==0){
                            this.menuLvl--; //lvl 1
                        } else {
                            System.out.println("Your User has been added to the database!");
                            System.out.println("Now select 1 and enter with the user and password you have just created.");
                            //createsuser
                            this.menuLvl--; //lvl 1
                        }
                        
                    break;
                }
                
            //menu lvl
            default:
                System.out.println("Invalid Option. Try again!");
                this.menuLvl=1;
                printMenu();
                break;
        }
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
    
    public boolean checkPassword (String pass, String user) throws SQLException{
        
        conn = DatabaseConnector.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT password from user WHERE user_name like '%"+user+"%'");
        
        
        
        while(rs.next()) {
            if (rs.getString("password").equals(pass)){
            return true;
            }
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return false;
    }
    
    public boolean keepGoing() {
        
        if(this.menuLvl==0){
            return false;
        }        
        return true;
    }
    
}
