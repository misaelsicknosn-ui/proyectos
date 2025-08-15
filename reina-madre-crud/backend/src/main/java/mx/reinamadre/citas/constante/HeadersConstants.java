package mx.reinamadre.citas.constante;

/**
 * Clase que contiene constantes relacionadas con las cabeceras HTTP y tokens JWT.
 */
public class HeadersConstants {
    /**
     * Nombre de la cabecera de autorización.
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * Prefijo para tokens de tipo Bearer.
     */
    public static final String BEARER = "Bearer ";

    /**
     * Nombre del claim que contiene el ID del usuario en el token JWT.
     */
    public static final String CLAIM_ID_USUARIO = "idUsuario";

    /**
     * Constructor privado para evitar instanciación.
     */
    private HeadersConstants() {}
}
