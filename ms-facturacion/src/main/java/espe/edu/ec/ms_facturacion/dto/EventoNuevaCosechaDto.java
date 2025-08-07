package espe.edu.ec.ms_facturacion.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventoNuevaCosechaDto {
    private UUID eventId;
    private String eventType;
    private LocalDateTime timestamp;
    private Payload payload;

    @Data
    public static class Payload {
        private UUID cosechaId;
        private String producto;
        private double toneladas;
    }
}
