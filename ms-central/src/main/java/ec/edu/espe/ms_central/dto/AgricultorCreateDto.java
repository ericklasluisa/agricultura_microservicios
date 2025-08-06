package ec.edu.espe.ms_central.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AgricultorCreateDto {

  @NotBlank(message = "El nombre es obligatorio")
  private String nombre;

  @NotBlank(message = "La finca es obligatoria")
  private String finca;

  @NotBlank(message = "La ubicación es obligatoria")
  private String ubicacion;

  @Email(message = "El correo debe tener un formato válido")
  @NotBlank(message = "El correo es obligatorio")
  private String correo;
}
