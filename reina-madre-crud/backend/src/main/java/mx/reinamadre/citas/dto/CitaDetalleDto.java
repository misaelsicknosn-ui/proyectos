package mx.reinamadre.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitaDetalleDto implements java.io.Serializable {
    private static final long serialVersionUID = -1793320710194151155L;

    private String fechaHoraCita;
    private String paciente;
    private String tipoCita;
    private String nombreMedico;
    private String numeroCita;
    private String estatus;
}