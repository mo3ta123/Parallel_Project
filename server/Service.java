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
    
        public void signUp()
    {
        try {
            // Read user name and password
            String userName = dis.readUTF();
            String Password = dis.readUTF();
            String email = dis.readUTF();
            String phoneNumber = dis.readUTF();

            // Check that the user already exists
            boolean userExists = DB.doesUserExist(userName);

            if(!userExists)
            {
                // Save user name and encrypted password in database
                DB.addUser(userName, encryptPassword(Password,Password.length(),3) , email ,phoneNumber);
                dos.writeUTF("validSignUp");

            }
            else
            {
                dos.writeUTF("invalidSignUp");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getInitialItems()
    {
        try {

            output.writeObject( DB.getAllItems() );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMyCart()
    {
        try {
            // Read user name
            String userName = dis.readUTF();

            output.writeObject( DB.getMyCart(userName) );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        public void getAccountInfo() {
        try {
            // Read user name
            String userName = dis.readUTF();

            output.writeObject( DB.getAccountInfo(userName) );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void depositCash() {
        try {
            // Read user name and amount of money to deposit
            String userName = dis.readUTF();
            String amount = dis.readUTF();

            DB.depositBalance(userName, Double.parseDouble(amount));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void search() {
        try {
            // Read user searched item
            String searchString = dis.readUTF();

            output.writeObject( DB.search(searchString) );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
