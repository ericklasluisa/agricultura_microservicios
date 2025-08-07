package espe.edu.ec.ms_facturacion.listener;

import espe.edu.ec.ms_facturacion.dto.EventoNuevaCosechaDto;
import espe.edu.ec.ms_facturacion.dto.FacturaDto;
import espe.edu.ec.ms_facturacion.service.FacturaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class FacturaEventListener {

    @Autowired
    private FacturaService facturaService;

    @Value("${central.service.url:http://localhost:8080}")
    private String centralServiceUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "${cola.facturacion}")
    public void escucharEventoNuevaCosecha(EventoNuevaCosechaDto evento) {
        if (!"nueva_cosecha".equalsIgnoreCase(evento.getEventType())) return;

        var payload = evento.getPayload();

        try {
            FacturaDto factura = facturaService.generarFactura(
                    payload.getCosechaId(),       // camelCase
                    payload.getProducto(),
                    payload.getToneladas()
            );

            // URL a la que se va a hacer PUT
            String url = centralServiceUrl + "/api/cosechas/" + payload.getCosechaId() + "/estado";

            // Cuerpo de la solicitud
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("estado", "FACTURADA");
            requestBody.put("facturaId", factura.getFacturaId());  // camelCase aquí también

            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            // HTTP PUT con HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonRequest.getBytes());
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                System.out.println("✅ Estado actualizado en microservicio central.");
            } else {
                System.err.println("⚠️ Error al notificar al central. Código: " + responseCode);
            }

        } catch (Exception e) {
            System.err.println("❌ Error en facturación: " + e.getMessage());
        }
    }
}
