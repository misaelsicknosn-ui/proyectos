package mx.reinamadre.citas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mx.reinamadre.citas.dto.CitaDetalleDto;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CitasResponseDto extends ResponseGeneralDTO implements java.io.Serializable {
    private static final long serialVersionUID = 7549912899056257174L;

    private List<CitaDetalleDto> citaDetalleDtoList;
}