package com.example.ipr.CompleatableFutureEx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompleatableFutureEx {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Выполнили задачу " + Thread.currentThread().getName();
        });

        CompletableFuture<String> resultFuture = completableFuture.thenApply(String::toUpperCase);
        System.out.println(resultFuture.get());
    }
}
