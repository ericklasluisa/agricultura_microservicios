package ec.edu.espe.ms_inventario.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String INVENTORY_QUEUE = "cola_inventario";

    @Bean
    public Queue solicitud() {
        return QueueBuilder.durable(INVENTORY_QUEUE).build();
    }
}
