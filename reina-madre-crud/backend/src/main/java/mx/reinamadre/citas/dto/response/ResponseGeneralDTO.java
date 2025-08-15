package mx.reinamadre.citas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mx.reinamadre.citas.enumeraciones.TipoAlertaE;

/**
 * DTO base para respuestas generales del sistema.
 * Contiene campos comunes para todas las respuestas como código, mensaje y tipo de alerta.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO base para respuestas generales del sistema")
public class ResponseGeneralDTO implements java.io.Serializable {
    private static final long serialVersionUID = 5222812197951745816L;

    /**
     * Código de respuesta para operaciones exitosas.
     */
    public static final int CODIGO_OK = 0;

    /**
     * Código de respuesta para errores de negocio.
     */
    public static final int CODIGO_ERROR_NEGOCIO = 1;

    /**
     * Código de respuesta para errores técnicos.
     */
    public static final int CODIGO_ERROR_TECNICO = -1;

    /**
     * Código de respuesta de la operación.
     */
    @Schema(description = "Código de respuesta de la operación", example = "0")
    private int codigo;

    /**
     * Mensaje descriptivo de la respuesta.
     */
    @Builder.Default
    @Schema(description = "Mensaje descriptivo de la respuesta", example = "OK")
    private String mensaje = "OK";

    /**
     * Tipo de alerta para mostrar en la interfaz de usuario.
     */
    @Builder.Default
    @Schema(description = "Tipo de alerta para mostrar en la interfaz de usuario", example = "success")
    private String tipoAlerta = TipoAlertaE.SUCCESS.getDescripcion();
}
