package mx.reinamadre.citas.exception;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import mx.reinamadre.citas.dto.response.ResponseGeneralDTO;
import mx.reinamadre.citas.enumeraciones.TipoAlertaE;
import mx.reinamadre.citas.utilerias.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la aplicación.
 * Procesa todas las excepciones no capturadas y devuelve respuestas adecuadas.
 */
@Hidden // Oculta este manejador en la documentación Swagger
@RestControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    /**
     * Utilidad para obtener mensajes configurados en la aplicación.
     */
    @Autowired
    private Mensajes mensajes;

    /**
     * Maneja excepciones de tipo RuntimeException.
     *
     * @param ex La excepción capturada
     * @return ResponseEntity con información del error
     */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseGeneralDTO> handleRuntimeException(RuntimeException ex) {
		log.error(ex.getMessage(),ex);
		ResponseGeneralDTO errorResponse =  mensajes.getMensaje("ERR_500", TipoAlertaE.DANGER);
		errorResponse.setCodigo(ResponseGeneralDTO.CODIGO_ERROR_TECNICO);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

    /**
     * Maneja excepciones genéricas de tipo Exception.
     *
     * @param ex La excepción capturada
     * @return ResponseEntity con información del error
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseGeneralDTO> handleAllExceptions(Exception ex) {
		log.error(ex.getMessage(),ex);
		ResponseGeneralDTO errorResponse =  mensajes.getMensaje("ERR_500",TipoAlertaE.DANGER);
		errorResponse.setCodigo(ResponseGeneralDTO.CODIGO_ERROR_TECNICO);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

    /**
     * Maneja excepciones por cabeceras faltantes en las peticiones.
     *
     * @param ex La excepción capturada
     * @return ResponseEntity con información del error
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseGeneralDTO> handleMissingHeaders(MissingRequestHeaderException ex) {
		log.error(ex.getMessage());
		ResponseGeneralDTO errorResponse =  mensajes.getMensaje("ERR_400",TipoAlertaE.DANGER);
		errorResponse.setCodigo(ResponseGeneralDTO.CODIGO_ERROR_TECNICO);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Maneja excepciones de negocio personalizadas.
     *
     * @param ex La excepción de negocio capturada
     * @return ResponseEntity con información del error de negocio
     */
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ResponseGeneralDTO> handleAllExceptions(NegocioException ex) {
		ResponseGeneralDTO errorResponse;

		if (ex.getMensaje() == null || ex.getMensaje().isEmpty()) {
			errorResponse =  mensajes.getMensaje(ex.getCodigo());

			if(ex.getTipoAlerta() != null) {
				errorResponse.setTipoAlerta(ex.getTipoAlerta().getDescripcion());
			}
		} else{
			errorResponse = ResponseGeneralDTO.builder()
					.mensaje(ex.getMensaje())
					.tipoAlerta(ex.getTipoAlerta() != null ? ex.getTipoAlerta().getDescripcion() : TipoAlertaE.DANGER.getDescripcion())
					.build();
		}

		errorResponse.setCodigo(ResponseGeneralDTO.CODIGO_ERROR_NEGOCIO);
		log.error(errorResponse.getMensaje());
		return ResponseEntity.status(ex.getStatus()).body(errorResponse);
	}

}
