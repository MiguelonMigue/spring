package com.example.desafio_spring;

import com.example.desafio_spring.principal.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSpringApplication.class, args);
	}
	@Override
    public void run(String...args) throws Exception{
		Main main = new Main();
		main.muestraElMenu();
	}

}
