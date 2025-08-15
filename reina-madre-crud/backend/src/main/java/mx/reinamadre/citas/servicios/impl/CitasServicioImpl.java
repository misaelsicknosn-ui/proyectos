package mx.reinamadre.citas.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.reinamadre.citas.dto.CitaDetalleDto;
import mx.reinamadre.citas.dto.CitaRequestDto;
import mx.reinamadre.citas.dto.response.CitasResponseDto;
import mx.reinamadre.citas.dto.response.ResponseGeneralDTO;
import mx.reinamadre.citas.entidades.CitaEntity;
import mx.reinamadre.citas.enumeraciones.TipoAlertaE;
import mx.reinamadre.citas.exception.ErrorException;
import mx.reinamadre.citas.exception.NegocioException;
import mx.reinamadre.citas.repositorios.CitasRepository;
import mx.reinamadre.citas.servicios.CitasServicio;
import mx.reinamadre.citas.utilerias.Formateador;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementación del servicio para gestionar citas.
 * 
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CitasServicioImpl implements CitasServicio {

    /**
     * Repositorio para acceder a los datos de citas.
     */
    private final CitasRepository citasRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CitasResponseDto obtenerCitasTodas() throws ErrorException {
        CitasResponseDto citasResponseDto = new CitasResponseDto();
        try {
            List<CitaEntity> citaEntityList = citasRepository.findAll();
            if(citaEntityList != null && !citaEntityList.isEmpty()){
                citasResponseDto.setCitaDetalleDtoList(new ArrayList<>());
                for(CitaEntity elem: citaEntityList){
                    CitaDetalleDto citaDetalleDto = new CitaDetalleDto();
                    citaDetalleDto.setFechaHoraCita(Formateador.localDateTimeAFechaddmmyyyyhhmmss(elem.getFechaHoraCita()));
                    citaDetalleDto.setPaciente(elem.getPaciente());
                    citaDetalleDto.setTipoCita(elem.getTipoCita());
                    citaDetalleDto.setNombreMedico(elem.getNombreMedico());
                    citaDetalleDto.setNumeroCita(elem.getNumeroCita());
                    citaDetalleDto.setEstatus(elem.getEstatus()?"Activa":"Eliminada");
                    citasResponseDto.getCitaDetalleDtoList().add(citaDetalleDto);
                }
            } else {
                throw new NegocioException("1", "No se encontraron datos.", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }
            return citasResponseDto;
        } catch (Exception e){
            log.error("Ocurrio un error al consultas las citas:", e);
            if(e instanceof NegocioException)
                throw e;
            throw new ErrorException("Error al obtener los datos de la cita", e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseGeneralDTO guardarCita(CitaRequestDto cita) throws ErrorException {
        try {
            ResponseGeneralDTO responseGeneralDTO = new CitasResponseDto();
            if(cita == null){
                throw new NegocioException("1", "El parametro 'cita' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }
            if(cita.getFechaHoraCita() == null){
                throw new NegocioException("1", "El parametro 'fechaHoraCita' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }
            if(cita.getPaciente() == null){
                throw new NegocioException("1", "El parametro 'paciente' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }
            if(cita.getTipoCita() == null){
                throw new NegocioException("1", "El parametro 'tipoCita' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }
            if(cita.getNombreMedico() == null){
                throw new NegocioException("1", "El parametro 'nombreMedico' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }

            LocalDateTime fechaHoraCita = Formateador.stringALocalDateTime(cita.getFechaHoraCita());
            List<CitaEntity> citaEntityList = citasRepository.findByFechaHoraCitaAndEstatus(fechaHoraCita, Boolean.TRUE);

            if (citaEntityList != null && citaEntityList.size()>0) {
                throw new NegocioException("1", "Ya existe una cita con esta Fecha y Hora. ", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }

            CitaEntity citaEntity = new CitaEntity();
            citaEntity.setFechaHoraCita(fechaHoraCita);
            citaEntity.setPaciente(cita.getPaciente());
            citaEntity.setTipoCita(cita.getTipoCita());
            citaEntity.setNombreMedico(cita.getNombreMedico());
            citaEntity.setNumeroCita("NO-"+Formateador.dateAFechayyyymmddhhmmss(new Date()));
            citaEntity.setEstatus(Boolean.TRUE);
            citaEntity.setUsuarioAlta("administrador@reinamadre.mx");
            citaEntity.setFechaAlta(LocalDateTime.now());
            citaEntity.setUsuarioModificacion("administrador@reinamadre.mx");
            citaEntity.setFechaModificacion(LocalDateTime.now());

            citasRepository.save(citaEntity);
            responseGeneralDTO.setCodigo(0);
            responseGeneralDTO.setMensaje("Se dio de alta correctamente.");
            responseGeneralDTO.setTipoAlerta(TipoAlertaE.SUCCESS.getDescripcion());
            return responseGeneralDTO;
        } catch (Exception e){
            log.error("Ocurrio un error al crear una cita:", e);
            if(e instanceof NegocioException)
                throw e;
            throw new ErrorException("Error al guardar cita", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseGeneralDTO actualizarCita(CitaRequestDto cita) throws ErrorException {
        try {
            ResponseGeneralDTO responseGeneralDTO = new CitasResponseDto();
            if (cita == null) {
                throw new NegocioException("1", "El parametro 'cita' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }
            if (cita.getNumeroCita() == null) {
                throw new NegocioException("1", "El parametro 'numeroCita' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }

            LocalDateTime fechaHoraCita = Formateador.stringALocalDateTime(cita.getFechaHoraCita());
            List<CitaEntity> citaEntityList = citasRepository.findByNumeroCitaAndEstatus(cita.getNumeroCita(), Boolean.TRUE);

            if (citaEntityList == null || citaEntityList.size() == 0) {
                throw new NegocioException("1", "No existe una cita. ", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }

            CitaEntity citaEntity = citaEntityList.get(0);
            citaEntity.setFechaHoraCita(fechaHoraCita);
            citaEntity.setPaciente(cita.getPaciente());
            citaEntity.setTipoCita(cita.getTipoCita());
            citaEntity.setNombreMedico(cita.getNombreMedico());
            citaEntity.setUsuarioModificacion("administrador@reinamadre.mx");
            citaEntity.setFechaModificacion(LocalDateTime.now());

            citasRepository.save(citaEntity);
            responseGeneralDTO.setCodigo(0);
            responseGeneralDTO.setMensaje("Se actualizó correctamente.");
            responseGeneralDTO.setTipoAlerta(TipoAlertaE.SUCCESS.getDescripcion());
            return responseGeneralDTO;
        } catch (Exception e) {
            log.error("Ocurrio un error general eliminar la cita", e);
            if (e instanceof NegocioException)
                throw e;
            throw new ErrorException("Error al eliminar cita", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseGeneralDTO eliminarCita(CitaRequestDto cita) throws ErrorException {
        try {
            ResponseGeneralDTO responseGeneralDTO = new CitasResponseDto();
            if(cita == null){
                throw new NegocioException("1", "El parametro 'cita' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }
            if (cita.getNumeroCita() == null) {
                throw new NegocioException("1", "El parametro 'numeroCita' es obligatorio", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }

            List<CitaEntity> citaEntityList = citasRepository.findByNumeroCitaAndEstatus(cita.getNumeroCita(), Boolean.TRUE);

            if (citaEntityList == null || citaEntityList.size()==0) {
                throw new NegocioException("1", "No existe una cita. ", HttpStatus.CONFLICT, TipoAlertaE.DANGER);
            }

            CitaEntity citaEntity = citaEntityList.get(0);

            citaEntity.setEstatus(Boolean.FALSE);
            citaEntity.setUsuarioModificacion("administrador@reinamadre.mx");
            citaEntity.setFechaModificacion(LocalDateTime.now());

            citasRepository.save(citaEntity);
            responseGeneralDTO.setCodigo(0);
            responseGeneralDTO.setMensaje("Se elimino correctamente.");
            responseGeneralDTO.setTipoAlerta(TipoAlertaE.SUCCESS.getDescripcion());
            return responseGeneralDTO;
        } catch (Exception e){
            log.error("Ocurrio un error general eliminar la cita", e);
            if(e instanceof NegocioException)
                throw e;
            throw new ErrorException("Error al eliminar cita", e);
        }
    }
}
