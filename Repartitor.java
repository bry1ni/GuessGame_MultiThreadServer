package TP3.socket.socket_multiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Repartitor extends Thread {
    private Socket socket;
    private int nClient;
    private int secretNumber;
    private boolean gameActive;

    public Repartitor(ServerSocket serverSocket, int nClient) {
        super();
        try {
            this.socket = serverSocket.accept();
            this.nClient = nClient;
            this.secretNumber = generateSecretNumber();
            this.gameActive = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int generateSecretNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            printWriter.println("Welcome client number: " + nClient);
            printWriter.println("Guess the secret number between 1 and 100.");

            while (gameActive) {
                String guess = bufferedReader.readLine();
                // client disconnction
                if (guess == null) {
                    gameActive = false;
                    break;
                }

                try {
                    int userGuess = Integer.parseInt(guess);

                    if (userGuess == secretNumber) {
                        String winnerIP = socket.getRemoteSocketAddress();
                        System.out.println("Client with @IP= " + winnerIP + " won the game.");
                        printWriter.println("Congrats! You guessed the correct number.");
                        printWriter.println("The secret value: " + secretNumber);
                        printWriter.println("Game ended.");

                        //notify all
                        /*if (!clientIPs.contains(winnerIP)) {
                            printWriter.println("Client with @IP= " + winnerIP + " won the game.");
                            printWriter.println("The secret value: " + secretNumber);
                            printWriter.println("Game ended.");
                        }*/
                        gameActive = false;
                    }else if (userGuess < secretNumber) {
                        printWriter.println("Try again. The secret number is higher.");
                    } else {
                        printWriter.println("Try again. The secret number is lower.");
                    }
                } catch (NumberFormatException e) {
                    printWriter.println("Please enter a valid number.");
                }
            }

            System.out.println("Client number: " + nClient + " disconnected.");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
