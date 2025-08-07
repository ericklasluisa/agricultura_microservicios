package espe.edu.ec.ms_facturacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue colaFacturacion() {
        return new Queue("cola_facturacion", true);
    }
}