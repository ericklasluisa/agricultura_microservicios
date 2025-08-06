package ec.edu.espe.ms_central.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ec.edu.espe.ms_central.dto.MensajeColaDto;
import ec.edu.espe.ms_central.model.Cosecha;

@Service
public class CosechaProducer {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void enviarCosechaCreada(Cosecha cosecha) {
    try {
      // Configurar ObjectMapper para manejar LocalDateTime
      objectMapper.registerModule(new JavaTimeModule());
      objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

      // Crear el mensaje con la estructura requerida
      MensajeColaDto mensaje = new MensajeColaDto();
      mensaje.setEvento("nueva_cosecha");
      mensaje.setCosecha_id(UUID.randomUUID().toString());
      mensaje.setProducto(cosecha.getProducto());
      mensaje.setToneladas(cosecha.getToneladas());
      mensaje.setTimestamp(LocalDateTime.now());

      String mensajeJson = objectMapper.writeValueAsString(mensaje);

      // Formatear manualmente el timestamp para que tenga la "Z" al final
      String timestampISO = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
      mensajeJson = mensajeJson.replaceAll("\"timestamp\":\"[^\"]*\"", "\"timestamp\":\"" + timestampISO + "\"");

      rabbitTemplate.convertAndSend("cola_inventario", mensajeJson);
      rabbitTemplate.convertAndSend("cola_facturacion", mensajeJson);

      System.out.println("Mensaje enviado: " + mensajeJson);
    } catch (Exception e) {
      System.err.println("Error al enviar mensaje a la cola: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
