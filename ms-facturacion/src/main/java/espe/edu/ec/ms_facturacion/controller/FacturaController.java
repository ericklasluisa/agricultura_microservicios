package espe.edu.ec.ms_facturacion.controller;

import espe.edu.ec.ms_facturacion.dto.FacturaDto;
import espe.edu.ec.ms_facturacion.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // Obtener factura por ID de cosecha
    @GetMapping("/por-cosecha/{cosecha_id}")
    public FacturaDto obtenerPorCosecha(@PathVariable UUID cosecha_id) {
        return facturaService.obtenerFacturaPorCosecha(cosecha_id);
    }

    // Marcar una factura como pagada
    @PutMapping("/{factura_id}/pagada")
    public FacturaDto marcarPagada(@PathVariable UUID factura_id) {
        return facturaService.marcarComoPagada(factura_id);
    }

    // Endpoint opcional para crear factura manualmente (usado para pruebas)
    @PostMapping("/generar")
    public FacturaDto generarFactura(@RequestParam UUID cosecha_id,
                                     @RequestParam String producto,
                                     @RequestParam double toneladas) {
        return facturaService.generarFactura(cosecha_id, producto, toneladas);
    }
}
