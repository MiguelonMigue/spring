package Miguelon.Mi_primer_proyecto.service;

public interface IConversorDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
