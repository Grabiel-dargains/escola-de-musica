package com.escolamusica.sistemalunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition(servers = { @Server(url="/", description="Default Server URL")})
@SpringBootApplication
public class SistemaAlunosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaAlunosApplication.class, args);
    }

}