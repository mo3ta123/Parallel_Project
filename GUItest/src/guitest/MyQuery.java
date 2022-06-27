package guitest;

/**
 *
 * @author Youssef Essam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyQuery {
    
   public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root","");
        } catch (SQLException ex) {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public ArrayList<Product> BindTable()
    {
        ArrayList<Product> list = new ArrayList<Product>();
        Connection con = getConnection();
        Client obj = new Client();
        Vector<HashMap<String,String>> items = obj.getInitialItems();
        Statement st;
        ResultSet rs;
   
        try 
        {
                st = con.createStatement();
                rs = st.executeQuery("SELECT `Name`, `Amount_available`, `category`, `Img_URL`, `Price` FROM `item`");
   
                Product p;
                while(rs.next())
                    {
                        p = new Product(
                        rs.getString("Name"),
                        rs.getInt("Amount_available"),
                        rs.getString("category"),     
                        rs.getString("Price"),
                        rs.getBytes("Img_URL"),
                        );
                        list.add(p);
                    }
   
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
   return list;
   }
}