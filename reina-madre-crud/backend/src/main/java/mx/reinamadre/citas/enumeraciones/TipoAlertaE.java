package mx.reinamadre.citas.enumeraciones;

/**
 * Enumeración que define los diferentes tipos de alertas utilizados en el sistema.
 * Cada tipo de alerta tiene una descripción asociada que se utiliza en la interfaz de usuario.
 * 
 * @version 1.0
 */
public enum TipoAlertaE {

    /**
     * Alerta de éxito, utilizada para operaciones completadas correctamente.
     */
    SUCCESS("success"),

    /**
     * Alerta informativa, utilizada para mensajes informativos.
     */
    INFO("info"),

    /**
     * Alerta de advertencia, utilizada para situaciones que requieren atención.
     */
    WARNING("warning"),

    /**
     * Alerta de peligro, utilizada para errores o situaciones críticas.
     */
    DANGER("danger");

    /**
     * Descripción del tipo de alerta.
     */
    private final String descripcion;

    /**
     * Constructor de la enumeración.
     *
     * @param descripcion Descripción del tipo de alerta
     */
    TipoAlertaE(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción del tipo de alerta.
     *
     * @return Descripción del tipo de alerta
     */
    public String getDescripcion() {
        return descripcion;
    }
}
