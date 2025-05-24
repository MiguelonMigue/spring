package Miguelon.Mi_segundo_proyecto.service;

public interface IConversionDatos {
    <T> T obtenerDatos(String json,Class<T> clase);
}
