package client;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

public class Client
{
    String IP_ADDRESS;

    private  void setIP_ADDRESS()
    {

        File file = new File("./src/client/IP_Address.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;

            str = br.readLine();
            IP_ADDRESS = str;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean login(String userName , String password)
    {
        setIP_ADDRESS();

        String result = "invalidLogin";

        try
        {
            // Setup Communication
            Socket c = new Socket(IP_ADDRESS, 1234);
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(dos);
            ObjectInputStream input = new ObjectInputStream(dis);

            // Ask server for login services
            dos.writeUTF("login");

            // Send username and password
            dos.writeUTF(userName);
            dos.writeUTF(password);

            // Wait for result
            result = dis.readUTF();


            // Close Communication
            dos.close();
            dis.close();
            c.close();

        } catch (IOException  ex)
        {

        }

        if ( result.equals("validLogin") )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

        public boolean signUp(String userName , String password , String email , String phoneNumber)
    {
        setIP_ADDRESS();
        String result = "invalidSignUp";

        try
        {
            // Setup Communication
            Socket c = new Socket(IP_ADDRESS, 1234);
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(dos);
            ObjectInputStream input = new ObjectInputStream(dis);

            // Ask server for signUp services
            dos.writeUTF("signUp");

            // Send username and password
            dos.writeUTF(userName);
            dos.writeUTF(password);
            dos.writeUTF(email);
            dos.writeUTF(phoneNumber);

            // Wait for result
            result = dis.readUTF();


            // Close Communication
            dos.close();
            dis.close();
            c.close();

        } catch (IOException  ex)
        {

        }

        if ( result.equals("validSignUp") )
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    
}

