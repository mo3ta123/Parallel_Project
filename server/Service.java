package server;

import amazon_database.DataBase;
import amazon_database.ServerDBConst;
import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class Service
{
    DataOutputStream dos;
    DataInputStream dis;
    ObjectOutputStream output;
    ObjectInputStream input;
    DataBase DB;

    Service(DataOutputStream dos, DataInputStream dis, ObjectOutputStream output, ObjectInputStream input, DataBase DB)
    {
        this.dis = dis;
        this.dos = dos;
        this.output = output;
        this.input = input;
        this.DB = DB;
    }
    
    public void login()
    {
        try {
            // Read user name and password
            String userName = dis.readUTF();
            String Password = dis.readUTF();


            // Encrypt the given password
            String encryptedPassword = encryptPassword(Password, Password.length(), 3);

            // Get the encrypted password from database
            String encryptedPasswordFormDB = DB.getEncryptedPassword(userName);

            // Compare the encrypted password from database wit the encrypted password from user
            if (encryptedPassword.equals(encryptedPasswordFormDB))
            {
                dos.writeUTF("validLogin");
            }
            else
            {
                dos.writeUTF("invalidLogin");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
