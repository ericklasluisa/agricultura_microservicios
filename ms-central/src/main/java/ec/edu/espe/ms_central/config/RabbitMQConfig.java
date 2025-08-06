package ec.edu.espe.ms_central.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Queue colaInventario() {
    return QueueBuilder.durable("cola_inventario").build();
  }

  @Bean
  public Queue colaFacturacion() {
    return QueueBuilder.durable("cola_facturacion").build();
  }

}
