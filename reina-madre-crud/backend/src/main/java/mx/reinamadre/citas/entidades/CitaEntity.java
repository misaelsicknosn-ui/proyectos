package mx.reinamadre.citas.entidades;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHoraCita;
    private String paciente;
    private String tipoCita;
    private String nombreMedico;
    private String numeroCita;

    private Boolean estatus;//0=Borrado logico, 1=Activo
    private String usuarioAlta;
    private LocalDateTime fechaAlta;
    private String usuarioModificacion;
    private LocalDateTime fechaModificacion;
}
