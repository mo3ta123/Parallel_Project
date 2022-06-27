
package amazon_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import amazon_database.*;
import java.util.HashMap;
import java.util.Vector;
import amazon_database.ServerDBConst.*;

public class DataBase {
    Connection connection;
    Transaction transaction;
    Item item;
    User user;
    public boolean connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/amazon","root","");
              return true;
        }
            
    catch (ClassNotFoundException ex){
        java.util.logging.Logger.getLogger(Item.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
        return false;
    }
     catch (SQLException ex){
        java.util.logging.Logger.getLogger(Item.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
         return false;
    }
    }
    
    public DataBase(){
        connect();
        transaction = new Transaction(connection);
        item =new Item(connection);
        user=new User(connection);
    }
    
    public synchronized void addUser(String userName, String Password , String email , String phoneNumber){
        
    }
    public synchronized boolean doesUserExist(String userName){
        
    } 
    public synchronized String getEncryptedPassword(String userName){
        
    }
    
    public static void main(){
        
    }
  
    
}
