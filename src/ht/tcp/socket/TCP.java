package ht.tcp.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class TCP
{
    public static void main(String[] args) throws IOException
    {

        PrintWriter logfile = new PrintWriter("logfile");
        System.out.println("Start");

        try (ServerSocket ss = new ServerSocket(1234))
        {
            while(true)
            {
                new Thread(new TCPSzerver(ss.accept(), logfile)).start();
            }
        }
    }
}
