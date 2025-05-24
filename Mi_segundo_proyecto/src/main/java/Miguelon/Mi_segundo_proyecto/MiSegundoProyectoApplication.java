package Miguelon.Mi_segundo_proyecto;

import Miguelon.Mi_segundo_proyecto.model.APIReference;
import Miguelon.Mi_segundo_proyecto.service.ConsumidorApi;
import Miguelon.Mi_segundo_proyecto.service.ConversionDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiSegundoProyectoApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(MiSegundoProyectoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		ConsumidorApi ca = new ConsumidorApi();
		var json = ca.obtenerDatos("https://www.thesportsdb.com/api/v1/json/3/searchteams.php?t=Arsenal");
		ConversionDatos cd = new ConversionDatos();
		var apiReference = cd.obtenerDatos(json, APIReference.class);
		for(var team:apiReference.getTeams()){
			System.out.println("Id: " + team.idTeam() + ", nombre del equipo: " + team.strTeam() + ", nombres alternativos: " +  team.strTeamAlternate() + ", Liga: "+ team.strLeague());
		}

	}
}

