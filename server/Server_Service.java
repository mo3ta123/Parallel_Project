package server;

import amazon_database.DataBase;
import java.util.HashMap;
import java.util.Vector;

public class Server_Service
{
    DataBase DB ;
    
    Server_Service(DataBase DB)
    {
        this.DB = DB;
    }
    
    public void startServer()
    {
        Server server = new Server();
        Thread TH = new Thread(server);
        TH.start();
    }

    public boolean login(String userName , String password)
    {

        // Encrypt the given password
        String encryptedPassword = encryptPassword(password, password.length(), 3);

        return DB.isAdminExists(userName,encryptedPassword);
    }

    private static String encryptPassword(String str, int n, int x)
    {
        int MAX = 26;
        // Reduce x because rotation of length 26 is unnecessary
        x = x % MAX;

        char arr[] = str.toCharArray();

        // calculate the frequency of characters
        int freq[] = new int[MAX];
        for (int i = 0; i < n; i++)
            freq[arr[i] - 'a']++;

        for (int i = 0; i < n; i++) {

            if (freq[arr[i] - 'a'] % 2 == 0) {
                // If the frequency of current character is even then increment it by x
                int pos = (arr[i] - 'a' + x) % MAX;
                arr[i] = (char)(pos + 'a');
            }
            else {
                // Else decrement it by x
                int pos = (arr[i] - 'a' - x);
                if (pos < 0)
                    pos += MAX;
                arr[i] = (char)(pos + 'a');
            }
        }
        return String.valueOf(arr);
    }
}
