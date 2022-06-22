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
        public static final String Name="Name";
        public static final String Price="Price";
    }
}
