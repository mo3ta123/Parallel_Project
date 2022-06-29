package client;

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
            //item1.put("Img_URL", "https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg");
            item1.put("Price", "5000");

            items.add(item1);
            return items;
        }
        else
        {
            return new Vector();
            
        }
        
    }
}
