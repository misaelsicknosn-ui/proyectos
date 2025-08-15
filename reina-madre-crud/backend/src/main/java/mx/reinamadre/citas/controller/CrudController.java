package mx.reinamadre.citas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.reinamadre.citas.dto.CitaRequestDto;
import mx.reinamadre.citas.dto.response.CitasResponseDto;
import mx.reinamadre.citas.dto.response.ResponseGeneralDTO;
import mx.reinamadre.citas.exception.ErrorException;
import mx.reinamadre.citas.servicios.CitasServicio;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controlador para gestionar las citas.
 */
@RestController
@RequestMapping("/citas/crud")
@Tag(name = "Citas", description = "Endpoints para la gestión de citas")
@Validated
public class CrudController {

    /**
     * Servicio para gestionar las citas.
     */
    private final CitasServicio citasServicio;

    /**
     * Constructor del controlador.
     *
     * @param citasServicio Servicio de citas
     */
    public CrudController(CitasServicio citasServicio) {
        this.citasServicio = citasServicio;
    }

    /**
     * Obtiene una cita por su UPP.
     *
     * @return ResponseEntity con la información de la cita
     */
    @GetMapping(path = "/obtener-todas",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene cita por UPP", description = "Devuelve la información de la cita correspondiente al UPP proporcionado.",
        responses = {
            @ApiResponse(responseCode = "200", description = "cita encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró la cita"),
            @ApiResponse(responseCode = "406", description = "Error interno del servidor")
        })
    public ResponseEntity<CitasResponseDto> obtenerCitas() throws ErrorException {
        return ResponseEntity.ok(citasServicio.obtenerCitasTodas());
    }

    /**
     * Guarda una nueva cita.
     *
     * @param citaRequestDto Datos de la cita a guardar
     * @return ResponseEntity con la información de la cita guardada
     */
    @PostMapping(path = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Guarda una nueva cita", description = "Guarda una nueva cita y devuelve la información guardada.",
        responses = {
            @ApiResponse(responseCode = "200", description = "cita guardada correctamente"),
            @ApiResponse(responseCode = "409", description = "Datos inválidos para la cita"),
            @ApiResponse(responseCode = "406", description = "Error interno del servidor")
        })
    public ResponseEntity<ResponseGeneralDTO> guardarCita(@Valid @RequestBody CitaRequestDto citaRequestDto) throws ErrorException {
        return ResponseEntity.ok(citasServicio.guardarCita(citaRequestDto));
    }

    /**
     * Actualiza una cita existente.
     *
     * @param citaRequestDto Nuevos datos
     * @return ResponseEntity con la información de la cita actualizada
     */
    @PutMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza una cita", description = "Actualiza los datos de una cita existente.",
        responses = {
            @ApiResponse(responseCode = "200", description = "cita actualizada correctamente"),
            @ApiResponse(responseCode = "409", description = "Datos inválidos para la actualización"),
            @ApiResponse(responseCode = "406", description = "Error interno del servidor")
        })
    public ResponseEntity<ResponseGeneralDTO> actualizarCita(@RequestBody CitaRequestDto citaRequestDto) throws ErrorException {
        return ResponseEntity.ok(citasServicio.actualizarCita(citaRequestDto));
    }

    /**
     * Elimina una cita existente.
     *
     * @param citaRequestDto eliminar datos
     * @return ResponseEntity con la información de la cita actualizada
     */
    @DeleteMapping(path = "/eliminar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina una cita", description = "Elimina una cita existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "cita actualizada correctamente"),
                    @ApiResponse(responseCode = "409", description = "Datos inválidos para la actualización"),
                    @ApiResponse(responseCode = "406", description = "Error interno del servidor")
            })
    public ResponseEntity<ResponseGeneralDTO> eliminarCita(@RequestBody CitaRequestDto citaRequestDto) throws ErrorException {
        return ResponseEntity.ok(citasServicio.eliminarCita(citaRequestDto));
    }
}
