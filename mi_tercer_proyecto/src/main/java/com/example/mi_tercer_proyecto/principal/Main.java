package com.example.mi_tercer_proyecto.principal;

import com.example.mi_tercer_proyecto.model.DatosCapital;
import com.example.mi_tercer_proyecto.model.DatosDepartamento;
import com.example.mi_tercer_proyecto.service.ConsumoAPI;
import com.example.mi_tercer_proyecto.service.ConversorDatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String url = "https://api-colombia.com/api/v1/Department";
    private ConversorDatos conversor = new ConversorDatos();

    public void muestraElMenu() {
        System.out.println("Escribe el nombre del departamento que deseas buscar:");
        String nombreDepartamento = teclado.nextLine();

        String json = consumoApi.obtenerDatos(url);

        // Si la respuesta es un array de departamentos:
        DatosDepartamento[] departamentos = conversor.obtenerDatos(json, DatosDepartamento[].class);

        boolean encontrado = false;
        for (DatosDepartamento dep : departamentos) {
            if (dep.nombre().equalsIgnoreCase(nombreDepartamento)) {
                System.out.println("Departamento: " + dep.nombre());
                if (dep.capital() != null) {
                    System.out.println("Capital: " + dep.capital().nombre());
                    System.out.println("Población de la capital: " + dep.capital().poblacion());
                } else {
                    System.out.println("Este departamento no tiene información de capital.");
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Departamento no encontrado.");
        }
    }

}
