package TP3.socket.socket_multiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            System.out.println(bufferedReader.readLine());
            Scanner scanner = new Scanner(System.in);

            // game loop
            while (true) {
                String serverMessage = bufferedReader.readLine();
                System.out.println(serverMessage);

                if (serverMessage.contains("Game ended") || serverMessage.contains("Congrats")) {
                    break;
                }

                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                printWriter.println(userGuess);
            }

            socket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
