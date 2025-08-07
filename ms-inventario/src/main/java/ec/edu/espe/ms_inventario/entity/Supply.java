package ec.edu.espe.ms_inventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "insumos")
@Getter
@Setter
public class Supply {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "insumo_id", updatable = false, nullable = false)
    private UUID insumoId;

    @Column(name = "nombre_insumo", length = 100, nullable = false, unique = true)
    private String nombreInsumo;

    @Column(name = "stock", nullable = false)
    @Min(0)
    private double stock = 0.0;

    @Column(name = "unidad_medida", length = 10)
    private String unidadMedida = "kg";

    @Column(name = "categoria", length = 30, nullable = false)
    private String categoria;

    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

}
