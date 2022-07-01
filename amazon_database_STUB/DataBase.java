package amazon_database;

import java.util.HashMap;
import java.util.Vector;

public class DataBase
{
  
  // Stubs for Pays
    public synchronized double getBalance(String userName)
    {
        return 1;
    }

    public synchronized void clearCart(String userName)
    {

    }


    // save transaction for history , withdraw balance , decrease amounts in item table
    public synchronized void makeTransaction(String user_name,double money_amount,String transaction_type,Vector<HashMap<String,String>> items_id)
    {
    }


    // Stubs for search
    public synchronized Vector<HashMap<String,String>> search(String searchString)
    {
        Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

        HashMap<String,String> item1 = new HashMap<String,String>();
        item1.put("Item_ID", "1");
        item1.put("Name", "Camera");
        item1.put("Amount_available", "20");
        item1.put("category", "'Electronics'");
        item1.put("Img_URL", "https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg%22);
        item1.put("Price", "5000");

        items.add(item1);

        HashMap<String,String> item2 = new HashMap<String,String>();
        item2.put("Item_ID", "2");
        item2.put("Name", "CameraHD");
        item2.put("Amount_available", "10");
        item2.put("category", "'Electronics'");
        item2.put("Img_URL", "https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70%22);
        item2.put("Price", "6000");

        items.add(item2);

        return items;
    }
                  
         // Stubs for deposit , save this deposit in transaction history to be viewed later
    public synchronized void depositBalance(String userName, double amount)
    {
        System.out.println("deposit :"+amount);
    }

    // Stub for get account INFO, get all User table of specific user name
    public synchronized Vector<HashMap<String,String>> getAccountInfo(String userName)
    {
        Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

        HashMap<String,String> item1 = new HashMap<String,String>();
        item1.put("Item_ID", "1");
        item1.put("Name", "REDA");
        item1.put("Amount_available", "20");
        item1.put("category", "'Electronics'");
        item1.put("Img_URL", "https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg");
        item1.put("Price", "5000");
        item1.put(ServerDBConst.Cart_COLS.Amount, "100");

        items.add(item1);

        return items;
    }


    // Stubs for GetMYCArt

   /*
    *  It Returns :
    *
    * SELECT Item_IDو Name وAmount_available,category ,Img_URL, Price , Amount
    * FROM   Item, cart
    * WHERE  Item.Item_ID = cart.Item_ID AND
    *       user_name = function parameter (userName)
    *
    *
    */
    public synchronized Vector<HashMap<String,String>> getMyCart(String userName)
    {
        Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();

        HashMap<String,String> item1 = new HashMap<String,String>();
        item1.put("Item_ID", "1");
        item1.put("Name", "Camera");
        item1.put("Amount_available", "20");
        item1.put("category", "'Electronics'");
        item1.put("Img_URL", "https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg");
        item1.put("Price", "5000");
        item1.put(ServerDBConst.Cart_COLS.Amount, "100");

        items.add(item1);

        HashMap<String,String> item2 = new HashMap<String,String>();
        item2.put("Item_ID", "2");
        item2.put("Name", "Phone");
        item2.put("Amount_available", "10");
        item2.put("category", "'Electronics'");
        item2.put("Img_URL", "https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70");
        item2.put("Price", "6000");
        item2.put(ServerDBConst.Cart_COLS.Amount, "100");

        items.add(item2);


        return items;
    }
                  
    // Stubs for editing Item at cart
    public synchronized void removeCartItem(String userName, int itemID)
    {
        System.out.println("REMOVING ITEM");
    }

    public  synchronized  void addItemInCart(String userName,int ItemID , int quantity)
    {
        System.out.println("Adding Item");
    }

    public  synchronized  void updateCartQuantity(String userName,int ItemID , int quantity)
    {
        System.out.println("updating item");
    }


    public synchronized boolean isItemExistsInCart(String userName, int ItemID)
    {
        if(userName.equals("user"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // from item table
    public synchronized int getItemQuantity(int itemID)
    {
        if(itemID == 1)
        {
            return 5;
        }
        else
        {
            return 2;
        }
    }

              
             
}
