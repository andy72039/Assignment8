package com.example;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Gary on 16/5/28.
 */
public class Server implements Runnable{
    private Thread thread;
    private ServerSocket servSock;

    public Server(){


        try {
            // Detect server ip
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println("IP of my system is := "+IP.getHostAddress());
            System.out.println("Waitting to connect......");

            // Create server socket
            servSock = new ServerSocket(2000);

            // Create socket thread
            thread = new Thread(this);
            thread.start();
        } catch (java.io.IOException e) {
            System.out.println("Socket?��??��?�?!");
            System.out.println("IOException :" + e.toString());
        } finally{

        }
    }

    @Override
    public void run(){
        // Running for waitting multiple client
        try{
            // After client connected, create client socket connect with client
            Socket clntSock = servSock.accept();
            InputStream in = clntSock.getInputStream();
             System.out.println("Connected!!");
             // Transfer data
            byte[] b = new byte[1024];
            int length;
            length = in.read(b);
            String s = new String(b);
            System.out.println("[Server Said]" + s);
            while(true) {
                 length = in.read(b);
                 s = new String(b);
                 System.out.println("[Server Said]" + s);
            }
        }
        catch(Exception e){
            //System.out.println("Error: "+e.getMessage());
        }
    }
    public static void main(String[] args){
        Server obj = new Server();
    }
}