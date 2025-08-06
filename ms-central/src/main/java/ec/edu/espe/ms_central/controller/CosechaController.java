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

import ec.edu.espe.ms_central.dto.CosechaCreateDto;
import ec.edu.espe.ms_central.model.Cosecha;
import ec.edu.espe.ms_central.service.CosechaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cosechas")
public class CosechaController {

  @Autowired
  private CosechaService cosechaService;

  @GetMapping
  public ResponseEntity<List<Cosecha>> getAllCosechas() {

    List<Cosecha> cosechas = cosechaService.findAll();
    return ResponseEntity.ok(cosechas);
  }

  @PostMapping
  public ResponseEntity<Cosecha> createCosecha(@Valid @RequestBody CosechaCreateDto cosechaCreateDto) {
    try {
      Cosecha nuevaCosecha = cosechaService.create(cosechaCreateDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCosecha);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
