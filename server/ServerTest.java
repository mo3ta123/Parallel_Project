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
  
}


}
