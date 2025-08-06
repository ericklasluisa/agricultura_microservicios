package ec.edu.espe.ms_central.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MensajeColaDto {
  private String evento;
  private String cosecha_id;
  private String producto;
  private double toneladas;
  private LocalDateTime timestamp;
}
