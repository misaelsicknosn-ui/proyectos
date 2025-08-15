package mx.reinamadre.citas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO (Data Transfer Object) que representa una cita Pecuaria (UPP).
 * Contiene información sobre el predio, productor y ubicación geográfica de la unidad.
 * 
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto que representa una cita")
public class CitaRequestDto implements Serializable {
    private static final long serialVersionUID = 1449623074395154451L;

    private String fechaHoraCita;
    private String paciente;
    private String tipoCita;
    private String nombreMedico;
    private String numeroCita;
}
