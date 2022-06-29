package client;

import guitest.ServerConst;
import java.util.HashMap;
import java.util.Vector;



/**
 *
 * @author Youssef
 */
public class Client {
    public boolean login(String userName , String password){
    
    
    return true;
    }
    
    public boolean signUp(String userName , String password , String email , String phoneNumber){
    
    
    return true;

    }
    
    public Vector<HashMap<String,String>> getMyCart(String userName){
    
        Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

        HashMap<String,String> item1 = new HashMap<String,String>();
        item1.put("Item_ID", "1");
        item1.put("Name", "Camera");
        item1.put("Amount_available", "20");
        item1.put("category", "'Electronics'");
        item1.put("Img_URL", "https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg");
        item1.put("Price", "5000");
        item1.put("Amount","4");
        items.add(item1);
        
        return items;
        
    }
    
    public Vector<HashMap<String,String>> getInitialItems()
    {

        Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

        HashMap<String,String> item1 = new HashMap<String,String>();
        item1.put("Item_ID", "1");
        item1.put("Name", "Camera");
        item1.put("Amount_available", "20");
        item1.put("category", "'Electronics'");
        item1.put("Img_URL", "https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg");
        item1.put("Price", "5000");

        items.add(item1);

        HashMap<String,String> item2 = new HashMap<String,String>();
        item2.put("Item_ID", "2");
        item2.put("Name", "Phone");
        item2.put("Amount_available", "10");
        item2.put("category", "'Electronics'");
        item2.put("Img_URL", "https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70");
        item2.put("Price", "6000");

        items.add(item2);
        
        HashMap<String,String> item3 = new HashMap<String,String>();
        item3.put("Item_ID", "3");
        item3.put("Name", "books");
        item3.put("Amount_available", "30");
        item3.put("category", "'books'");
        item3.put("Img_URL", "https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70");
        item3.put("Price", "88000");

        items.add(item3);
        return items;  
    }
    
    public Vector<HashMap<String,String>> search(String searchString)
    {
        if(searchString.equals("essam"))
        {
            Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

            HashMap<String,String> item1 = new HashMap<String,String>();
            item1.put("Item_ID", "1");
            item1.put("Name", "Camera");
            item1.put("Amount_available", "20");
            item1.put("category", "'Electronics'");
            item1.put("Img_URL", "https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg");
            item1.put("Price", "5000");

            items.add(item1);
            return items;
        }
        else
        {
            return new Vector();
            
        }
        
    }
    public boolean editItemAtCart(String userName , int itemID, int quantity){
        return false;
    }
    
    
    
    public  Vector<HashMap<String,String>> getTransactionHistory(String userName){
    
    Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

            HashMap<String,String> item1 = new HashMap<String,String>();
         
            item1.put(ServerConst.Transaction_COLS.Transaction_type, "Deposit");
            item1.put(ServerConst.Transaction_COLS.Transaction_ID, "20");
            item1.put(ServerConst.Transaction_COLS.Transaction_date, "13/4/3055");
            item1.put(ServerConst.Transaction_COLS.money_Amount, "5600");
            

            items.add(item1);
            return items;
    
    }
    
    public Vector<HashMap<String,String>> getItemBoughtList(String userName){
    
        
        Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

            HashMap<String,String> item1 = new HashMap<String,String>();
            item1.put("Item_ID", "1");
            item1.put("Name", "Camera");
            item1.put("Amount_available", "20");
            item1.put(ServerConst.Transaction_COLS.Transaction_date, "13/4/3055");
            item1.put(ServerConst.Holds_COLS.Amount, "56");
            item1.put("Price", "5000");

            items.add(item1);
            return items;
    
    }
    
   public Vector<HashMap<String,String>> getAccountInfo(String userName){
           Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

            HashMap<String,String> item1 = new HashMap<String,String>();
            item1.put(ServerConst.User_COLS.USER_NAME, "Mohamed");
            item1.put(ServerConst.User_COLS.USER_PHONE, "0329328");
            item1.put(ServerConst.User_COLS.USER_EMAIL, "hola@yahoo.com");
            item1.put(ServerConst.User_COLS.USER_BAL, "3055");

            items.add(item1);
            return items;
    
   
   }
   
   public void depositCash(String userName,double value){
   
   
   }
}
