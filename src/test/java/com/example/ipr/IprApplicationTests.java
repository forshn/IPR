package com.example.ipr;

import com.example.ipr.multiTcp.MultiTcpServer;
import com.example.ipr.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IprApplicationTests {
    @Test
    public void testServerClientCommunication() throws IOException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Thread serverThread = new Thread(() -> MultiTcpServer.main(null));
        serverThread.start();

        TimeUnit.SECONDS.sleep(2); // Ждем, пока сервер запустится

        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                try (Socket socket = new Socket(Constants.IP, Constants.PORT);
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    // Отправляем сообщение на сервер
                    String message = "Проверка сервера";
                    out.println(message);

                    // Получаем обработанное сообщение от сервера
                    String processedMessage = in.readLine();

                    // Проверяем, что обработанное сообщение верно
                    assertEquals(message.toUpperCase(), processedMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        serverThread.interrupt();
    }

}
