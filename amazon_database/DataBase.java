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
}
public Vector<HashMap<String,String>> databaseRequestHandler(Vector<HashMap<String,String>> request){
        String function=request.get(0).get(Request_Config.Config_key);
        switch(function){
            //transaction
            case Request_Config.transaction_input:
                transaction_input_handler(request);
                return null;
            case Request_Config.transaction_output:
                return transaction.transaction_output();
            case Request_Config.items_bought_list:
                return transaction.items_bought_list(Integer.parseInt(request.get(0).get(Transaction_COLS.User_ID)));
            //items
                
            case Request_Config.itemInsert:
                item.itemInsert(Integer.parseInt(request.get(0).get(Items_COLS.Item_ID)),
                                request.get(0).get(Items_COLS.Name),
                                Integer.parseInt(request.get(0).get(Items_COLS.Amount_available)),
                                request.get(0).get(Items_COLS.category),
                                Integer.parseInt(request.get(0).get(Items_COLS.Price)),
                                request.get(0).get(Items_COLS.Img_URL));
                return null;
            case Request_Config.itemView:
                return item.itemView(Integer.parseInt(Items_COLS.Item_ID));
            case Request_Config.itemUpdate:
                item.itemUpdate(Integer.parseInt(request.get(0).get(Items_COLS.Item_ID)),
                                Integer.parseInt(request.get(0).get(Items_COLS.Amount_available)));
                return null;
            case Request_Config.item_home_view:
                return item.item_home_view(Integer.parseInt(request.get(0).get(Items_COLS.item_page)));
            //user
            case Request_Config.insert_user:
                return user.insert_user(request.get(0).get(User_COLS.USER_NAME),
                                 request.get(0).get(User_COLS.USER_PASS),
                                 Integer.parseInt(request.get(0).get(User_COLS.USER_BAL)),
                                 request.get(0).get(User_COLS.USER_PHONE),
                                 request.get(0).get(User_COLS.USER_TYPE));
            case Request_Config.update_user:
                user.update_user(Integer.parseInt(request.get(0).get(User_COLS.USER_ID)),
                        request.get(0).get(User_COLS.USER_NAME),
                        request.get(0).get(User_COLS.USER_PASS),
                        Integer.parseInt(request.get(0).get(User_COLS.USER_BAL)),
                        request.get(0).get(User_COLS.USER_PHONE));
                return null;
            case Request_Config.checkLogin:
                return user.checkLogin(request.get(0).get(User_COLS.USER_NAME), request.get(0).get(User_COLS.USER_PASS));
            case Request_Config.output_user_ALL:
                return user.output_user();
            case Request_Config.output_user:
                return user.output_user(Integer.parseInt(request.get(0).get(User_COLS.USER_ID)));
            default:
                return null;
        }
    }
    private void transaction_input_handler(Vector<HashMap<String,String>> request){
        HashMap<String,String> options=request.get(0);
        request.remove(0);
        transaction.transaction_input(Integer.parseInt(options.get(Transaction_COLS.User_ID)),
                                      Integer.parseInt(options.get(Transaction_COLS.money_Amount)), 
                                                        options.get(Transaction_COLS.Transaction_type)
                                                         ,request);

    }
