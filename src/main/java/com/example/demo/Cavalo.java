package com.example.demo;

import java.util.Random;

public class Cavalo extends Thread{

    private String nome;
    private Integer distancia = 0;

    public Cavalo(String nome){
        this.nome = nome;
    }

    @Override
    public void run(){
        Random r = new Random();
        while (distancia < 100){
            distancia += r.nextInt(0,10) ;
            System.out.println(nome + " correu até " + distancia);
        }

        try{
            Thread.sleep(100);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(nome + " teminou a corrida");

    }

}
