package ec.edu.espe.ms_central.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.ms_central.dto.AgricultorCreateDto;
import ec.edu.espe.ms_central.model.Agricultor;
import ec.edu.espe.ms_central.service.AgricultorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/agricultores")
public class AgricultorController {

  @Autowired
  private AgricultorService agricultorService;

  @GetMapping
  public ResponseEntity<List<Agricultor>> getAllAgricultores() {
    List<Agricultor> agricultores = agricultorService.findAll();
    return ResponseEntity.ok(agricultores);
  }

  @PostMapping
  public ResponseEntity<Agricultor> createAgricultor(@Valid @RequestBody AgricultorCreateDto agricultorCreateDto) {
    try {
      Agricultor nuevoAgricultor = agricultorService.create(agricultorCreateDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAgricultor);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
