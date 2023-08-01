package com.example.ipr.udp;

import com.example.ipr.utils.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class udpEchoClient {

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getLocalHost();
            byte[] sendData;
            byte[] receiveData = new byte[Constants.BUFFER_SIZE];

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Введи сообщение ('выход' для завершения): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("выход")) {
                    break;
                }

                sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, Constants.PORT);
                socket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Сервак: " + response);
            }

            System.out.println("Стопим клиент.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
