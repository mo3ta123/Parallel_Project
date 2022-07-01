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
    
    
    
    
}

