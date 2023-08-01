package com.example.ipr.udp;

import com.example.ipr.utils.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * UDP по большому счёту работает быстрее, но возможно что будут теряться пакеты информации. Исопльзуется например в видеоПередаче
 *
 * Создание приложений UDP очень похоже на создание системы TCP;
 * единственная разница в том, что мы не устанавливаем соединение точка-точка между клиентом и сервером.
 */
public class UdpEcho {

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(Constants.PORT)) {
            System.out.println("Сервер запущен. Ждём вопросики...");

            while (true) {
                byte[] buffer = new byte[Constants.BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String request = new String(packet.getData(), 0, packet.getLength());
                System.out.println("запрос от клиента: " + request);
                String response = "Ну " + request;

                byte[] responseData = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                        packet.getAddress(), packet.getPort());

                socket.send(responsePacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
