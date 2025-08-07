package espe.edu.ec.ms_facturacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "factura")
@Getter
@Setter
@NoArgsConstructor
public class Factura {

    @Id
    @Column(name = "factura_id", updatable = false, nullable = false)
    private UUID facturaId;   // camelCase

    @Column(name = "cosecha_id")
    private UUID cosechaId;   // camelCase

    @Column(nullable = false, name = "monto_total")
    private Double montoTotal;

    @Column(nullable = false, name = "pagado")
    private Boolean pagado;

    @Column(nullable = false, name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(length = 30, name = "metodo_pago")
    private String metodoPago;

    @Column(name = "codigo_qr", columnDefinition = "TEXT")
    private String codigoQr;

    public Factura(UUID cosechaId, double montoTotal) {
        this.cosechaId = cosechaId;
        this.montoTotal = montoTotal;
    }

    @PrePersist
    public void prePersist() {
        if (this.facturaId == null) {
            
            this.facturaId = UUID.randomUUID();
        }
        if (this.fechaEmision == null) {
            this.fechaEmision = LocalDateTime.now();
        }
        if (this.pagado == null) {
            this.pagado = Boolean.FALSE;
        }
    }
}
