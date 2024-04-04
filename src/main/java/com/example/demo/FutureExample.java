package com.example.demo;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Finalizado";
        };

        Future<String> future = executor.submit(task);

        System.out.println("teste");


        Thread.sleep(7000);

        System.out.println("terminou os 7 segundos");

        String resultado = future.get();
        System.out.println(resultado);

    }

}
