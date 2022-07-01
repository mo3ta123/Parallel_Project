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

    
    
    
}
