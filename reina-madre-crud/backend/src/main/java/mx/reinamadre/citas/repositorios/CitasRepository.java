package mx.reinamadre.citas.repositorios;

import mx.reinamadre.citas.entidades.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitasRepository extends JpaRepository<CitaEntity, Long> {
    List<CitaEntity> findByEstatus(Boolean estatus);
    List<CitaEntity> findByFechaHoraCitaAndEstatus(LocalDateTime fechaHoraCita, Boolean estatus);
    List<CitaEntity> findByNumeroCitaAndEstatus(String numeroCita, Boolean estatus);
}