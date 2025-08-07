package ec.edu.espe.ms_inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsCentralMessageDto {
private String evento;
private String cosecha_id;
private String producto;
private double toneladas;
private LocalDateTime timestamp;
}
