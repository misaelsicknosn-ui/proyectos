package mx.reinamadre.citas.exception;

import java.io.Serializable;

/**
 * Excepción general para errores en la aplicación.
 * Se utiliza para representar errores genéricos que pueden ocurrir durante la ejecución.
 * 
 * @version 1.0
 */
public class ErrorException extends Exception implements Serializable {

    /**
     * Número de serie para la serialización.
     */
    private static final long serialVersionUID = -1114967179996599613L;

    /**
     * Constructor con mensaje y causa.
     *
     * @param mensaje Mensaje descriptivo del error
     * @param cause Causa original del error
     */
    public ErrorException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }
}
