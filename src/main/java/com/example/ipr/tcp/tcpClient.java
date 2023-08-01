package com.example.ipr.tcp;

import com.example.ipr.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Наш текущий сервер блокируется до тех пор, пока к нему не подключится клиент, а затем снова блокируется,
 * чтобы прослушать сообщение от клиента. После одного сообщения соединение закрывается, потому что мы не рассмотрели непрерывность.
 *
 * Таким образом, это полезно только в запросах ping.
 */
public class tcpClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(Constants.IP, Constants.PORT);
            System.out.println("Подключаем сервак " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String command = "Привет";
            out.println(command);
            System.out.println("Отправлена команда на сервер: " + command);

            String response = in.readLine();
            System.out.println("Получен ответ от сервера: " + response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
