package mx.reinamadre.citas.exception;

/**
 * Excepción para representar que no se encontraron catálogos.
 * Se lanza cuando se intenta acceder a un catálogo que no existe.
 */
public class NotFoundCatalogsException extends RuntimeException {

	private static final long serialVersionUID = -4706210667603463560L;

	/**
	 * Constructor con mensaje.
	 *
	 * @param message Mensaje descriptivo del error
	 */
	public NotFoundCatalogsException(String message) {
        super(message);
    }

}
