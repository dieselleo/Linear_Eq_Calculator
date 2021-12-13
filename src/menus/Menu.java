/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import equations.Calculator;
import equations.Equation;
import equations.EquationInterface;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;
import util.DatabaseConnector;
import util.UserInput;

/**
 *
 * @author diese
 */
public class Menu implements MenuInterface{
    
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private String userName;
    private String pass;
    private Integer menuLvl;
    private Integer option;
    private String[] newUser;
    private int numOpt;
    private User user;
    private double x,y,z,c;
    private List<EquationInterface> equations = new ArrayList<EquationInterface>();
    private HashMap<Integer, String[]> operations;
    
    public Menu(){
        
        this.userName = null;
        this.pass = null;
        this.menuLvl = 1;
        this.option = null;
        this.newUser = new String[5];
        this.numOpt = 0;
        this.operations =  new HashMap<>();
        
                
    }

    @Override
    public void printMenu() {
        
        switch (menuLvl){
                        
            //menu lvl 1
            //main menu
            case 1:
                System.out.println("Hello, this is a Linear Equation Calculator");
                System.out.println("Choose one of the available options below:");
                System.out.println("1 - Enter with already existent user");
                System.out.println("2 - Create new user");
                System.out.println("0 - Exit system");
                chooseOption(2);
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
                        newUser[0]=UserInput.getInput();
                        {
                            if (checkUser(newUser[0])==true){
                                System.out.println("Enter your password:");
                                newUser[1]=UserInput.getInput();
                                while (checkPassword(newUser[0], newUser[1])==false && !(newUser[1].equals("0"))){
                                    System.out.println("Wrong password! Try again or enter 0 to exit.");
                                    newUser[1]=UserInput.getInput();
                                }
                                if(newUser[1].equals("0")){
                                    this.menuLvl--; //menu  lvl 1
                                } else {
                                    user = new User(newUser);
                                    System.out.println("Your welcome "+user.getFName()+"\n");
                                    this.menuLvl++; //menu lvl 3
                                }                                    
                            } else {
                                System.out.println("User not found!\n");
                                this.menuLvl--;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Your Welcome! Please enter the requiered field and press enter:\n");
                        System.out.println("User name:");
                        this.newUser[0] = UserInput.getInput();
                        System.out.println("Password:");
                        this.newUser[1] = UserInput.getInput();
                        System.out.println("First name:");
                        this.newUser[2] = UserInput.getText();
                        System.out.println("Last name:");
                        this.newUser[3] = UserInput.getText();
                        System.out.println("\nWould you like to create this user?\n");
                        System.out.println("User name: "+newUser[0]);
                        System.out.println("Password: "+newUser[1]);
                        System.out.println("First name: "+newUser[2]);
                        System.out.println("Last name: "+newUser[3]);
                        System.out.println("\n1 - Yes");
                        System.out.println("0 - No");
                        chooseOption(1);
                        
                        if(option==0){
                            this.menuLvl--; //lvl 1
                        } else {
                            System.out.println("Your User has been added to the database!");
                            System.out.println("Now select 1 and enter with the user and password you have just created.\n");
                            user = new User(newUser);
                            
                            this.menuLvl--; //lvl 1
                        }
                        
                    break;
                }
            break;
            
            //menu lvl 3
            //after got in the system with user
            case 3:
                
                //checking user type
                if(user.getType()=='A'){
                    System.out.println("Choose an option!");
                    System.out.println("1 - Solve Equation");
                    System.out.println("2 - My User");
                    System.out.println("3 - Review Previous Operations");
                    System.out.println("4 - Manage Users");
                    System.out.println("0 - Back to Main Menu");
                    chooseOption(4);
                } else {
                    System.out.println("Choose an option!");
                    System.out.println("1 - Solve Equation");
                    System.out.println("2 - My User");
                    System.out.println("0 - Back to Main Menu\n");
                    chooseOption(2);
                }
                
                if(option==0){
                    this.menuLvl=1; //back to main
                } else {                    
                    this.menuLvl++;
                }                
            break;
            
            //menu lvl 4
            //user can solve eq, see its user, review solved equations and manage other users (if adm)
            case 4:
                
                switch(option){
                    case 1:
                        System.out.println("How many variables has your equation have?");
                        System.out.println("2 - 2 Variables");
                        System.out.println("3 - 3 Variables");
                        System.out.println("0 - Exit");
                        System.out.println("\nNOTES:");
                        System.out.println(" - The results of the equations are aproximated");
                        System.out.println(" - The entrys must be decimals (fractions must be entered e.g. 1.55)");
                        chooseOption(3);
                        while(this.option==1){
                            System.out.println("Invalid Option. Try again!\n");
                            chooseOption(3);
                        }
                        switch (option){
                            case 0:
                                System.out.println("hello");
                                this.menuLvl--;
                                break;  
                            case 2:
                                for (int i=1; i<=2; i++){
                                    System.out.println("Now you will insert the terms for the equation number "+i+"!");
                                    System.out.println("Please enter value for variable X:");
                                    this.x = UserInput.getNumber();
                                    System.out.println("Please enter value for variable Y:");
                                    this.y = UserInput.getNumber();
                                    System.out.println("Please enter value for the constant:");
                                    this.c = UserInput.getNumber();
                                    EquationInterface eq = new Equation(x,y,c);
                                    this.equations.add(eq);
                                    System.out.println("The quation created is "+eq.getEquation());
                                }
                                Calculator calc2 = new Calculator(equations);
                                calc2.printMatrixX();
                                calc2.recordOperation(user.getUserName());
                                this.equations.clear();
                                System.out.println("\nWould you like to solve more equations?");
                                System.out.println("1 - Yes");
                                System.out.println("0 - No");
                                chooseOption(1);
                                if (this.option==0){
                                    this.menuLvl--;
                                    this.option=1;
                                }
                                break;
                            case 3:
                                for (int i=1; i<=3; i++){
                                    System.out.println("Now you will insert the terms for the equation number "+i+"!");
                                    System.out.println("Please enter value for variable X:");
                                    this.x = UserInput.getNumber();
                                    System.out.println("Please enter value for variable Y:");
                                    this.y = UserInput.getNumber();
                                    System.out.println("Please enter value for variable Z:");
                                    this.z = UserInput.getNumber();
                                    System.out.println("Please enter value for the constant:");
                                    this.c = UserInput.getNumber();
                                    EquationInterface eq = new Equation(x,y,z,c);
                                    this.equations.add(eq);
                                    System.out.println("The quation created is "+eq.getEquation());
                                }
                                Calculator calc3 = new Calculator(equations);
                                calc3.printMatrixX();
                                calc3.recordOperation(user.getUserName());
                                this.equations.clear();
                                System.out.println("\nWould you like to solve more equations?");
                                System.out.println("1 - Yes");
                                System.out.println("0 - No");
                                chooseOption(1);
                                if (this.option==0){
                                    this.menuLvl--;
                                    this.option=1;
                                }
                                break;                              
                        }
                    break;
                    
                    
                    case 2:
                        System.out.println("Username: "+user.getUserName());
                        System.out.println("First Name: "+user.getFName());
                        System.out.println("Last Name: "+user.getLName());
                        System.out.println("\n1- Modify First Name");
                        System.out.println("2- Modify Last Name");
                        System.out.println("3- Modify Password");
                        System.out.println("0 - Go Back");
                        chooseOption(3);
                        switch(option){
                            case 1:
                                System.out.println("Enter the new First Name and press enter:");
                                {
                                    user.setFName(UserInput.getText(), user.getUserName());
                                }
                                this.option=2;
                                break;
                                
                            case 2:
                                System.out.println("Enter the new Last Name and press enter:");
                                {
                                    user.setLName(UserInput.getText(), user.getUserName());
                                }
                                this.option=2;
                                break;
                            
                            case 3:
                                System.out.println("Enter the new Password and press enter:");
                                {
                                    user.setPassword(UserInput.getInput(), user.getUserName());
                                }
                                this.option=2;
                                break;
                            case 0:
                                this.menuLvl--;
                                break;
                        }
                        
                    break;
                    case 3:
                        this.operations = reviewOperations();
                        System.out.println("These are all the operations performed in the system:");
                        for(Integer id : operations.keySet()){
                            if(operations.get(id)[2] == null){
                                System.out.println("User: "+operations.get(id)[6]);
                                System.out.println("\nEquations:");
                                System.out.println(operations.get(id)[0]);
                                System.out.println(operations.get(id)[1]);
                                System.out.println("\nResult: ");
                                System.out.println("X = "+operations.get(id)[3]);
                                System.out.println("Y = "+operations.get(id)[4]+"\n");
                                System.out.println("=================================");
                            } else {
                                System.out.println("User: "+operations.get(id)[6]);
                                System.out.println("\nEquations:");
                                System.out.println(operations.get(id)[0]);
                                System.out.println(operations.get(id)[1]);
                                System.out.println(operations.get(id)[2]);
                                System.out.println("\nResult: ");
                                System.out.println("X = "+operations.get(id)[3]);
                                System.out.println("Y = "+operations.get(id)[4]);
                                System.out.println("Z = "+operations.get(id)[5]+"\n");
                                System.out.println("=================================");
                            }                            
                        }
                        this.menuLvl--;
                    break;
                    
                    case 4:
                        System.out.println("These are all the users registered in the system!");
                        user.getAllUsers();
                        System.out.println("\nIf you like to remove one of these users, type its name exactly how it appears in the list and press enter.");
                        System.out.println("If you like to go back, press 0 and enter.");
                        String action=UserInput.getInput();
                        
                        while(checkUser(action)==false && Objects.equals(action, "0")==false){
                            System.out.println("Invalid user! Check Typing.");
                            action=UserInput.getInput();
                        }
                        if(action.equals("0")){
                            this.menuLvl--;
                        } else {
                            System.out.println("Do you really like to DELETE the user "+action+" from the system?");
                            System.out.println("\n1 - Yes\n0 - No");
                            chooseOption(2);
                            if(option==0){
                                this.menuLvl--;
                            } else {
                                user.deleteUser(action);
                                this.menuLvl--;
                            }
                        }
                        
                    break;
                    
                }
            
            //menu lvl
//            default:
//                System.out.println("AAAAAAAAAAInvalid Option. Please, try again!\n");
//                this.menuLvl=1;
//                break;
        }
    }
    
    public boolean checkUser (String user) {
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT user_name from user WHERE user_name='"+user+"'");
            
            while(rs.next()) {
                if (rs.getString("user_name").equals(user)){
                    return true;
                }
                
                rs.close();
                stmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean checkPassword (String user, String pass){
        
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("SELECT password from user WHERE user_name='"+user+"'");
            
            while(rs.next()) {
                if (rs.getString("password").equals(pass)){
                    return true;
                }
            }
            
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return false;
    }
    
    public char checkType (String user){
        
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("SELECT type from user WHERE user_name='"+user+"'");
                    
            while(rs.next()) {
                return rs.getString("type").charAt(0);
                
            }
            
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 'U';
    }
    
    public HashMap<Integer, String[]> reviewOperations(){
        
        HashMap<Integer, String[]> data = new HashMap<>();
        conn = DatabaseConnector.getConnection();
        try {
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("SELECT eq.id, eq.user, eq.equation_1, eq.equation_2, eq.equation_3, ed.x, ed.y, ed.z from equations eq, eq_details ed WHERE eq.id=ed.equation");
                    
            while(rs.next()) {
                String[] values = new String[7];
                values[0] = rs.getString("eq.equation_1");
                values[1] = rs.getString("eq.equation_2");
                values[2] = rs.getString("eq.equation_3");
                values[3] = rs.getString("ed.x");
                values[4] = rs.getString("ed.y");
                values[5] = rs.getString("ed.z");
                values[6] = rs.getString("eq.user");
                data.put(Integer.parseInt(rs.getString("eq.id")), values);
            }
            
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    public boolean keepGoing() {
        
        if(this.menuLvl==0){
            return false;
        }        
        return true;
    }
    
    private void chooseOption(int numOpt){
        this.option = UserInput.getOption();
        while (this.option<0 || this.option>numOpt){
            System.out.println("Invalid Option. Try again!\n");
            this.option = UserInput.getOption();
        }
    }
    
}
