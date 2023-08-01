package com.example.ipr.factory;

public class ProductFactory {

    /**
     * пример использование в коде - класс Action Factory в 102
     */

    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ProductA();
        } else if (type.equals("B")) {
            return new ProductB();
        } else {
            throw new IllegalArgumentException("Неподдерживаемый тип продукта");
        }
    }
}
