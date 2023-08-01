package com.example.ipr.multiTcp;

import com.example.ipr.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiTcpServer {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(Constants.NUM_THREADS);

        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT)) {
            System.out.println("Сервер запущен");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключен клиент: " + clientSocket.getInetAddress().getHostAddress());

                executor.execute(() -> {
                    try (
                            Socket socket = clientSocket;
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                    ) {
                        String message = in.readLine();
                        System.out.println("Сообщение от клиента: " + message);

                        String processedMessage = message.toUpperCase();

                        out.println(processedMessage);
                        System.out.println("Отправлено сообщение клиенту: " + processedMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                            System.out.println("Клиент отключен: " + clientSocket.getInetAddress().getHostAddress());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
