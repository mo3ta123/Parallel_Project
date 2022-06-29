/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amazon_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import amazon_database.ServerDBConst.*;

/**
 *
 * @author Omar Moataz
 */
public class Cart {
    Connection connection;
    PreparedStatement prestatement;
    ResultSet resultset;
      
    public Cart(Connection connection){
        this.connection=connection;
    }
    public void cartDeleteUser(String User_name){
        try{
        prestatement=connection.prepareStatement("delete from Cart where user_name=?");
        prestatement.setString(1, User_name);
        prestatement.executeUpdate();
        } catch (SQLException ex) {
            //noting
            System.out.println("Fail");
        }
    }
    public Vector<HashMap<String,String>> cartGetUser(String userName){
        Vector<HashMap<String,String>> result=new Vector<>();
        try{
        prestatement=connection.prepareStatement("select i.item_id,i.name,c.Amount,i.Amount_available,i.Category,i.Img_url,i.price from Cart c,item i where user_name=? and c.item_id=i.item_id");
        prestatement.setString(1, userName);
        resultset=prestatement.executeQuery();
        while(resultset.next())
        {
            HashMap<String,String>map=new HashMap<String,String>();
            map.put(Items_COLS.Item_ID, resultset.getString(Items_COLS.Item_ID));
            map.put(Items_COLS.Name, resultset.getString(Items_COLS.Name));
            map.put(Items_COLS.Amount_available, resultset.getString(Items_COLS.Amount_available));
            map.put(Items_COLS.category, resultset.getString(Items_COLS.category));
            map.put(Items_COLS.Price, resultset.getString(Items_COLS.Price));
            map.put(Items_COLS.Img_URL, resultset.getString(Items_COLS.Img_URL));
            map.put(Cart_COLS.Amount, resultset.getString(Cart_COLS.Amount));
            result.add(map);
        }
        } catch (SQLException ex) {
            //noting
            System.out.println("Fail");
        }
        return result;
    }
    public void deleteItemCart(String userName, int itemID){
        try{
        prestatement=connection.prepareStatement("delete from cart where user_name=? and item_id=?");
        prestatement.setString(1, userName);
        prestatement.setString(2, Integer.toString(itemID));
        prestatement.executeUpdate();
        } catch (SQLException ex) {
            //noting
            System.out.println("Fail");
        }
    }
    public  void addItemCart(String userName,int ItemID , int quantity)
    {
        try{
            prestatement=connection.prepareStatement("insert into cart(user_name,item_id,Amount)values(?,?,?)");
            prestatement.setString(1, userName);
            prestatement.setString(2, Integer.toString(ItemID));
            prestatement.setString(3, Integer.toString(quantity));
            prestatement.executeUpdate();
        }catch (SQLException ex) {
            //noting
            System.out.println("Fail");
        }
        
    }
    public  void  updateCartAmount(String userName,int ItemID , int quantity){
        try{
            prestatement=connection.prepareStatement("update cart set Amount=? where user_name=? and item_id=?");
            prestatement.setString(1, Integer.toString(quantity));
            prestatement.setString(2, userName);
            prestatement.setString(3, Integer.toString(ItemID));
            prestatement.executeUpdate();
        }catch (SQLException ex) {
            //noting
            System.out.println("Fail");
        }
    }
    public boolean isItemExistsInCart(String userName, int ItemID){
        Vector<HashMap<String,String>> result=new Vector<>();
        try{
        prestatement=connection.prepareStatement("select * from cart where user_name=? and item_id=?");
        prestatement.setString(1, userName);
        prestatement.setString(2, Integer.toString(ItemID));
        resultset=prestatement.executeQuery();
        if(resultset.next()){
            return true;
        }
        else 
            return false;
        } catch (SQLException ex) {
            //noting
            System.out.println("Fail");
        }
        return false;
    }
}
