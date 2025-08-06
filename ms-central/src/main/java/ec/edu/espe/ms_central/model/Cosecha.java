package ec.edu.espe.ms_central.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "cosecha")
public class Cosecha {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cosecha_id;

  private String producto;
  private double toneladas;
  private String estado;

  @Column(name = "creado_en", updatable = false)
  private LocalDateTime creado_en;

  @Column(name = "factura_id", nullable = true)
  private Long factura_id;

  @ManyToOne
  @JoinColumn(name = "id_agricultor", nullable = false)
  @JsonBackReference
  private Agricultor agricultor;

  @PrePersist
  protected void onCreate() {
    creado_en = LocalDateTime.now();
  }
}
