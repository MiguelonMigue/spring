package Miguelon.Mi_primer_proyecto;

import Miguelon.Mi_primer_proyecto.model.APIResponse;
import Miguelon.Mi_primer_proyecto.model.DatosCarro;
import Miguelon.Mi_primer_proyecto.service.ConsumidorDeApi;
import Miguelon.Mi_primer_proyecto.service.ConversionDatos;
import com.sun.source.tree.BreakTree;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiPrimerProyectoApplication  implements  CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(MiPrimerProyectoApplication.class,args);
	}


	@Override
	public void run(String... args) throws Exception {
	//	System.out.println("Hola mundo!!!");
		ConsumidorDeApi consumidorDeApi = new ConsumidorDeApi();
		var json = consumidorDeApi.obtenerDatos("https://carapi.app/api/models?year=2020&make=ford&json");
		//System.out.println(json);
		ConversionDatos conversionDatos = new ConversionDatos();
		var apiResponse = conversionDatos.obtenerDatos(json, APIResponse.class);
		for(var carro:apiResponse.getData()){
			System.out.println("id: " + carro.id() + ", " + "nombre: " +  carro.name());
		}

	}
}
