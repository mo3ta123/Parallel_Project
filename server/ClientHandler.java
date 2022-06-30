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

 
}
