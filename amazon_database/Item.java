package amazon_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import amazon_database.ServerDBConst.*;
import java.util.HashMap;






public class Item {

   
       Connection connection;
       PreparedStatement prestatement;
       ResultSet resultset;
      
       public Item(Connection connection){
           this.connection=connection;
       }

        
      public void itemInsert(int Item_ID,String Name,int Amount_available,String category,int Price,String Img_URL)
      {
        try {
            //record item
                prestatement=connection.prepareStatement("insert into item (Name,Amount_available,category,Price,Img_URL) values (?,?,?,?,?)");
                prestatement.setString(1,Name);
                prestatement.setString(2,Integer.toString(Amount_available));
                prestatement.setString(3,category);
                prestatement.setString(4,Integer.toString(Price));
                prestatement.setString(5,Img_URL);
                prestatement.executeUpdate();
                 
             }
        catch (SQLException ex){
            java.util.logging.Logger.getLogger(Item.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
        }
   }  
 
      
      
      
   public  HashMap<String,String> itemView(int Item_ID)
 {
     HashMap<String,String>map=new HashMap<String,String>();
    try
    {
        prestatement=connection.prepareStatement("select* from  item where  (Item_ID=?)");
        prestatement.setString(1, Integer.toString(Item_ID));
        resultset=prestatement.executeQuery();
       if(resultset.next())
        {
            map.put(Items_COLS.Item_ID, resultset.getString(Items_COLS.Item_ID));
            map.put(Items_COLS.Name, resultset.getString(Items_COLS.Name));
            map.put(Items_COLS.Amount_available, resultset.getString(Items_COLS.Amount_available));
            map.put(Items_COLS.category, resultset.getString(Items_COLS.category));
            map.put(Items_COLS.Price, resultset.getString(Items_COLS.Price));
            map.put(Items_COLS.Img_URL, resultset.getString(Items_COLS.Img_URL));
        }
       return map;
    
    }
     
    catch (SQLException ex)
    {
        java.util.logging.Logger.getLogger(Item.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
    }
    return map;
}   
 
     

public void itemUpdate(int Item_ID,int Amount_available)
{
        
    try
    {
        prestatement=connection.prepareStatement("UPDATE item "
                                                                +"SET Amount_available =(Amount_available+? )"
                                                                                                               +"WHERE Item_ID=?");
        prestatement.setString(1,  Integer.toString(Amount_available ));
        prestatement.setString(2, Integer.toString(Item_ID));
        prestatement.executeUpdate();
          
          
         
      
    } 
    catch (SQLException ex)
    {
        java.util.logging.Logger.getLogger(Item.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
    }
}
 public Vector<HashMap<String,String>> item_home_view(int  page)
 {
        
    Vector<HashMap<String,String>> result=new Vector<>();
    int itemsInpage=6;
    int start= (page-1)*itemsInpage;
    int end =page*itemsInpage;
        
    try 
    {
        prestatement =connection.prepareStatement("select * from item where (Item_ID >?) and(Item_ID<=?) ");
        prestatement.setString(1, Integer.toString(start));
        prestatement.setString(2, Integer.toString(end));
        resultset=prestatement.executeQuery();
        while(resultset.next())
        {
            HashMap<String,String>map=new HashMap<String,String>();
            map.put(Items_COLS.Item_ID, resultset.getString(Items_COLS.Item_ID));
            map.put(Items_COLS.Name, resultset.getString(Items_COLS.Name));
            map.put(Items_COLS.Amount_available, resultset.getString(Items_COLS.Amount_available));
            map.put(Items_COLS.category, resultset.getString(Items_COLS.category));
            map.put(Items_COLS.Price, resultset.getString(Items_COLS.Price));
            map.put(Items_COLS.Img_URL, resultset.getString(Items_COLS.Img_URL));
            result.add(map);
        }
    } 
    catch (SQLException ex) 
    {
            java.util.logging.Logger.getLogger(Item.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);

    }
        return result;

 }
 
 public Vector<HashMap<String,String>> searchItems(String searchString){
     Vector<HashMap<String,String>> result=new Vector<>();
     try 
    {
        prestatement =connection.prepareStatement("select * from item where upper(name) like upper(?)");
        prestatement.setString(1,searchString+"%");
        resultset=prestatement.executeQuery();
        while(resultset.next())
        {
            HashMap<String,String>map=new HashMap<String,String>();
            map.put(Items_COLS.Item_ID, resultset.getString(Items_COLS.Item_ID));
            map.put(Items_COLS.Name, resultset.getString(Items_COLS.Name));
            map.put(Items_COLS.Amount_available, resultset.getString(Items_COLS.Amount_available));
            map.put(Items_COLS.category, resultset.getString(Items_COLS.category));
            map.put(Items_COLS.Price, resultset.getString(Items_COLS.Price));
            map.put(Items_COLS.Img_URL, resultset.getString(Items_COLS.Img_URL));
            result.add(map);
        }
    } 
    catch (SQLException ex) 
    {
            java.util.logging.Logger.getLogger(Item.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);

    }
     return result;
 }

 public Vector<HashMap<String,String>> getItems()
    {
        Vector<HashMap<String,String>> items = new Vector<HashMap<String,String>>();
        try{
            prestatement = connection.prepareStatement("SELECT * FROM ITEM");
            resultset = prestatement.executeQuery();
            while(resultset.next()){
                HashMap<String, String> item = new HashMap<String, String>();
                item.put(Items_COLS.Item_ID, resultset.getString(Items_COLS.Item_ID));
                item.put(Items_COLS.Name, resultset.getString(Items_COLS.Name));
                item.put(Items_COLS.Amount_available, resultset.getString(Items_COLS.Amount_available));
                item.put(Items_COLS.category, resultset.getString(Items_COLS.category));
                item.put(Items_COLS.Price, resultset.getString(Items_COLS.Price));
                item.put(Items_COLS.Img_URL, resultset.getString(Items_COLS.Img_URL));
                items.add(item);
            }
            return items;
        }
        catch(SQLException ex){
            //do noting
        }
        return items;
    }
 
 
}

