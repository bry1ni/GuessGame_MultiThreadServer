package TP3.socket.socket_multiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide extends Thread implements Runnable{
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                System.out.println("Client connected!");

                // Read data from the client
                InputStream inputStream = clientSocket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream, true);
                
                System.out.println("Gm, your complete name pls");
                String text = bufferedReader.readLine();
                System.out.println("Received message: "+text);

                String answer = text+ " well received";
                printWriter.println(answer);
                System.out.println("Session closed.");

                // Close the connection
                clientSocket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
