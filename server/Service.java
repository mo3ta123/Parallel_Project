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
}
