package com.example.ipr.strategy;

public class ShippingStrategy1 implements ShippingStrategy  {

    @Override
    public void execute() {
        System.out.println("first strategy runs");
    }
}
