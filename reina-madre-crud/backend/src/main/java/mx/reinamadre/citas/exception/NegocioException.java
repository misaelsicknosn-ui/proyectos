package mx.reinamadre.citas.exception;

import lombok.Getter;
import mx.reinamadre.citas.enumeraciones.TipoAlertaE;
import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para errores de negocio.
 * Se utiliza para representar errores relacionados con la lógica de negocio de la aplicación.
 */
@Getter
public class NegocioException extends RuntimeException{

	private static final long serialVersionUID = 3342715734760561753L;

	/**
	 * Código de error.
	 */
	private final String codigo;

	/**
	 * Mensaje descriptivo del error.
	 */
	private String mensaje;

	/**
	 * Tipo de alerta asociada al error.
	 */
	private TipoAlertaE tipoAlerta;

	/**
	 * Estado HTTP asociado al error.
	 */
	private final HttpStatus status;

	/**
	 * Constructor con código y estado HTTP.
	 *
	 * @param code Código de error
	 * @param status Estado HTTP asociado al error
	 */
	public NegocioException(final String code, final HttpStatus status) {
		this.codigo = code;
		this.status = status;
	}

	/**
	 * Constructor con código, mensaje y estado HTTP.
	 *
	 * @param code Código de error
	 * @param mensaje Mensaje descriptivo del error
	 * @param status Estado HTTP asociado al error
	 */
	public NegocioException(final String code, final String mensaje, final HttpStatus status) {
		this.codigo = code;
		this.mensaje = mensaje;
		this.status = status;
	}

	/**
	 * Constructor con código, estado HTTP y tipo de alerta.
	 *
	 * @param code Código de error
	 * @param status Estado HTTP asociado al error
	 * @param tipoAlerta Tipo de alerta asociada al error
	 */
	public NegocioException(final String code, final HttpStatus status, final TipoAlertaE tipoAlerta) {
		this.codigo = code;
		this.status = status;
		this.tipoAlerta = tipoAlerta;
	}

	/**
	 * Constructor con código, mensaje, estado HTTP y tipo de alerta.
	 *
	 * @param code Código de error
	 * @param mensaje Mensaje descriptivo del error
	 * @param status Estado HTTP asociado al error
	 * @param tipoAlerta Tipo de alerta asociada al error
	 */
	public NegocioException(final String code, final String mensaje, final HttpStatus status, final TipoAlertaE tipoAlerta) {
		super(mensaje);
		this.codigo = code;
		this.mensaje = mensaje;
		this.status = status;
		this.tipoAlerta = tipoAlerta;
	}

}
