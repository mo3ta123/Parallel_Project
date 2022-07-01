package server;

import amazon_database.DataBase;
import amazon_database.ServerDBConst;
import java.util.HashMap;
import java.util.Vector;

public class ServerTest {

public static void main(String[] args)
    {
        DataBase DB = new DataBase();
        Server_Service obj = new Server_Service(DB);
        obj.startServer();

        // Test Admin login
        System.out.println(obj.login("ADMIN", "admin"));
        System.out.println(obj.login("ADMIN", "admi"));
        System.out.println("");
        System.out.println("");


        // Test Generate report in GUI admin
         Vector<HashMap<String,String>> clientObj3 = obj.report_1_getAllItems();

        for(int i =0 ; i<clientObj3.size() ; i++)
        {
            System.out.println(clientObj3.get(i).get(ServerDBConst.Items_COLS.Item_ID));
            System.out.println(clientObj3.get(i).get(ServerDBConst.Items_COLS.Name));
            System.out.println(clientObj3.get(i).get(ServerDBConst.Items_COLS.Amount_available));
            System.out.println(clientObj3.elementAt(i).get(ServerDBConst.Items_COLS.category));
            System.out.println(clientObj3.get(i).get(ServerDBConst.Items_COLS.Price) );
            System.out.println("");
        }

        System.out.println();
        System.out.println();
        
           
        Vector<HashMap<String,String>> clientObj4 = obj.report_2_getAllTransaction();

        for(int i =0 ; i<clientObj4.size() ; i++)
        {
            System.out.println(clientObj4.get(i).get(ServerDBConst.Transaction_COLS.Transaction_ID));
            System.out.println(clientObj4.get(i).get(ServerDBConst.Transaction_COLS.Transaction_date));
            System.out.println(clientObj4.get(i).get(ServerDBConst.Transaction_COLS.Transaction_type));
            System.out.println(clientObj4.get(i).get(ServerDBConst.Transaction_COLS.money_Amount));
            System.out.println("");
        }

        System.out.println();
        System.out.println();

      
        Vector<HashMap<String,String>> clientObj5 = obj.report_3_getAllItemBoughtList();
        
        System.out.println(clientObj5.isEmpty());
        
        for(int i =0 ; i<clientObj5.size() ; i++)
        {
            System.out.println(clientObj5.get(i).get(ServerDBConst.Transaction_COLS.User_Name));
            System.out.println(clientObj5.get(i).get(ServerDBConst.Items_COLS.Item_ID));
            System.out.println(clientObj5.get(i).get(ServerDBConst.Items_COLS.Name));
            System.out.println( clientObj5.get(i).get(ServerDBConst.Items_COLS.Price));
            System.out.println(clientObj5.get(i).get(ServerDBConst.Transaction_COLS.Transaction_date));
            System.out.println(clientObj5.get(i).get(ServerDBConst.Holds_COLS.Amount));
            System.out.println("");
        }

        System.out.println();
        System.out.println();
        

 }


}
