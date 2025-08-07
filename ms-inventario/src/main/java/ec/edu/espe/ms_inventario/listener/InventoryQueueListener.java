package ec.edu.espe.ms_inventario.listener;

import ec.edu.espe.ms_inventario.dto.MsCentralMessageDto;
import ec.edu.espe.ms_inventario.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryQueueListener {
    @Autowired
    InventoryService service;

    @RabbitListener(queues = "cola_inventario")
    public void recibirMensajes(@Payload MsCentralMessageDto dto) {
        log.info("Mensaje recibido y deserializado automáticamente a DTO: {}", dto);
        try{
            service.updateEntity(dto);
        } catch (Exception e){
            log.error("Error al procesar la notificación para el DTO: {}", dto, e);
        }
    }
}
