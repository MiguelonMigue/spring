package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=4fc7c187";
    private ConvierteDatos conversor = new ConvierteDatos();
    public void muestraElMenu(){
        System.out.println("Escribe el nombre de la série que deseas buscar");
        //Busca los datos generales de las series
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        //https://www.omdbapi.com/?t=game+of+thrones&apikey=4fc7c187
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= datos.totalTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);
        //Mostrar solo el titulo de los episodios para las temporadas
        for (int i = 0; i < datos.totalTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporadas.size(); j++) {
                System.out.println(episodiosTemporadas.get(j).titulo());
            }
        }
        // Mejoría usando funciones Lambda
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        // Obtener los top 5 episodios
//        System.out.println("\n Top 5 episodios");
//        datosEpisodios.stream()
//                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primer filtro de las series " + e))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .peek(e -> System.out.println("Segundo filtro de mayor a menor" + e))
//                .limit(5)
//                .peek(e-> System.out.println("Número de límite de los episodios " + e))
//                .forEach(System.out::println);

       // Convirtiendo los datos a una lista del tipo Episodio
       List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());

        // episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de x año
//        System.out.println("a partir de que año deseas ver los episodios?");
//        var fecha = teclado.nextInt();
//        teclado.nextLine();
//
//        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episodio: " + e.getTitulo() +
//                                " Fecha de Lanzamiento: " + e.getFechaDeLanzamiento().format(dtf)
//                ));


//        System.out.println("Por favor escriba el episodio que desea ver");
//        var pedazoEpisodio = teclado.nextLine();
//        Optional<Episodio>episodioBuscado = episodios.stream()
//                .filter(e->e.getTitulo().toUpperCase().contains(pedazoEpisodio.toUpperCase()))
//                .findFirst();
//        if(episodioBuscado.isPresent()){
//            System.out.println("Episodio encontrado");
//            System.out.println("Datos del episodio: " + episodioBuscado.get() );
//        } else {
//            System.out.println("Episodio no encontrado");
//        }

        Map<Integer,Double> evaluacionPorTemporada = episodios.stream()
                .filter(e->e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));

        System.out.println(evaluacionPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e->e.getEvaluacion()>0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Esta es la  media de la evaluación " + est.getAverage());
        System.out.println("Sumatoria " +est.getSum());
        System.out.println("La máxima es " + est.getMax());
        System.out.println("Su mínima es " + est.getMin());
    }
}
