package mx.reinamadre.citas.utilerias;

import mx.reinamadre.citas.dto.response.ResponseGeneralDTO;
import mx.reinamadre.citas.enumeraciones.TipoAlertaE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

/**
 * Utilidad para gestionar los mensajes de la aplicación.
 * Carga los mensajes desde un archivo de propiedades y proporciona métodos para obtenerlos.
 */
@Configuration
public class Mensajes {

	/**
	 * Recurso que contiene los mensajes de la aplicación.
	 */
	@Value("classpath:mensajes.yml")
    private Resource esResource;

	/**
	 * Propiedades cargadas desde el archivo de mensajes.
	 */
	private Properties esProperties;

	/**
	 * Inicializa las propiedades cargando los mensajes desde el archivo.
	 *
	 * @throws IOException Si ocurre un error al cargar el archivo
	 */
    @PostConstruct
    public void loadProperties() throws IOException {
        esProperties = new Properties();
        esProperties.load(esResource.getInputStream());
    }

    /**
     * Obtiene un mensaje por su código.
     *
     * @param code Código del mensaje
     * @return ResponseGeneralDTO con el mensaje
     */
    public ResponseGeneralDTO getMensaje(String code) {
    	return ResponseGeneralDTO.builder()
    			.codigo(ResponseGeneralDTO.CODIGO_ERROR_NEGOCIO)
    			.mensaje(esProperties.getProperty(code))
    			.build();
    }

    /**
     * Obtiene un mensaje por su código y tipo de alerta.
     *
     * @param code Código del mensaje
     * @param alerta Tipo de alerta
     * @return ResponseGeneralDTO con el mensaje y tipo de alerta
     */
    public ResponseGeneralDTO getMensaje(String code, TipoAlertaE alerta) {
    	return ResponseGeneralDTO.builder()
    			.codigo(ResponseGeneralDTO.CODIGO_ERROR_NEGOCIO)
    			.mensaje(esProperties.getProperty(code))
    			.tipoAlerta(alerta.getDescripcion())
    			.build();
    }

    /**
     * Obtiene un mensaje por su código y tipo de alerta, con parámetros para formatear el mensaje.
     *
     * @param code Código del mensaje
     * @param alerta Tipo de alerta
     * @param parametros Parámetros para formatear el mensaje
     * @return ResponseGeneralDTO con el mensaje formateado y tipo de alerta
     */
    public ResponseGeneralDTO getMensaje(String code, TipoAlertaE alerta, String... parametros) {
    	final String mensaje = esProperties.getProperty(code);
    	return ResponseGeneralDTO.builder()
    			.codigo(ResponseGeneralDTO.CODIGO_ERROR_NEGOCIO)
    			.mensaje(String.format(mensaje,(Object[])parametros))
    			.tipoAlerta(alerta.getDescripcion())
    			.build();
    }

    /**
     * Obtiene un mensaje por su código, con parámetros para formatear el mensaje.
     *
     * @param code Código del mensaje
     * @param parametros Parámetros para formatear el mensaje
     * @return ResponseGeneralDTO con el mensaje formateado
     */
    public ResponseGeneralDTO getMensaje(String code, String... parametros) {
    	final String mensaje = esProperties.getProperty(code);
    	return ResponseGeneralDTO.builder()
    			.codigo(ResponseGeneralDTO.CODIGO_ERROR_NEGOCIO)
    			.mensaje(String.format(mensaje,(Object[])parametros))
    			.build();
    }

}
