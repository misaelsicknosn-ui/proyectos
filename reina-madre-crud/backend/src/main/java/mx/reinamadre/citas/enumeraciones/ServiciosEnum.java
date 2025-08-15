package mx.reinamadre.citas.enumeraciones;

import lombok.Getter;

/**
 * Enumeración que define los diferentes servicios de API disponibles en el sistema.
 * Cada servicio tiene un nombre asociado que se utiliza para identificarlo.
 * 
 * @version 1.0
 */
@Getter
public enum ServiciosEnum {
    /**
     * Servicio de API Gateway.
     */
    API_GATEWAY("gateway"),

    /**
     * Servicio de autenticación.
     */
    API_AUTHENTICATION("autenticacion"),

    /**
     * Servicio de catálogos.
     */
    API_CATALOGOS("catalogos"),

    /**
     * Servicio core o principal.
     */
    API_CORE("core"),

    /**
     * Servicio común.
     */
    API_COMUN("comun"),

    /**
     * Servicio de usuarios.
     */
    API_USUARIOS("usuarios");

    /**
     * Nombre del servicio.
     */
    private final String nombre;

    /**
     * Constructor de la enumeración.
     *
     * @param nombre Nombre del servicio
     */
    ServiciosEnum(String nombre) {
        this.nombre = nombre;
    }
}
