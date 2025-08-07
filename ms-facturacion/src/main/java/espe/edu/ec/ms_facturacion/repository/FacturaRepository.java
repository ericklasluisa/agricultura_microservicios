package espe.edu.ec.ms_facturacion.repository;

import espe.edu.ec.ms_facturacion.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FacturaRepository extends JpaRepository<Factura, UUID> {
    Factura findByCosechaId(UUID cosecha_id);
}
