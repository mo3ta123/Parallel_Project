package amazon_database;
/**
 *
 * @author Omar Moataz
 */
public class ServerDBConst {
    public static class User_Type{
        public static  final String ADMIN="admin"; 
        public static final String ClIENT="client";
    }
    public static class User_COLS{
        public static final String USER_NAME = "Name";
        public static final String USER_ID = "User_ID";
        public static final String USER_PASS = "Password";
        public static final String USER_BAL = "Balance";
        public static final String USER_PHONE = "Phone";
        public static final String USER_TYPE="User_type ";
    }
    public static class Transaction_type{
        public static final String BUY="Buy";
        public static final String DEPOSIT="Deposit";
    }
    public static class Transaction_COLS{
        public static final String Transaction_ID="Transaction_ID";
        public static final String User_ID="User_ID";
        public static final String Transaction_date="Transaction_date";
        public static final String money_Amount="money_Amount";
        public static final String Transaction_type="Transaction_type";
    }
    public static class Holds_COLS{
        public static final String Amount="Amount";
        public static final String Transaction_ID="Transaction_ID";
        public static final String Item_ID="Item_ID";
    }
    public static class Items_COLS{
      public static final String Item_ID="Item_ID";  
      public static final String Name="Name";
      public static final String Price="Price";
      public static final String Amount_available="Amount_available";
      public static final String category="category";
      public static final String Img_URL="Img_URL";
      public static final String item_page="Page";//not col
    }
    public static class Request_Config{
        public static final String Config_key="Config";
        
        public static final String transaction_input="transaction_input";
        public static final String transaction_output="transaction_output";
        public static final String items_bought_list="items_bought_list";
        
        public static final String itemInsert ="itemInsert";
        public static final String itemView="itemView";
        public static final String itemUpdate="itemUpdate";
        public static final String item_home_view="item_home_view";
        
        
        public static final String insert_user="insert_user";
        public static final String update_user="update_user";
        public static final String output_user_ALL="output_user_ALL";
        public static final String output_user="output_user";
        public static final String checkLogin="checkLogin";
    }
    
}
