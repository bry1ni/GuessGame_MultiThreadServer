package TP3.socket.socket_multiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Repartitor extends Thread{
    private Socket socket;
    private int nClient;

    public Repartitor(ServerSocket serverSocket, int nClient){
        super();
        this.socket = socket;
        this.nClient = nClient;
    }

    public void run() {
        try{
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            printWriter.println("Welcome client number: "+nClient);
            System.out.println("Client number: "+nClient+ " connected.");
            System.out.println("@IP= "+socket.getRemoteSocketAddress());

            while(true){
                String req = bufferedReader.readLine();
                int resp = req.length();
                printWriter.println(resp);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
