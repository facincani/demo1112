package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pessoa {

    private Integer id;
    private String nome;
    private Integer idade;

}
