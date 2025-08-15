package mx.reinamadre.citas.servicios;

import mx.reinamadre.citas.dto.CitaRequestDto;
import mx.reinamadre.citas.dto.response.CitasResponseDto;
import mx.reinamadre.citas.dto.response.ResponseGeneralDTO;
import mx.reinamadre.citas.exception.ErrorException;

/**
 * Servicio para gestionar las citas.
 * 
 * @version 1.0
 */
public interface CitasServicio {

    CitasResponseDto obtenerCitasTodas() throws ErrorException;

    ResponseGeneralDTO guardarCita(CitaRequestDto cita) throws ErrorException;

    ResponseGeneralDTO actualizarCita(CitaRequestDto cita) throws ErrorException;
    ResponseGeneralDTO eliminarCita(CitaRequestDto cita) throws ErrorException;
}
