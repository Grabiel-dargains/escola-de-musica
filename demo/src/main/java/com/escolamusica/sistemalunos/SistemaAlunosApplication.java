package com.escolamusica.sistemalunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta anotação é a chave de tudo!
@SpringBootApplication
public class SistemaAlunosApplication {

    // Este é o método padrão que o Java procura para iniciar um programa.
    public static void main(String[] args) {
        // Esta linha diz ao Spring para iniciar a aplicação.
        SpringApplication.run(SistemaAlunosApplication.class, args);
    }

}