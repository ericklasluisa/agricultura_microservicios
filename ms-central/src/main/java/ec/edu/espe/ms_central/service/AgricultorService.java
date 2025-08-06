package ec.edu.espe.ms_central.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.ms_central.dto.AgricultorCreateDto;
import ec.edu.espe.ms_central.model.Agricultor;
import ec.edu.espe.ms_central.repository.AgricultorRepository;

@Service
public class AgricultorService {

  @Autowired
  private AgricultorRepository agricultorRepository;

  public List<Agricultor> findAll() {
    return agricultorRepository.findAll();
  }

  public Optional<Agricultor> findById(Long id) {
    return agricultorRepository.findById(id);
  }

  public Agricultor create(AgricultorCreateDto agricultorCreateDto) {
    Agricultor agricultor = new Agricultor();
    agricultor.setNombre(agricultorCreateDto.getNombre());
    agricultor.setFinca(agricultorCreateDto.getFinca());
    agricultor.setUbicacion(agricultorCreateDto.getUbicacion());
    agricultor.setCorreo(agricultorCreateDto.getCorreo());

    return agricultorRepository.save(agricultor);
  }
}
