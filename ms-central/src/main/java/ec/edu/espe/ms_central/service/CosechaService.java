package ec.edu.espe.ms_central.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.ms_central.dto.CosechaCreateDto;
import ec.edu.espe.ms_central.model.Agricultor;
import ec.edu.espe.ms_central.model.Cosecha;
import ec.edu.espe.ms_central.repository.AgricultorRepository;
import ec.edu.espe.ms_central.repository.CosechaRepository;

@Service
public class CosechaService {

  @Autowired
  private CosechaRepository cosechaRepository;

  @Autowired
  private AgricultorRepository agricultorRepository;

  @Autowired
  private CosechaProducer cosechaProducer;

  public List<Cosecha> findAll() {
    return cosechaRepository.findAll();
  }

  public Cosecha create(CosechaCreateDto cosechaCreateDto) {
    Optional<Agricultor> agricultorOpt = agricultorRepository.findById(cosechaCreateDto.getAgricultorId());

    if (agricultorOpt.isEmpty()) {
      throw new RuntimeException("Agricultor no encontrado con ID: " + cosechaCreateDto.getAgricultorId());
    }

    Cosecha cosecha = new Cosecha();
    cosecha.setProducto(cosechaCreateDto.getProducto());
    cosecha.setToneladas(cosechaCreateDto.getToneladas());
    cosecha.setEstado(cosechaCreateDto.getEstado());
    cosecha.setFactura_id(cosechaCreateDto.getFacturaId());
    cosecha.setAgricultor(agricultorOpt.get());

    // Guardar la cosecha en la base de datos
    Cosecha cosechaGuardada = cosechaRepository.save(cosecha);

    // Enviar mensaje a la cola de RabbitMQ
    try {
      cosechaProducer.enviarCosechaCreada(cosechaGuardada);
    } catch (Exception e) {
      System.err.println("Error al enviar mensaje a RabbitMQ: " + e.getMessage());
      // No fallar la operación si hay error en el envío del mensaje
    }

    return cosechaGuardada;
  }
}
