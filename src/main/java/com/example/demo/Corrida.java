package com.example.demo;

public class Corrida {

    public static void main(String[] args) {

        Cavalo cavalo1 = new Cavalo("azul");
        Cavalo cavalo2 = new Cavalo("amarelo");

        cavalo1.start();
        cavalo2.start();

        try {
            cavalo1.join();
            cavalo2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
