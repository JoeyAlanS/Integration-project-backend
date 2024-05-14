package com.eletra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Testando {
    public static void main(String[] args) {
        SpringApplication.run(Testando.class, args);
        System.out.println("Está executando");
    }

}