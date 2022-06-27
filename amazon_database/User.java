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
    public boolean connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/Amazon","root","");
            return true;
        } catch (Exception ex) {
            System.out.println("something is worng");
            return false;
        }
    }
    public Vector<HashMap<String, String>> insert_user(String u_Name, String u_Password, int u_Balance, String u_Phone, String u_Type){
        Vector<HashMap<String, String>> result = new Vector<>();
        try{
            //Preparing 
            prestatement=connection.prepareStatement("INSERT INTO User(Name,Password,Balance,Phone,User_type) VALUES(?,?,?,?,?)");
            prestatement.setString(1, u_Name);
            prestatement.setString(2, u_Password);
            prestatement.setString(3, Integer.toString(u_Balance));
            prestatement.setString(4, u_Phone);
            prestatement.setString(5, u_Type);
            prestatement.executeUpdate();
            prestatement = connection.prepareStatement("SELECT User_ID FROM USER WHERE Name = ? AND PASSWORD = ?");
            prestatement.setString(1, u_Name);
            prestatement.setString(2, u_Password);
            resultset = prestatement.executeQuery();
            if(resultset.next()){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(User_COLS.USER_ID, resultset.getString(User_COLS.USER_ID));
                result.add(map);
                return result;
            }
        }
        catch(SQLException ex){
            System.out.println("ERROR: Insert Failure");
        }
        return result;
    }
    public void update_user(int u_ID, String u_Name, String u_Password, int u_Balance, String u_Phone){ //INSERTING AN EMPTY STRING WILL RESULT IN NO CHANGE
        try{
            //Preparing the names
            prestatement = connection.prepareStatement("SELECT * FROM USER WHERE User_ID = ?");
            prestatement.setString(1, Integer.toString(u_ID));
            resultset = prestatement.executeQuery();
            if(resultset.next()){
                if("".equals(u_Name)) u_Name = resultset.getString(User_COLS.USER_NAME);
                if("".equals(u_Password)) u_Password = resultset.getString(User_COLS.USER_PASS);
                if(u_Balance < 0) u_Balance = resultset.getInt(User_COLS.USER_BAL);
                if("".equals(u_Phone)) u_Phone = resultset.getString(User_COLS.USER_PHONE);
            }
            prestatement = connection.prepareStatement("UPDATE USER SET Name = ?, Password = ?, Phone = ?, Balance = ? WHERE User_ID = ?");
            prestatement.setString(1, u_Name);
            prestatement.setString(2, u_Password);
            prestatement.setString(3, u_Phone);
            prestatement.setString(4, Integer.toString(u_Balance));
            prestatement.setString(5, Integer.toString(u_ID));
            prestatement.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("ERROR: Insert Failure");
        }
    }
    public Vector<HashMap<String,String>> checkLogin(String u_Name, String u_Password){
        Vector<HashMap<String,String>> result = new Vector<>();
        try{
            prestatement = connection.prepareStatement("SELECT User_ID FROM USER WHERE Name = ? AND Password = ?");
            prestatement.setString(1, u_Name);
            prestatement.setString(2, u_Password);
            resultset = prestatement.executeQuery();
            if(resultset.next()){
                HashMap<String, String> map = new HashMap<String, String>();
               map.put(User_COLS.USER_ID, resultset.getString(User_COLS.USER_ID));
               result.add(map);
            }
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
    public Vector<HashMap<String,String>> output_user(int u_ID){
        Vector<HashMap<String,String>> result=new Vector<>();
        try{
            prestatement = connection.prepareStatement("SELECT * FROM User WHERE User_ID = ?");
            prestatement.setString(1, Integer.toString(u_ID));
            resultset = prestatement.executeQuery();
            if(resultset.next()){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(User_COLS.USER_ID, resultset.getString(User_COLS.USER_ID));
                map.put(User_COLS.USER_BAL, resultset.getString(User_COLS.USER_BAL));
                map.put(User_COLS.USER_NAME, resultset.getString(User_COLS.USER_NAME));
                map.put(User_COLS.USER_PASS, resultset.getString(User_COLS.USER_PASS));
                map.put(User_COLS.USER_PHONE, resultset.getString(User_COLS.USER_PHONE));
                map.put(User_COLS.USER_TYPE, resultset.getString(User_COLS.USER_TYPE));
                result.add(map);
            }
            return result;
        }
        catch(SQLException ex){
            //noting
        }
        return result;
    }
}
    
