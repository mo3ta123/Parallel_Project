package server;

import amazon_database.DataBase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket c;
    DataBase DB;

    public ClientHandler(Socket c , DataBase DB)
    {
        this.c = c;
        this.DB = DB;
    }
    
    @Override
    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(dos);
            ObjectInputStream input = new ObjectInputStream(dis);


            Service service_obj = new Service(dos, dis, output, input , DB);


            String service = dis.readUTF();

            if (service.equals("login"))
            {
                service_obj.login();
            }
            else if (service.equals("signUp"))
            {
                service_obj.signUp();
            }
            else if (service.equals("getInitialItems"))
            {
                service_obj.getInitialItems();
            }
            else if (service.equals("getMyCart"))
            {
                service_obj.getMyCart();
            }
            else if (service.equals("getAccountInfo"))
            {
                service_obj.getAccountInfo();
            }
            else if (service.equals("depositCash"))
            {
                service_obj.depositCash();
            }
            else if (service.equals("search"))
            {
                service_obj.search();
            }
            else if (service.equals("pay"))
            {
                service_obj.pay();
            }
            else if (service.equals("editItemAtCart"))
            {
                service_obj.editItemAtCart();
            }
            else if(service.equals("getTransactionHistory"))
            {
                service_obj.getTransactionHistory();
            }
            else if (service.equals("getItemBoughtList"))
            {
                service_obj.getItemBoughtList();
            }



            dos.close();
            dis.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Something went wrongggggg ");
        }
    }


 
}
