package ec.edu.espe.ms_central.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CosechaCreateDto {

  @NotBlank(message = "El producto es obligatorio")
  private String producto;

  @Positive(message = "Las toneladas deben ser un valor positivo")
  private double toneladas;

  @NotBlank(message = "El estado es obligatorio")
  private String estado;

  private Long facturaId;

  @NotNull(message = "El ID del agricultor es obligatorio")
  private Long agricultorId;
}
