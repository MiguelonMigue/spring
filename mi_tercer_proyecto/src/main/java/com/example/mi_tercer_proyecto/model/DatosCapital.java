package com.example.mi_tercer_proyecto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosCapital(
        @JsonAlias("name")String nombre,
        Integer id,
        @JsonAlias("population")Integer poblacion
){

}
