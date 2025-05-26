package com.example.mi_tercer_proyecto.service;

public interface IConversorDatos {
    public <T> T obtenerDatos(String json, Class<T> clase);
}
