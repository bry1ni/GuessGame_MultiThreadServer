package TP3.socket.socket_multiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread{
        private int nClient = 0;

        public void run() {
            try{
                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Server is waiting for connections...");

                while (true) {
                    Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                    System.out.println("Client connected!");
                    ++nClient;
                    new Repartitor(serverSocket, nClient).start();
                }
                
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            new ServerMT().start();
        }
}
