package com.example.mi_tercer_proyecto;

import com.example.mi_tercer_proyecto.principal.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiTercerProyectoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MiTercerProyectoApplication.class, args);
	}
		@Override
		public void run(String... args) throws Exception{
			Main main = new Main();
			main.muestraElMenu();
		}

}
