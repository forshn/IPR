package com.example.ipr.strategy;

public class ShippingStrategy2 implements ShippingStrategy  {

    @Override
    public void execute() {
        System.out.println("second strategy runs");
    }
}
