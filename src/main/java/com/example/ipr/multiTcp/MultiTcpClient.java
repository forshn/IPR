package com.example.ipr.multiTcp;

import com.example.ipr.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiTcpClient {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 5; i++) {
                final int clientId = i + 1;
                executor.submit(() -> {
                    try (Socket socket = new Socket(Constants.IP, Constants.PORT);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                        String message = "Привет от кл " + clientId;
                        out.println(message);

                        String response = in.readLine();
                        System.out.println("Ответ сервера: " + response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            executor.shutdown();
        }
    }
}
