package mx.reinamadre.citas.configuracion;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger para la documentación de la API.
 * Esta clase configura la interfaz de Swagger UI para mostrar la documentación de los endpoints de la API.
 * 
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura la información básica de la API para Swagger.
     * 
     * @return OpenAPI configurada con la información de la API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ms-catalogos API")
                        .version("1.0.0")
                        .description("Documentación de la API para el microservicio ms-citas. Este microservicio proporciona acceso a los diferentes Citas.")
                        .termsOfService("https://reinamadre.mx/")
                        .contact(new Contact()
                                .name("REINA MADRE - Soporte Técnico")
                                .url("https://reinamadre.mx/")
                                .email("soporte@reinamadre.mx"))
                        .license(new License()
                                .name("Uso exclusivo de REINA MADRE")
                                .url("https://reinamadre.mx/"))
                )
                .addServersItem(new Server().url("/").description("Servidor actual"));
    }
}
