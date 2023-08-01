package com.example.ipr;

import com.example.ipr.command.CommandA;
import com.example.ipr.command.Invoker;
import com.example.ipr.factory.Product;
import com.example.ipr.factory.ProductFactory;
import com.example.ipr.strategy.ShippingStrategy;
import com.example.ipr.strategy.ShippingStrategy1;
import com.example.ipr.strategy.ShippingStrategy2;
import com.example.ipr.tcp.TcpServer;
import com.example.ipr.tcp.tcpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IprApplication {

    public static void main(String[] args) {
        SpringApplication.run(IprApplication.class, args);

       /* ShippingStrategy strategy = new ShippingStrategy2();
        ShippingStrategy strategy2 = new ShippingStrategy1();

        strategy.execute();
        strategy2.execute();*/


       /* Product productA = ProductFactory.createProduct("A");
        productA.execute();

        Product productB = ProductFactory.createProduct("B");
        productB.execute();*/

        Invoker invoker = new Invoker();
        invoker.setCommand(new CommandA("say something"));
        invoker.executeCommand();
    }
}
