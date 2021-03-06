package client;

import amazon_database.ServerDBConst.Holds_COLS;
import amazon_database.ServerDBConst.Items_COLS;
import amazon_database.ServerDBConst.Transaction_COLS;
import amazon_database.ServerDBConst.User_COLS;
import java.util.HashMap;
import java.util.Vector;

public class ClientTest {
  
  public static void main(String[] args) {
        Client obj = new Client();


        /* Test cases for login && singup */


        //False
        System.out.println("First Trial login result  = " + obj.login("user", "abcda"));

         //True
        System.out.println("First Trial signUp result  = " + obj.signUp("user", "abcda","Email1","01099501184"));

        // True
        System.out.println("Second Trial login result = " + obj.login("user", "abcda"));

         //False
        System.out.println("First Trial signUp result  = " + obj.signUp("user", "abcda","Email1","01099501184"));

        System.out.println();
        System.out.println();



        /* Test cases for Editing Items */

        //Test1

        // True
        System.out.println(obj.editItemAtCart("user",1,1));

        // True
        System.out.println(obj.editItemAtCart("user",2,10)); 

        // False
        System.out.println(obj.editItemAtCart("user",1,22)); 

        // True
        //System.out.println(obj.editItemAtCart("user",1,0));

        System.out.println();
        System.out.println();
    

  
  /* Test Cases for get my cart */

        Vector<HashMap<String,String>> clientObj = obj.getMyCart("user");
        for(int i =0 ; i<clientObj.size() ; i++)
        {
            System.out.println(clientObj.elementAt(i).get("Item_ID"));
            System.out.println(clientObj.elementAt(i).get("Name"));
            System.out.println(clientObj.elementAt(i).get("Amount_available"));
            System.out.println(clientObj.elementAt(i).get("category"));
            System.out.println(clientObj.elementAt(i).get("Img_URL"));
            System.out.println(clientObj.elementAt(i).get("Price"));
            System.out.println(clientObj.elementAt(i).get("Amount"));
            System.out.println();
        }

        System.out.println();
        System.out.println();


        /* Test case for Deposit */

        obj.depositCash("user",60000);



        /* Tests for pay */

        System.out.println(obj.pay("user"));



        System.out.println();
        System.out.println();
  
               
        // Test Case for transaction history
        Vector<HashMap<String,String>> clientObj4 = obj.getTransactionHistory("user");

        for(int i =0 ; i<clientObj4.size() ; i++)
        {
            System.out.println(clientObj4.get(i).get(Transaction_COLS.Transaction_ID));
            System.out.println(clientObj4.get(i).get(Transaction_COLS.Transaction_date));
            System.out.println(clientObj4.get(i).get(Transaction_COLS.Transaction_type));
            System.out.println(clientObj4.get(i).get(Transaction_COLS.money_Amount));
            System.out.println("");
        }

        System.out.println();
        System.out.println();

       
        // Test Case for Item BoughList
        Vector<HashMap<String,String>> clientObj5 = obj.getItemBoughtList("user");
        
        for(int i =0 ; i<clientObj5.size() ; i++)
        {
            System.out.println(clientObj5.get(i).get(Items_COLS.Item_ID));
            System.out.println(clientObj5.get(i).get(Items_COLS.Name));
            System.out.println( clientObj5.get(i).get(Items_COLS.Price));
            System.out.println(clientObj5.get(i).get(Transaction_COLS.Transaction_date));
            System.out.println(clientObj5.get(i).get(Holds_COLS.Amount));
            System.out.println("");
        }

        System.out.println();
        System.out.println();
        

    // Test cases for Search
        Vector<HashMap<String,String>> clientObj1 = obj.search("X");

        for(int i =0 ; i<clientObj1.size() ; i++)
        {
            System.out.println(clientObj1.get(i).get(Items_COLS.Item_ID));
            System.out.println(clientObj1.get(i).get(Items_COLS.Name));
            System.out.println(clientObj1.get(i).get(Items_COLS.Amount_available));
            System.out.println(clientObj1.elementAt(i).get(Items_COLS.category));
            System.out.println(clientObj1.get(i).get(Items_COLS.Price) );
            System.out.println("");
        }

        System.out.println();
        System.out.println();

        //Test case for Get initial items
        Vector<HashMap<String,String>> clientObj3 = obj.getInitialItems();

        for(int i =0 ; i<clientObj3.size() ; i++)
        {
            System.out.println(clientObj3.get(i).get(Items_COLS.Item_ID));
            System.out.println(clientObj3.get(i).get(Items_COLS.Name));
            System.out.println(clientObj3.get(i).get(Items_COLS.Amount_available));
            System.out.println(clientObj3.elementAt(i).get(Items_COLS.category));
            System.out.println(clientObj3.get(i).get(Items_COLS.Price) );
            System.out.println("");
        }

        System.out.println();
        System.out.println();
        
// Test cases for Search
        Vector<HashMap<String,String>> clientObj1 = obj.search("X");

        for(int i =0 ; i<clientObj1.size() ; i++)
        {
            System.out.println(clientObj1.get(i).get(Items_COLS.Item_ID));
            System.out.println(clientObj1.get(i).get(Items_COLS.Name));
            System.out.println(clientObj1.get(i).get(Items_COLS.Amount_available));
            System.out.println(clientObj1.elementAt(i).get(Items_COLS.category));
            System.out.println(clientObj1.get(i).get(Items_COLS.Price) );
            System.out.println("");
        }

        System.out.println();
        System.out.println();

        //Test case for Get initial items
        Vector<HashMap<String,String>> clientObj3 = obj.getInitialItems();

        for(int i =0 ; i<clientObj3.size() ; i++)
        {
            System.out.println(clientObj3.get(i).get(Items_COLS.Item_ID));
            System.out.println(clientObj3.get(i).get(Items_COLS.Name));
            System.out.println(clientObj3.get(i).get(Items_COLS.Amount_available));
            System.out.println(clientObj3.elementAt(i).get(Items_COLS.category));
            System.out.println(clientObj3.get(i).get(Items_COLS.Price) );
            System.out.println("");
        }

        System.out.println();
        System.out.println();

// Test case for Get account info
        Vector<HashMap<String,String>> clientObj2 = obj.getAccountInfo("user");

        for(int i =0 ; i<clientObj2.size() ; i++)
        {
            System.out.println(clientObj2.elementAt(i).get(User_COLS.USER_NAME));
            System.out.println(clientObj2.elementAt(i).get(User_COLS.USER_PHONE));
            System.out.println(clientObj2.elementAt(i).get(User_COLS.USER_EMAIL));
            System.out.println(clientObj2.elementAt(i).get(User_COLS.USER_BAL));
        }

        System.out.println();
        System.out.println();

  }

}
