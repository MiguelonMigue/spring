package com.example.desafio_spring.principal;

import com.example.desafio_spring.model.DatosPerro;
import com.example.desafio_spring.service.ConsumoAPI;
import com.example.desafio_spring.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String url = "https://dog.ceo/api/breeds/list/all";
    private ConvierteDatos conversor = new ConvierteDatos();
    Scanner scanner = new Scanner(System.in);
    public void muestraElMenu() {
        var json = consumoApi.obtenerDatos(url);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosPerro.class);
        System.out.println(datos);


        ((Map<String, ?>) datos.mensaje()).keySet().stream()
                .limit(10)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("\nIngrese el nombre de la raza que desea buscar:");
        var nombreRaza = scanner.nextLine().toLowerCase();
        Map<String, ?> razas = (Map<String, ?>) datos.mensaje();

        if (razas.containsKey(nombreRaza)) {
            System.out.println("¡Raza encontrada! Subrazas: " + razas.get(nombreRaza));
        } else {
            System.out.println("La raza '" + nombreRaza + "' no fue encontrada.");




        }
    }
}
