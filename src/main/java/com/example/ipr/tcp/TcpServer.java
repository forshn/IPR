package com.example.ipr.tcp;

import com.example.ipr.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Сервер создает экземпляр объекта ServerSocket, определяющий, по какому номеру порта должна происходить связь.
 * Сервер вызывает метод accept() класса ServerSocket. Этот метод ожидает, пока клиент не подключится к серверу по указанному порту.
 * По завершению ожидания сервера клиент создает экземпляр объекта сокета, указывая имя сервера и номер порта подключения.
 * Конструктор класса Socket осуществляет попытку подключить клиента к указанному серверу и номеру порта. Если связь установлена, у клиента теперь есть объект Socket, способный связываться с сервером.
 * На стороне сервера метод accept() возвращает ссылку к новому сокету на сервере, который подключен к клиентскому сокету.
 */
public class TcpServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.PORT);
            System.out.println("Сервер запущен. Ожидание подключения клиента...");

            Socket clientSocket = serverSocket.accept(); //Когда код сервера встречает метод accept , он блокируется до тех пор, пока клиент не отправит ему запрос на подключение.
            System.out.println("Клиент подключен. IP: " + clientSocket.getInetAddress());

            // Получение потоков ввода-вывода для обмена данными с клиентом
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String question;
            do {
                System.out.print("Введите вопрос (или 'выход' для завершения): ");
                question = new Scanner(System.in).nextLine();
                out.println(question);

                String response = in.readLine();
                System.out.println("Ответ сервера: " + response);

            } while (!question.equalsIgnoreCase("выход"));

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
