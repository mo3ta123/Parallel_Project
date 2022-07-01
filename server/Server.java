package server;

import amazon_database.DataBase;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable
{

    @Override
    public void run()
    {
        DataBase DB = new DataBase();

        try
        {
            ExecutorService executor = Executors.newFixedThreadPool(50);

            ServerSocket s = new ServerSocket(1234);
            while (true)
            {

                Socket c = s.accept();

                ClientHandler ch = new ClientHandler(c,DB);
                executor.execute(ch);


            }

            //s.close();
        }
        catch (EOFException eofException)
        {
            eofException.printStackTrace();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }



}

