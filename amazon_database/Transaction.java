package amazon_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import amazon_database.ServerDBConst.*;

import java.util.HashMap;
/**
 *
 * @author Omar Moataz
 */
public class Transaction {
    
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
    public void transaction_input(int user_ID,int money_amount,String transaction_type,Vector<HashMap<String,String>>items_id){
        try {
           /* prestatement =connection.prepareStatement("select * from User");
            resultset=prestatement.executeQuery();
            ResultSetMetaData rsmetadata=resultset.getMetaData();
            int size=rsmetadata.getColumnCount();
            */
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            
            //record transaction
            prestatement=connection.prepareStatement("insert into Transaction(User_ID,Transaction_date,money_Amount,Transaction_type)values(?,?,?,?)");
            prestatement.setString(1, Integer.toString(user_ID));
            prestatement.setString(2,formattedDate);
            prestatement.setString(3,Integer.toString(money_amount));
            prestatement.setString(4,transaction_type);
            prestatement.executeUpdate();
            //get transaction id
            prestatement=connection.prepareStatement("select max(Transaction_ID)as "+Transaction_COLS.Transaction_ID+" from Transaction where User_ID=?");
            prestatement.setString(1,Integer.toString(user_ID));
            resultset=prestatement.executeQuery();
            String Transaction_ID="";
            if(resultset.next())
            {
                Transaction_ID=resultset.getString(Transaction_COLS.Transaction_ID);
            }
            
            // get user balance
            prestatement=connection.prepareStatement("select balance from  User  where user_ID=?");
            prestatement.setString(1, Integer.toString(user_ID));
            resultset=prestatement.executeQuery();
            //update user balance
            if(resultset.next()){
                int balance= Integer.parseInt(resultset.getString("balance"));
                prestatement=connection.prepareStatement("Update User set balance=? where user_ID=?");
                if (transaction_type.equals(Transaction_type.BUY)){
                    prestatement.setString(1,Integer.toString(balance-money_amount));
                }
                else{
                    prestatement.setString(1,Integer.toString(balance+money_amount));
                }
                prestatement.setString(2, Integer.toString(user_ID));
                prestatement.executeUpdate();
            }
            
            //holds table and item table update in case of buying
            if (transaction_type.equals(Transaction_type.BUY)&& items_id!=null)
            {
                    for(int i=0;i<items_id.size();i++)
                    {
                        //entry in the holds table
                        prestatement=connection.prepareStatement("insert into Holds(Transaction_ID,Item_ID,Amount)values(?,?,?)");
                        prestatement.setString(1,Transaction_ID);
                        prestatement.setString(2,items_id.get(i).get(Holds_COLS.Item_ID));
                        prestatement.setString(3,items_id.get(i).get(Holds_COLS.Amount));
                        prestatement.executeUpdate();
                        
                        // updating amount in the items table
                        prestatement=connection.prepareStatement("update item set Amount_available=Amount_available-? where item_id =?");
                        prestatement.setString(1,items_id.get(i).get(Holds_COLS.Amount));
                        prestatement.setString(2,items_id.get(i).get(Holds_COLS.Item_ID));
                        prestatement.executeUpdate();
                    }
            }
                    
        } catch (SQLException ex) {
            System.out.println("Fail");
        }
        
    }
    public Vector<HashMap<String,String>> transaction_output(){
        Vector<HashMap<String,String>> result=new Vector<>();
        try {
            prestatement =connection.prepareStatement("select * from Transaction");
            resultset=prestatement.executeQuery();
            while(resultset.next()){
                HashMap<String,String>map=new HashMap<String,String>();
                map.put(Transaction_COLS.User_ID, resultset.getString(Transaction_COLS.User_ID));
                map.put(Transaction_COLS.Transaction_date, resultset.getString(Transaction_COLS.Transaction_date));
                map.put(Transaction_COLS.Transaction_type, resultset.getString(Transaction_COLS.Transaction_type));
                map.put(Transaction_COLS.money_Amount, resultset.getString(Transaction_COLS.money_Amount));
                result.add(map);
            }
        } catch (SQLException ex) {
            //nothing
        }
        return result;
    }
    public Vector<HashMap<String,String>> items_bought_list(int client_id){
        Vector<HashMap<String,String>> result=new Vector<>();
        try {
            prestatement =connection.prepareStatement("select i.name,i.price,t.Transaction_date,h.amount \n" +
                                                        "from item as i,transaction as t,holds as h\n" +
                                                        "where i.item_id=h.item_id and t.user_id=? and t.Transaction_ID = h.Transaction_ID\n" +
                                                        "order by t.Transaction_date desc;");
            prestatement.setString(1, Integer.toString(client_id));
            resultset=prestatement.executeQuery();
            while(resultset.next()){
                HashMap<String,String>map=new HashMap<String,String>();
                map.put(Items_COLS.Name, resultset.getString(Items_COLS.Name));
                map.put(Items_COLS.Price, resultset.getString(Items_COLS.Price));
                map.put(Transaction_COLS.Transaction_date, resultset.getString(Transaction_COLS.Transaction_date));
                map.put(Holds_COLS.Amount, resultset.getString(Holds_COLS.Amount));
                result.add(map);
            }
        } catch (SQLException ex) {
            //noting
        }
        return result;
    }
    public static void main(String [] args){
        Transaction t=new Transaction();
        t.connect();
        Vector<HashMap<String,String>> items=t.items_bought_list(2);
        for (int i=0;i<items.size();i++)
        {
         System.out.println(items.get(i).get(Items_COLS.Name)+"\t"+items.get(i).get(Items_COLS.Price)+"\t"+items.get(i).get(Transaction_COLS.Transaction_date)+"\t"+items.get(i).get(Holds_COLS.Amount));
        }
        
    }
}
