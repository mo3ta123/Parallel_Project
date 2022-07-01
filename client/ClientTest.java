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


        /* Test cases for login && singup /


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



        / Test cases for Editing Items */

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
    
  }


}
