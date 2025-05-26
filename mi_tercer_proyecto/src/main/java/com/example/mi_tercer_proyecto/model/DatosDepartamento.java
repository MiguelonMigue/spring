package com.example.mi_tercer_proyecto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosDepartamento(
        Integer id,
        @JsonAlias("name") String nombre,
        @JsonAlias("cityCapital") DatosCapital capital,
        @JsonAlias("phonePrefix") Integer extension,
        @JsonAlias("municipalities") Integer municipios
        ) {
}
