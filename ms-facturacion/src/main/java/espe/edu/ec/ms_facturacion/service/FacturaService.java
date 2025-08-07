package espe.edu.ec.ms_facturacion.service;

import espe.edu.ec.ms_facturacion.dto.FacturaDto;
import espe.edu.ec.ms_facturacion.entity.Factura;
import espe.edu.ec.ms_facturacion.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    private static final Map<String, Double> PRECIOS_BASE = Map.of(
            "Arroz Oro", 120.0,
            "Café Premium", 300.0
    );

    public FacturaDto generarFactura(UUID cosechaId, String producto, double toneladas) {
        Double precioPorTonelada = PRECIOS_BASE.get(producto);

        if (precioPorTonelada == null) {
            throw new IllegalArgumentException("Producto no válido: " + producto);
        }

        double montoTotal = toneladas * precioPorTonelada;

        Factura factura = new Factura();
        factura.setFacturaId(UUID.randomUUID());
        factura.setCosechaId(cosechaId);
        factura.setMontoTotal(montoTotal);
        factura.setPagado(false);
        factura.setFechaEmision(LocalDateTime.now());
        factura.setMetodoPago(null);
        factura.setCodigoQr(null);

        factura = facturaRepository.save(factura);

        return mapToDto(factura);
    }

    public FacturaDto obtenerFacturaPorCosecha(UUID cosechaId) {
        Factura factura = facturaRepository.findByCosechaId(cosechaId);

        if (factura == null) {
            throw new IllegalArgumentException("No se encontró factura para esa cosecha");
        }

        return mapToDto(factura);
    }

    public FacturaDto marcarComoPagada(UUID facturaId) {
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));

        factura.setPagado(true);
        factura = facturaRepository.save(factura);

        return mapToDto(factura);
    }

    private FacturaDto mapToDto(Factura factura) {
        return new FacturaDto(
                factura.getFacturaId(),
                factura.getCosechaId(),
                factura.getMontoTotal(),
                factura.getPagado(),
                factura.getFechaEmision(),
                factura.getMetodoPago(),
                factura.getCodigoQr()
        );
    }
}
