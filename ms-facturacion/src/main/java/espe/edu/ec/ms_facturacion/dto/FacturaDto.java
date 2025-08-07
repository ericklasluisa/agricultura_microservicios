package espe.edu.ec.ms_facturacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDto {
    private UUID facturaId;
    private UUID cosechaId;
    private double montoTotal;
    private Boolean pagado;
    private LocalDateTime fechaEmision;
    private String metodoPago;
    private String codigoQr;
}

