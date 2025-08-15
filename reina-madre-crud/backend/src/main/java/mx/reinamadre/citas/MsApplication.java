package mx.reinamadre.citas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal de la aplicación de microservicio de catálogos.
 * Punto de entrada para iniciar el servicio Spring Boot.
 * 
 * @version 1.0
 */
@SpringBootApplication
@EnableJpaAuditing
public class MsApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 * 
	 * @param args Argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsApplication.class, args);
	}

}
