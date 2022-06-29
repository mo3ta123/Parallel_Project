/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    Cart cart;
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
        cart=new Cart(connection);
    }
    
    public synchronized void addUser(String userName, String Password , String email , String phoneNumber){
        user.insert_user(userName, Password, phoneNumber, email);
    }
    public synchronized boolean doesUserExist(String userName){
        if(user.checkLogin(userName) == null)
            return false;
        else
            return true;
    } 
    public synchronized String getEncryptedPassword(String userName){
        return user.checkLogin(userName);
    }
    public synchronized void depositBalance(String userName, double amount)
    {
        transaction.transaction_input(userName, amount, Transaction_type.DEPOSIT, null);
    }
    public synchronized double getBalance(String userName){
        HashMap<String, String> result = user.output_user(userName);
        if(result.isEmpty())
            return -1; // NO SUCH USER EXISTS RETURN -1
        else
            return Double.parseDouble(result.get(User_COLS.USER_BAL));
    }
    public synchronized boolean isAdminExists(String userName , String password){
        String pass = getEncryptedPassword(userName);
        return pass.equals(password);
        
    }
    public synchronized void clearCart(String userName)
    {
        cart.cartDeleteUser(userName);
    }
    public synchronized void makeTransaction(String user_name,double money_amount,String transaction_type,Vector<HashMap<String,String>> items_id)
    {
        transaction.transaction_input(user_name, money_amount, transaction_type, items_id);
    }
    public synchronized Vector<HashMap<String,String>> search(String searchString)
    {
       return item.searchItems(searchString);
    }
    public synchronized Vector<HashMap<String,String>> getMyCart(String userName)
    {
        return cart.cartGetUser(userName);
    }
    public synchronized void removeCartItem(String userName, int itemID)
    {
        cart.deleteItemCart(userName, itemID);
    }
    public  synchronized  void addItemInCart(String userName,int ItemID , int quantity)
    {
        cart.addItemCart(userName, ItemID, quantity);
    }
    public  synchronized  void updateCartQuantity(String userName,int ItemID , int quantity)
    {
       cart.updateCartAmount(userName, ItemID, quantity);
    }
    public synchronized boolean isItemExistsInCart(String userName, int ItemID)
    {
        return cart.isItemExistsInCart(userName,ItemID);
    }
    public synchronized Vector<HashMap<String,String>> getTransactionHistory(String userName)
    {
        return transaction.transactionOutputUser(userName);
    }
    public synchronized Vector<HashMap<String,String>> getItemBoughtList(String userName)
    {
        return transaction.items_bought_list(userName);
    }
    public synchronized Vector<HashMap<String,String>> getAllTransactions()
    {
        return transaction.transaction_output();
    }
    public synchronized Vector<HashMap<String,String>> getAllItemBoughtList()
    {
        return transaction.items_bought_list_all();
    }
    
    
    
     public synchronized Vector<HashMap<String, String>> getAccountInfo(String userName){
        Vector<HashMap<String, String>> result = new Vector<HashMap<String, String>>();
        result.add(user.output_user(userName));
        return result;
    }
   
    public synchronized int getItemQuantity(int itemID){
        HashMap<String, String> result = item.itemView(itemID);
        if(result.isEmpty())
            return -1;
        else
            return Integer.parseInt(result.get(Items_COLS.Amount_available));
    }
    public synchronized Vector<HashMap<String,String>> search(String searchString)
    {
       return item.searchItems(searchString);
    }
    public synchronized Vector<HashMap<String, String>> getAllItems(){
        return item.getItems();
    }
    
    public static void main(String args[]){
        DataBase db = new DataBase();
        //db.addUser("Ahmed", "Pass1234", "test123wa123@hotmail.com", "0110203040");
        System.out.println(db.doesUserExist("Ahmed"));
        System.out.println(db.getEncryptedPassword("Ahmed"));
        db.depositBalance("Ahmed", 2510.2);
        System.out.println(db.getBalance("Ahmed"));
        System.out.println(db.isAdminExists("ADMIN", "xajfk"));
        Vector<HashMap<String, String>> test;
        test = db.getAccountInfo("Ahmed");
        for(int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).get(User_COLS.USER_NAME) +test.get(i).get(User_COLS.USER_TYPE)+ test.get(i).get(User_COLS.USER_BAL) + " " + test.get(i).get(User_COLS.USER_EMAIL) + test.get(i).get(User_COLS.USER_PASS) + test.get(i).get(User_COLS.USER_PHONE));
        }
        test = db.getAllItems();
        for(int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).get(Items_COLS.Item_ID) + test.get(i).get(Items_COLS.Name));
        }
        System.out.println(db.getItemQuantity(2));
    }
}
