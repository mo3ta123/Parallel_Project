/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import amazon_database.ServerDBConst.*;

import java.util.HashMap;
/**
 *
 * @author Omar
 */
public class User {
    
    Connection connection;
    PreparedStatement prestatement;
    ResultSet resultset;
    
    public User(Connection connection){
           this.connection=connection;
    }
    public void insert_user(String u_Name, String u_Password, String u_Phone, String email){
        try{
            //Preparing 
            prestatement=connection.prepareStatement("INSERT INTO User(Name,Password,Phone,email) VALUES(?,?,?,?)");
            prestatement.setString(1, u_Name);
            prestatement.setString(2, u_Password);
            prestatement.setString(3, u_Phone);
            prestatement.setString(4, email);
            prestatement.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("ERROR: Insert Failure");
        }
    }
    public void update_user(String u_Name, String u_Password, double u_Balance, String u_Phone){ //INSERTING AN EMPTY STRING WILL RESULT IN NO CHANGE
        try{
            //Preparing the names
            prestatement = connection.prepareStatement("SELECT * FROM USER WHERE Name = ?");
            prestatement.setString(1, u_Name);
            resultset = prestatement.executeQuery();
            if(resultset.next()){
                if("".equals(u_Password)) u_Password = resultset.getString(User_COLS.USER_PASS);
                if(u_Balance < 0) u_Balance = resultset.getInt(User_COLS.USER_BAL);
                if("".equals(u_Phone)) u_Phone = resultset.getString(User_COLS.USER_PHONE);
            }
            prestatement = connection.prepareStatement("UPDATE USER SET Password = ?, Phone = ?, Balance = ? WHERE Name = ?");
            prestatement.setString(1, u_Password);
            prestatement.setString(2, u_Phone);
            prestatement.setString(3, Double.toString(u_Balance));
            prestatement.setString(4, u_Name);
            prestatement.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("ERROR: Insert Failure");
        }
    }
    public String checkLogin(String u_Name){
        String result = null;
        try{
            prestatement = connection.prepareStatement("SELECT Password FROM USER WHERE Name = ?");
            prestatement.setString(1, u_Name);
            resultset = prestatement.executeQuery();
            if(resultset.next())
                result = resultset.getString(User_COLS.USER_PASS);
            return result;
        }
        catch(SQLException ex){
            //nothinng
        }
        return result;
    }
    public Vector<HashMap<String,String>> output_user(){
            Vector<HashMap<String,String>> result=new Vector<>();
            try{
                prestatement = connection.prepareStatement("SELECT * FROM User");
                resultset = prestatement.executeQuery();
                while(resultset.next()){
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(User_COLS.USER_ID, resultset.getString(User_COLS.USER_ID));
                    map.put(User_COLS.USER_BAL, resultset.getString(User_COLS.USER_BAL));
                    map.put(User_COLS.USER_NAME, resultset.getString(User_COLS.USER_NAME));
                    map.put(User_COLS.USER_PASS, resultset.getString(User_COLS.USER_PASS));
                    map.put(User_COLS.USER_PHONE, resultset.getString(User_COLS.USER_PHONE));
                    map.put(User_COLS.USER_TYPE, resultset.getString(User_COLS.USER_TYPE));
                    result.add(map);
                }
            }
            catch(SQLException ex){
                //nothinng
            }
            return result;
    }
    public HashMap<String,String> output_user(String u_Name){
        HashMap<String, String> map = new HashMap<String, String>();
        try{
            prestatement = connection.prepareStatement("SELECT * FROM User WHERE Name = ?");
            prestatement.setString(1, u_Name);
            resultset = prestatement.executeQuery();
            if(resultset.next()){
                map.put(User_COLS.USER_BAL, resultset.getString(User_COLS.USER_BAL));
                map.put(User_COLS.USER_NAME, resultset.getString(User_COLS.USER_NAME));
                map.put(User_COLS.USER_PASS, resultset.getString(User_COLS.USER_PASS));
                map.put(User_COLS.USER_PHONE, resultset.getString(User_COLS.USER_PHONE));
                map.put(User_COLS.USER_TYPE, resultset.getString(User_COLS.USER_TYPE));
                map.put(User_COLS.USER_EMAIL, resultset.getString(User_COLS.USER_EMAIL));
            }
            return map;
        }
        catch(SQLException ex){
            //noting
        }
        return map;
    }
}
    
